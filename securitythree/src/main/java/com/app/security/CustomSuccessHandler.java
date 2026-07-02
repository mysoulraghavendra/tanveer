package com.app.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		for(GrantedAuthority authority:authentication.getAuthorities())
			{
				String role=authority.getAuthority();
				
				if(role.equals("ROLE_ADMIN"))
					{
					
						response.sendRedirect("/admin");
						
						return;
					}
				
				if(role.equals("ROLE_USER"))
					{
					
						response.sendRedirect("/user");
						return;
					}
			
			
			}
		
		response.sendRedirect("/login");
	}

}
