package th.co.priorsolution.springboot.novice.component.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticatedUsers;
import th.co.priorsolution.springboot.novice.component.security.service.TokenAuthenticationService;
import th.co.priorsolution.springboot.novice.model.ErrorModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JWTAuthenticationFilter extends GenericFilterBean{

	private AntPathRequestMatcher loginMatcher;
	private AntPathRequestMatcher errorMatcher;
	private TokenAuthenticationService tokenAuthenticationService;

	public JWTAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.loginMatcher = new AntPathRequestMatcher("/service/authenticate");
		this.errorMatcher = new AntPathRequestMatcher("/error");
	}

	@Override 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//incoming request check it! 


		if(this.loginMatcher.matches((HttpServletRequest) request)
				|| this.errorMatcher.matches((HttpServletRequest) request)){
			log.info("no token request from : "+request.getRemoteHost());
			chain.doFilter(request,response);
		} else {
			log.info("token request from : "+request.getRemoteHost());
			try {
				AuthenticatedUsers authentication = this.tokenAuthenticationService.parseToken((HttpServletRequest)request);
				if(authentication.isAuthenticated()){
					SecurityContextHolder.getContext().setAuthentication((Authentication) authentication);
					chain.doFilter(request,response);
				}else {
					this.writeErrorToOutputStream(response, "is not Authenticated", 401);
				}
			} catch (Exception e) {
				this.writeErrorToOutputStream(response, e.getMessage(), 500);
			}
		}
	}

	private void writeErrorToOutputStream(ServletResponse response, String errorMessage, int statusCode) throws IOException {
		List<ErrorModel> errorModelList = new ArrayList<>();
		ErrorModel errorModel = new ErrorModel();
		errorModel.setCode("07");
		errorModel.setDescription("cant parse token");
		errorModelList.add(errorModel);
		ResponseModel<Void> result = new ResponseModel<>();
		result.setStatus(statusCode);
		result.setDescription(errorMessage);
		result.setErrors(errorModelList);


		response.setContentType("application/json");
		response.getWriter().write(tokenAuthenticationService.writeAsString(result));
	}

}
