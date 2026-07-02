package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ResttwoApplication;
import com.app.entity.Person;
import com.app.service.PersonService;

@RestController
@RequestMapping("/persons/operation")
public class PersonController {

    
	
	
	
	private PersonService personService;
	@Autowired
	public PersonController(PersonService _personService, ResttwoApplication resttwoApplication)
		{
		
			this.personService=_personService;
		
	
		}
	@PostMapping
	public Person createPerson(@RequestBody Person person)
		{
		
				return personService.createPerson(person);
		}

}
