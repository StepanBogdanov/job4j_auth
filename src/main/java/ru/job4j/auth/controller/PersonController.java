package ru.job4j.auth.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;
import ru.job4j.auth.service.PersonService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        return personService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return personService.delete(id);
    }
}

