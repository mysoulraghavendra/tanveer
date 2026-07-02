package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Person;

@RestController
public class HomeController {
	
	
	@GetMapping("/hello")
	public String hello()
		{
		
				return "Hello...World!";
		}
	
	@GetMapping("/persons")
	public Person getPerson()
		{
		
			return new Person("Vinay", 50000);
		}
	
	@GetMapping("/morePersons")
	public List<Person> morePersons()
		{
		
			return List.of(
						new Person("Akash", 40000),
						new Person("Bhagat Ram",45000)
					
					);
		}

}
