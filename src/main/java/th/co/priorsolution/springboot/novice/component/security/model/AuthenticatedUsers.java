package th.co.priorsolution.springboot.novice.component.security.model;

import lombok.Data;
import org.springframework.security.core.Authentication;

import java.util.List;

@Data
public class AuthenticatedUsers implements Authentication{

	private String name;
	private AuthenticationCredential credentials;
	private List<Authorities> authorities;
	private boolean authenticated = true;
	private String details;
	private AuthenticatedUserPrincipal principal;
 
	public AuthenticatedUsers() { 
		super(); 
	} 
 
	public AuthenticatedUsers(String name, AuthenticationCredential credentials, List<Authorities> authorities, boolean authenticated) {
		this.name = name;
		this.credentials = credentials;
		this.authorities = authorities;
		this.authenticated = authenticated;
	} 
 
	@Override 
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
		 
	}


}
