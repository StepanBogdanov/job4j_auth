package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService {

    private final PersonRepository persons;

    @Override
    public List<Person> findAll() {
        return persons.findAll();
    }

    @Override
    public ResponseEntity<Person> findById(int id) {
        var person = persons.findById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );    }

    @Override
    public ResponseEntity<Person> create(Person person) {
        return new ResponseEntity<Person>(
                persons.save(person),
                HttpStatus.CREATED
        );    }

    @Override
    public ResponseEntity<Void> update(Person person) {
        if (!persons.existsById(person.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person with this ID has not been found");
        }
        persons.save(person);
        return ResponseEntity.ok().build();    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        if (!persons.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person with this ID has not been found");
        }
        Person person = new Person();
        person.setId(id);
        persons.delete(person);
        return ResponseEntity.ok().build();    }
}
