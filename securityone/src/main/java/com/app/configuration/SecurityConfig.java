package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoding()
		{
		
		
			return new BCryptPasswordEncoder();
		
		
		}
	
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService()
		{
			UserDetails user=User.builder().username("akash").password(passwordEncoding().encode("123")).roles("USER").build();
			
			UserDetails admin=User.builder().username("admin").password(passwordEncoding().encode("abc")).roles("ADMIN").build();
			
			
			return new InMemoryUserDetailsManager(user,admin);
		
		}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		{
				
				http.csrf(csrf->csrf.disable()).authorizeHttpRequests((authoize)->
					{
						
						
						authoize.anyRequest().authenticated();
					}
						
						).httpBasic(Customizer.withDefaults());
				
				
				return http.build();
		}
}
