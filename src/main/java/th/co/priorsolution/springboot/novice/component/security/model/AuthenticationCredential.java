package th.co.priorsolution.springboot.novice.component.security.model;

import lombok.Data;
import th.co.priorsolution.springboot.novice.annotations.Hash;

import java.io.Serializable;

@Data
public class AuthenticationCredential implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6651257695048018840L;
	private String username;
	@Hash
	private String password;
	

}
