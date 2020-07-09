package org.hsd.inflab.se2fxclient.controller;

import java.util.List;

import org.hsd.inflab.se2fxclient.model.Person;
import org.hsd.inflab.se2fxclient.service.PersonRestService;
import org.hsd.inflab.se2fxclient.view.FxPerson;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class PersonController {

    PersonRestService personService;

    @FXML
    VBox personsVBox;

    @FXML
    private void initialize() {
        personService = new PersonRestService();
        List<Person> persons = personService.readAll();
        if (personService.connectionIsWorking() == true && persons != null) {
            for (Person person : persons) {
                addNewPersonFromService(person);
            }
        } else {
            new Alert(AlertType.ERROR, "Could not connect to server!").showAndWait();
            System.exit(1);
        }
    }

    private void addNewPersonFromService(Person person) {
        personsVBox.getChildren().add(new FxPerson(person, personsVBox));
    }

    public void addNewPerson() {
        Person person = personService.create(new Person(""));
        if (person != null) {
            FxPerson fxPerson = new FxPerson(person, personsVBox);
            personsVBox.getChildren().add(fxPerson);
            fxPerson.getName().requestFocus();
        } else {
            new Alert(AlertType.ERROR, "Could not create Person!").showAndWait();
        }

    }

}
