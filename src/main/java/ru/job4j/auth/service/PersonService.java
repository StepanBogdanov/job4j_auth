package ru.job4j.auth.service;

import org.springframework.http.ResponseEntity;
import ru.job4j.auth.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(int id);

    Optional<Person> create(Person person);

    boolean update(Person person);

    boolean delete(int id);
}