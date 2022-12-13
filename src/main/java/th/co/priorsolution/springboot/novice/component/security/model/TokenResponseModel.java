package th.co.priorsolution.springboot.novice.component.security.model;

import lombok.Data;

@Data
public class TokenResponseModel {

    private String token;
    private String headerName;
    private String tokenScheme;
}
