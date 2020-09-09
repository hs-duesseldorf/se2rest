package org.hsd.inflab.se2server.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Person extends AbstractEntity {

	private static final long serialVersionUID = -3421268250084118586L;

	private String name;

	@OneToOne(mappedBy = "person")
    @JsonIgnore
	private Drink drink;

	public Person() {

	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

}