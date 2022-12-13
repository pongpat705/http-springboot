package th.co.priorsolution.springboot.novice.restcontroller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import th.co.priorsolution.springboot.novice.component.security.model.AuthenticationCredential;
import th.co.priorsolution.springboot.novice.component.security.model.TokenResponseModel;
import th.co.priorsolution.springboot.novice.component.security.service.AppAuthenticationProviderService;
import th.co.priorsolution.springboot.novice.component.security.service.TokenAuthenticationService;
import th.co.priorsolution.springboot.novice.model.ResponseModel;

@RestController
public class AuthenticationRestController {
    private AppAuthenticationProviderService appAuthenticationProviderService;
    private TokenAuthenticationService tokenAuthenticationService;

    public AuthenticationRestController(AppAuthenticationProviderService appAuthenticationProviderService, TokenAuthenticationService tokenAuthenticationService) {
        this.appAuthenticationProviderService = appAuthenticationProviderService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @PostMapping("/service/authenticate")
    public ResponseModel<TokenResponseModel> authentication(@RequestBody AuthenticationCredential authenticationCredential){
        return this.tokenAuthenticationService
                .generateToken(
                        this.appAuthenticationProviderService.authenticate(authenticationCredential)
                );
    }
}
