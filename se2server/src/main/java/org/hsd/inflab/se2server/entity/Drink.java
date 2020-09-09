package org.hsd.inflab.se2server.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Drink extends AbstractEntity {

    /**
     *
     */
    private static final long serialVersionUID = -557341681206142354L;
    private String drinkName;
    
    
    @OneToOne
    @JoinColumn
    private Person person;  
    

    public String getDrinkName() {
        return drinkName;
    }

    public void setName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Drink() {
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
