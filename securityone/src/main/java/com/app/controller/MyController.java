package com.app.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	

	
	
	@GetMapping("/welcome")
	public String welcome()
		{
			
			return "Hello...User";
		
		}
	
	@GetMapping("/admin")
	public String admin()
		{
		
			return "Hello...Admin!";
		}

}
