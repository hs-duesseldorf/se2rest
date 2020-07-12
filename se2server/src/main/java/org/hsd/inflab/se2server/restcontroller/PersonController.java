package org.hsd.inflab.se2server.restcontroller;

import org.hsd.inflab.se2server.entity.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController extends GenericRestController<Person> {

    @Override
    protected Person updateEntity(Person e, Person newE) {
        e.setName(newE.getName());

        return e;
    }    
    
}