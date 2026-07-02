package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.app.security.CustomSuccessHandler;

@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
		{
		
			return new BCryptPasswordEncoder();
		}
	
	@Bean
	public AuthenticationSuccessHandler successHandler()
		{
			return new CustomSuccessHandler();
		
		}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		{
				http.authorizeHttpRequests(auth->auth.requestMatchers("/login").permitAll().requestMatchers("/admin").
						hasRole("ADMIN").requestMatchers("/user").hasRole("USER").anyRequest().authenticated()).
				formLogin(form->form.loginPage("/login").successHandler(successHandler()).permitAll())
				.logout(logout->logout.logoutSuccessUrl("/login?logout"))
				
				;
		
		
				return http.build();
		
		
		}

}
