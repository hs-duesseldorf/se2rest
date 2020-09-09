package org.hsd.inflab.se2fxclient.model;

public class Drink extends AbstractModel {

    private String drinkName;

    private Person person;

    public Drink(String drinkName, Person person) {
        this.drinkName = drinkName;
        this.person = person;
    }
    
    public Drink() {}

	public String getDrinkName() {
        return drinkName;
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