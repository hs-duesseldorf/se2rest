package org.hsd.inflab.se2server.restcontroller;

import org.hsd.inflab.se2server.entity.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController extends GenericRestController<Person> {

    @Override
    @PutMapping("/{id}")
    public Person update(@PathVariable long id, @RequestBody Person newPerson) {

        return repository.findById(id).map(person -> {
            person.setName(newPerson.getName());

            return repository.save(person);

        }).orElseGet(() -> {
            newPerson.setId(id);
            return repository.save(newPerson);
        });
        
    }
    
}