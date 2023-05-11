package th.co.priorsolution.springboot.novice.component.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticatedUsers;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticationCredential;
import th.co.priorsolution.springboot.novice.component.security.model.Authorities;
import th.co.priorsolution.springboot.novice.model.MasterUserModel;
import th.co.priorsolution.springboot.novice.model.MasterUserRoleModel;
import th.co.priorsolution.springboot.novice.repository.MasterUserRepository;
import th.co.priorsolution.springboot.novice.repository.MasterUserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AppAuthenticationProviderService {
	
	private final MasterUserRepository masterUserRepository;
	private final MasterUserRoleRepository masterUserRoleRepository;

	public AppAuthenticationProviderService(MasterUserRepository masterUserRepository, MasterUserRoleRepository masterUserRoleRepository) {
		log.info("init AppAuthenticationProviderService");
		this.masterUserRepository = masterUserRepository;
		this.masterUserRoleRepository = masterUserRoleRepository;
	}
	public AuthenticatedUsers authenticate(AuthenticationCredential authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getUsername();
		String password = authentication.getPassword();
		log.info(" userName {} password {}", username, password);
		boolean authenticated = false;
		
		//contain object
		List<Authorities> authorities = new ArrayList<Authorities>();
		//contain usernamePassword
		AuthenticationCredential credentials = new AuthenticationCredential();
		
		MasterUserModel masterUser = masterUserRepository.findByUserNameAndPassword(username, password);
		log.info(masterUser.toString());
		if(null != masterUser) {
			log.info(masterUser.toString());
			authenticated = true;
//			credentials.setUsername(masterUser.getUserName());
//			credentials.setPassword(masterUser.getPassword());
			
			List<MasterUserRoleModel> userRoles = masterUserRoleRepository.findByMasterUserName(masterUser.getUserName());
			if(!userRoles.isEmpty()) {
				log.info(userRoles.toString());
				for (MasterUserRoleModel userRole : userRoles) {
					authorities.add(new Authorities(userRole.getRoleName()));
				}
			}
		}

		AuthenticatedUsers authentication2 = new AuthenticatedUsers(username, credentials, authorities, authenticated);
        return  authentication2;
	}


}
