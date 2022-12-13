package th.co.priorsolution.springboot.novice.component.security.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.component.security.config.JwtProperties;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticatedUsers;
import th.co.priorsolution.springboot.novice.component.security.model.TokenResponseModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Date;


@Component
@Slf4j
public class TokenAuthenticationService {


	private JwtProperties jwtProperties;

	public TokenAuthenticationService(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}


	public ResponseModel<TokenResponseModel> generateToken(AuthenticatedUsers authentication) {
        // We generate a token now.
    	String build = writeAsString(authentication);
    	log.info("gen username : "+authentication);
        String JWT = Jwts.builder()
            .setSubject(build)
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getJwtExpirationTime()))
            .signWith(SignatureAlgorithm.HS512, jwtProperties.getJwtSecret())
            .compact();
		TokenResponseModel tokenResponseModel = new TokenResponseModel();
		tokenResponseModel.setToken(JWT);
		tokenResponseModel.setHeaderName(jwtProperties.getJwtHeader());
		tokenResponseModel.setTokenScheme(jwtProperties.getJwtSchema());
		ResponseModel<TokenResponseModel> result = new ResponseModel<>();
		result.setData(tokenResponseModel);
		result.setStatus(200);
		result.setDescription("ok");
		return result;
    } 
     
    public AuthenticatedUsers parseToken(HttpServletRequest request) {
    	String token = request.getHeader(jwtProperties.getJwtHeader());
    	if (null != token) {
    		token = token.replace(jwtProperties.getJwtSchema(), "");
			token = StringUtils.trim(token);
    	}
    	
        if (token != null) {
            // parse the token. 
            Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody(); 
            if (claims != null) // we managed to retrieve a user
            { 
            	AuthenticatedUsers auth = stringToAuth(claims.getSubject());
            	log.info("parse username : "+auth.getName());
                return auth;
            } 
        } 
        return new AuthenticatedUsers(null, null,null,false);
    }
     
    public String writeAsString(Object xx) {
    	ObjectMapper mapper = new ObjectMapper();
    	String auth = null;
		try { 
			auth = mapper.writeValueAsString(xx);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		return auth;
    	 
    } 
     
    public AuthenticatedUsers stringToAuth(String xx) {
    	ObjectMapper mapper = new ObjectMapper();
    	AuthenticatedUsers auth = null;
    	try { 
			auth = mapper.readValue(xx, AuthenticatedUsers.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return auth;
    } 
}
