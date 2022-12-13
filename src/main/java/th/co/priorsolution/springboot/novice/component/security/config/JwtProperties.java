package th.co.priorsolution.springboot.novice.component.security.config;

import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Data
@Order(1)
public class JwtProperties {
	private final long jwtExpirationTime = 1000*60*5;
	private final String jwtSecret = "78a4de6352ef368a9e8a5533eb25442e0d89ab96c518f63ff69ac810fea01971ce99d2e68a52b1ea028733ed5a4d4c45049c42aff4b28ffb18a84b7b5bf1ccfd";
	private final String jwtSchema = "Bearer";
	private final String jwtHeader = "myapp-token";
	private final String salt = "8cbe18295de55596";

}
