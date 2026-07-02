package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	public static PasswordEncoder passwordEncoder()
		{
		
		
				return new BCryptPasswordEncoder();
		}
	
	
	@Bean
	public UserDetailsService userDetailsService()
		{
		
				UserDetails normal=User.builder().username("tanveer").password(passwordEncoder().encode("tanveer")).roles("USER").build();
				
				UserDetails admin=User.builder().username("vinay").password(passwordEncoder().encode("vinay")).roles("ADMIN").build();
				
				
				return new InMemoryUserDetailsManager(normal,admin);
		
		}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		{
				http.csrf(csrf->csrf.disable()).authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
		
		
				return http.build();
		}

}
