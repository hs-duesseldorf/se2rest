package org.hsd.inflab.se2fxclient.service;

import org.hsd.inflab.se2fxclient.model.Drink;
import org.hsd.inflab.se2fxclient.model.Person;
import org.json.JSONObject;

public class DrinkRestService extends GenericRestService<Drink> {

    private static GenericRestService<Drink> instance;

    public static synchronized GenericRestService<Drink> getInstance() {
        if (instance == null) {
            instance = new DrinkRestService();
        }
        return instance;
    }    

    @Override
    protected String getResourceName() {
        return "drinks";
    }

    @Override
    protected JSONObject createJSONObjectFromModelObject(Drink drink) {

        Person person = drink.getPerson();

        JSONObject jsonDrink = new JSONObject();
        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("name", person.getName());
        jsonPerson.put("id", person.getId());
        jsonDrink.put("drinkName", drink.getDrinkName());
        jsonDrink.put("person", jsonPerson);
        
        return jsonDrink;
    }

    @Override
    protected Drink createModelObjectFromJSONObject(JSONObject jsonDrink) {
        JSONObject jsonPerson = (JSONObject) jsonDrink.get("person");
        Drink drink = new Drink();
        drink.setDrinkName(jsonDrink.getString("drinkName"));
        Person person = new Person();
        person.setId(jsonPerson.getInt("id"));
        person.setName(jsonPerson.getString("name"));
        drink.setPerson(person);
        drink.setId(jsonDrink.getInt("id"));
        return drink;
    }

    public DrinkRestService() {
        super();
    }
    
}