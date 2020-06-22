package iqb.ora.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iqb.ora.api.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}