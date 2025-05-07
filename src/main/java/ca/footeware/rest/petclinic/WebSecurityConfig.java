package ca.footeware.rest.petclinic;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Specify the URLs to which the current user can access and those that are
 * forwarded to login.
 *
 * @author Footeware.ca
 */
@Configuration
public class WebSecurityConfig {
	
	@Value("${spring.security.user.password}")
	private String password;

	/**
	 * A bean that configures HTTP security.
	 *
	 * @param {@link HttpSecurity}
	 * @return {@link SecurityFilterChain}
	 * @throws Exception when the internet falls over.
	 */
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors(withDefaults()).csrf((csrf) -> csrf.disable())
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/**").hasRole("USER").anyRequest().authenticated())
				.httpBasic(withDefaults());
		
		return http.build();
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT",
						"HEAD");
			}
		};
	}

	@Bean
	UserDetailsService userDetailsService() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		UserDetails user = User.withUsername("craig").password(encodedPassword).roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}
}