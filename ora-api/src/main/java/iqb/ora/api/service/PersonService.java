package iqb.ora.api.service;

import java.util.List;

import iqb.ora.api.entity.Person;

public interface PersonService {
	Person save(Person person);
	List<Person> listAll();
}