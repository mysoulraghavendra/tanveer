package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.component.JwtUtil;
import com.app.entity.AuthRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request)
		{
				if("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword()))
					{
					
							return jwtUtil.generateToken(request.getUsername());
					}
				return "Invalid Credentials";
		}
	
	
	

}
