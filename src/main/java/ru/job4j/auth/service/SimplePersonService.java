package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService {

    private final PersonRepository persons;

    @Override
    public List<Person> findAll() {
        return persons.findAll();
    }

    @Override
    public Optional<Person> findById(int id) {
        return persons.findById(id);
    }

    @Override
    public Optional<Person> create(Person person) {
        try {
            persons.save(person);
            return Optional.of(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Person person) {
        if (!persons.existsById(person.getId())) {
            return false;
        }
        persons.save(person);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (!persons.existsById(id)) {
            return false;
        }
        Person person = new Person();
        person.setId(id);
        persons.delete(person);
        return true;
    }
}
