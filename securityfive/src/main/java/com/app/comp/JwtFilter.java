package com.app.comp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uri=request.getRequestURI();
		
		if(uri.endsWith("/login") || uri.endsWith("/authenticate"))
			{
			
				filterChain.doFilter(request, response);
				
				
				return;
			}
		
		
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		for(Cookie cookie:cookies)
			{
			
				if(cookie.getName().equals("jwt"))
					{
						try
							{
							
							
							jwtUtil.extractUserName(cookie.getValue());
							
							filterChain.doFilter(request, response);
							
							return;
							
							
							}
						catch (Exception e) {
							// TODO: handle exception
						}
					
					}
			
			}
		
		response.sendRedirect("/login");
		
	}

}
