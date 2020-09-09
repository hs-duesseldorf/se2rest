package org.hsd.inflab.se2server.restcontroller;

import org.hsd.inflab.se2server.entity.Drink;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drinks")
public class DrinkRestController extends GenericRestController<Drink> {

    @Override
    protected Drink updateEntity(Drink e, Drink newE) {
        e.setName(newE.getDrinkName());
        return e;
    }
    
}