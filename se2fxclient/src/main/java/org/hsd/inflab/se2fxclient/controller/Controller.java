package org.hsd.inflab.se2fxclient.controller;

import org.hsd.inflab.se2fxclient.model.Drink;
import org.hsd.inflab.se2fxclient.model.Person;
import org.hsd.inflab.se2fxclient.service.DrinkRestService;
import org.hsd.inflab.se2fxclient.service.GenericRestService;
import org.hsd.inflab.se2fxclient.service.PersonRestService;
import org.hsd.inflab.se2fxclient.view.FxPerson;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class Controller {

    GenericRestService<Person> personService;
    GenericRestService<Drink> drinkRestService;

    @FXML
    VBox personsVBox;

    @FXML
    private void initialize() {
        personService = PersonRestService.getInstance();
        drinkRestService = new DrinkRestService();

        if (drinkRestService.connectionIsWorking()) {
            for (Drink drink : drinkRestService.readAll()) {
                Person person = drink.getPerson();
                personsVBox.getChildren().addAll(new FxPerson(person, drink, personsVBox));
            }
            
        } else {
            new Alert(AlertType.ERROR, "Could not connect to server!").showAndWait();
            System.exit(1);
        }
    }

    public void addNewPerson() {
        Person person = personService.create(new Person(""));
        Drink drink = drinkRestService.create(new Drink("", person));
        if (person != null) {
            FxPerson fxPerson = new FxPerson(person, drink, personsVBox);
            personsVBox.getChildren().add(fxPerson);
            fxPerson.getName().requestFocus();
        } else {
            new Alert(AlertType.ERROR, "Could not create Person!").showAndWait();
        }
    }

}
