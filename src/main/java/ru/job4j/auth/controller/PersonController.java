package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.dto.PersonDto;
import ru.job4j.auth.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class.getSimpleName());

    private final PersonService personService;
    private BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<List<Person>>(
                personService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = personService.findById(id);
        return new ResponseEntity<Person>(
                person.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A person with this ID has not been found")),
                HttpStatus.OK
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        String pass = person.getPassword();
        person.setPassword(encoder.encode(pass));
        var savedPerson = personService.create(person);
        return new ResponseEntity<Person>(
                savedPerson.orElse(new Person()),
                savedPerson.isPresent() ? HttpStatus.CREATED : HttpStatus.CONFLICT
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Person person) {
        if (!personService.update(person)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person with this ID has not been found");
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody PersonDto personDto) {
        var current = personService.findById(personDto.getId());
        if (current.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person with this ID has not been found");
        }
        current.get().setPassword(encoder.encode(personDto.getPassword()));
        personService.update(current.get());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!personService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person with this ID has not been found");
        }
        return ResponseEntity.ok().build();
    }

}

