package iqb.ora.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iqb.ora.api.entity.Person;
import iqb.ora.api.service.PersonService;
import iqb.ora.api.util.Validator;

@RestController
@CrossOrigin(allowedHeaders = {"*"}, origins = {"*"})
@RequestMapping(value = "/person")
public class PersonController {
	private static final Logger log = LogManager.getLogger(PersonController.class);	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping("/new")
	public Person addPerson(@RequestBody Person person) {
		//Data validation
		if(validData(person)) {
			
			return personService.save(person);
		} else {
			log.error("Person could not be created. Data is invalid");
			return null;
		}
	}
	
	@GetMapping("/all")
	public List<Person> listAllPeople() {
		return personService.listAll();
	}
	
	/**
	 * Validates input data 
	 * @param surname
	 * @param firstname
	 * @param idType
	 * @param idNumber
	 * @return
	 */
	private Boolean validData(Person person) {
		if(person.getSurname() == null || person.getSurname().isEmpty()) {
			log.error("Surname is invalid");
			return false;
		}
		if(person.getFirstname() == null || person.getFirstname().isEmpty()) {
			log.error("Firstname is invalid");
			return false;
		}
		if(person.getIdNumber() == null || person.getIdNumber().isEmpty()) {
			log.error("ID number is invalid");
			return false;
		} else {
			// Is ID a valid SA ID number
			if(!Validator.isValidIdNumber(person.getIdNumber())) {
				log.error("ID number is invalid");
				return false;
			}
		}
		
		return true;
	}

}
