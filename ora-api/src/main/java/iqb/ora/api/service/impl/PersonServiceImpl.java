package iqb.ora.api.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iqb.ora.api.entity.Person;
import iqb.ora.api.repository.PersonRepository;
import iqb.ora.api.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	private static final Logger log = LogManager.getLogger(PersonServiceImpl.class);
	private PersonRepository personRepository;
	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public List<Person> listAll() {
		return personRepository.findAll();
	}
}