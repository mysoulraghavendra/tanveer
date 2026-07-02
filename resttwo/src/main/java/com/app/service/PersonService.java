package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Person;
import com.app.repo.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}
	
	
	
	public Person createPerson(Person person)
		{
		
				return personRepository.save(person);
		}
	
	
}
