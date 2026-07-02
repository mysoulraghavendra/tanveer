package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/login")
	public String loginPage()
		{
		
			return "login";
		
		}
	
	@GetMapping("/admin")
	public String adminPage()
		{
		
				return "admin";
		}
	
	@GetMapping("/user")
	public String userPage()
		{
				return "user";
		
		}

}
