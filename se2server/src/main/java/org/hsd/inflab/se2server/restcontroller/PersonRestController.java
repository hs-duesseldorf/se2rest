package org.hsd.inflab.se2server.restcontroller;

import org.hsd.inflab.se2server.entity.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonRestController extends GenericRestController<Person> {

    @Override
    protected Person updateEntity(Person e, Person newE) {
        if (null != newE.getName() && !newE.getName().isEmpty()) {
            e.setName(newE.getName());
        }
        
        e.setDrink(newE.getDrink());
        return e;
    }    
    
}