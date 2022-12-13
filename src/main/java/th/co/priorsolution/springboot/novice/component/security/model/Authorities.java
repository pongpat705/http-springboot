package th.co.priorsolution.springboot.novice.component.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authorities implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	 
	private String authority;
	 
	public Authorities(String authority) {
		this.authority = authority;
	} 
 
	public Authorities() { 
		super(); 
	} 
 

}
