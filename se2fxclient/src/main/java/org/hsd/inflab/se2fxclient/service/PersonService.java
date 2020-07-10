package org.hsd.inflab.se2fxclient.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hsd.inflab.se2fxclient.model.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PersonService {

    private final static String BASE_URL = "http://localhost:8080";
    private final static String URL = BASE_URL + "/" + "persons";

    public boolean connectionIsWorking() {
        boolean connectionOK = false;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(URL);
            HttpResponse response = client.execute(request);
            connectionOK = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return connectionOK;
        
    }

    public Person create(Person person) {
        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("name", person.getName());

        HttpPost request = new HttpPost(URL);
        HttpResponse response;
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        StringEntity stringEntity;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            stringEntity = new StringEntity(jsonPerson.toString());
            request.setEntity(stringEntity);
            response = client.execute(request);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                System.out.println(line);
            }
            JSONObject personJSON = new JSONObject(stringBuilder.toString());
            person.setId(personJSON.getInt("id"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    public List<Person> readAll() {
        HttpGet request = new HttpGet(URL);
        HttpResponse response;
        List<Person> persons = new ArrayList<>();
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            response = client.execute(request);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONArray personsJSONArray = new JSONArray(stringBuilder.toString());
            for (JSONObject personJSONObject : getJSONObjectListFromJSONArray(personsJSONArray)) {
                persons.add(new Person(personJSONObject.getString("name")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public void update(Person person) {
        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("name", person.getName());
        jsonPerson.put("id", person.getId());
        HttpPut request = new HttpPut(URL + "/" + person.getId());
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        StringEntity stringEntity;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            stringEntity = new StringEntity(jsonPerson.toString());
            request.setEntity(stringEntity);
            client.execute(request);
            System.out.println(jsonPerson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Person person) {
        HttpDelete request = new HttpDelete(URL + "/" + person.getId());
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            client.execute(request);
            System.out.println("Deleted Entity with id: " + person.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<JSONObject> getJSONObjectListFromJSONArray(JSONArray array) throws JSONException {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < (array != null ? array.length() : 0); jsonObjects.add(array.getJSONObject(i++)))
            ;
        return jsonObjects;
    }
}