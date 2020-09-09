package org.hsd.inflab.se2fxclient.view;

import org.hsd.inflab.se2fxclient.model.Drink;
import org.hsd.inflab.se2fxclient.model.Person;
import org.hsd.inflab.se2fxclient.service.DrinkRestService;
import org.hsd.inflab.se2fxclient.service.GenericRestService;
import org.hsd.inflab.se2fxclient.service.PersonRestService;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FxPerson extends HBox {

    private TextField nameTextField;
    private TextField drinkTextField;
    private Button delete, OK;
    private Person person;
    private Drink drink;
    private GenericRestService<Person> personRestService;
    private GenericRestService<Drink> drinkRestService;

    public FxPerson(Person person, Drink drink, VBox parentVBox) {
        this.person = person;
        this.drink = drink;

        personRestService = PersonRestService.getInstance();
        drinkRestService = DrinkRestService.getInstance();
        
        nameTextField = new TextField(this.person.getName());
        drinkTextField = new TextField(this.drink.getDrinkName());

        delete = new Button("delete");
        delete.setOnAction(e -> {
            drinkRestService.delete(drink);
            personRestService.delete(person);
            
            parentVBox.getChildren().removeAll(this);
        });
        OK = new Button("OK");
        OK.setOnAction(e -> {
            person.setName(nameTextField.textProperty().getValue());
            drink.setDrinkName(drinkTextField.textProperty().getValue());
            personRestService.update(this.person);
            drinkRestService.update(this.drink);
        });
        getChildren().addAll(nameTextField, drinkTextField, OK, delete);
        applyStyling();
    }

    public TextField getName() {
        return this.nameTextField;
    }

    private void applyStyling() {
        nameTextField.setStyle("-fx-border-radius: 10 0 0 10");
        nameTextField.setStyle("-fx-background-radius: 10 0 0 10");
        nameTextField.setMinWidth(150);
        nameTextField.setMaxWidth(150);
        drinkTextField.setMinWidth(150);
        drinkTextField.setMaxWidth(150);
        drinkTextField.setStyle("-fx-border-radius: 0");
        drinkTextField.setStyle("-fx-background-radius: 0");
        delete.setStyle("-fx-border-radius: 0 10 10 0");
        delete.setStyle("-fx-background-radius: 0 10 10 0");
        delete.setMinWidth(100);
        delete.setMaxWidth(100);
        OK.setMinWidth(50);
        OK.setMaxWidth(50);
        OK.setStyle("-fx-border-radius: 0");
        OK.setStyle("-fx-background-radius: 0");
    }
}