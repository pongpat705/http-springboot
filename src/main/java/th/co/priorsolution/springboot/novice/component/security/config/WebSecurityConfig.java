package th.co.priorsolution.springboot.novice.component.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import th.co.priorsolution.springboot.novice.component.security.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private JWTAuthenticationFilter jwtAuthenticationFilter;

	public WebSecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeRequests()
				.antMatchers("/service/authenticate").permitAll()
				.antMatchers("/api/**").authenticated()
				.antMatchers("/generate/**").hasAuthority("ROLE_STAFF")
				.antMatchers("/upload/**").hasAuthority("ROLE_STAFF")
			.and()
				.exceptionHandling()
				.accessDeniedHandler(new AccessDeniedHandler())
			.and()
				.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)


		;
		return http.build();
	}



}
