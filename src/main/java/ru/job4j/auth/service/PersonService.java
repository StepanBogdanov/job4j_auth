package ru.job4j.auth.service;

import org.springframework.http.ResponseEntity;
import ru.job4j.auth.domain.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    ResponseEntity<Person> findById(int id);

    ResponseEntity<Person> create(Person person);

    ResponseEntity<Void> update(Person person);

    ResponseEntity<Void> delete(int id);
}
