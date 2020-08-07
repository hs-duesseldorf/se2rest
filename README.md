- [1. Introduction](#1-introduction)
  - [1.1. Nice-to-know](#11-nice-to-know)
  - [1.2. Idea](#12-idea)
  - [1.3. Learning Goals](#13-learning-goals)
- [2. Database](#2-database)
  - [2.1. Install XAMPP](#21-install-xampp)
    - [2.1.1 Install via Wizard](#211-install-via-wizard)
    - [2.1.2. Install XAMPP via docker image (Ubuntu)](#212-install-xampp-via-docker-image-ubuntu)
  - [2.2. Setup user, database and permission](#22-setup-user-database-and-permission)
- [3. Server](#3-server)
  - [3.1. Preparation](#31-preparation)
    - [3.1.2 Select a workspace](#312-select-a-workspace)
    - [3.1.3. Create a new maven project](#313-create-a-new-maven-project)
    - [3.1.4. Include needed java libraries](#314-include-needed-java-libraries)
    - [3.1.5. Prepare the spring boot server application](#315-prepare-the-spring-boot-server-application)
    - [3.1.6. Make the application runnable](#316-make-the-application-runnable)
  - [3.2. Server implementation](#32-server-implementation)
    - [3.2.1. Create package structure](#321-create-package-structure)
    - [3.2.2. Entities](#322-entities)
      - [3.2.2.1. AbstractEntity.java](#3221-abstractentityjava)
      - [3.2.2.2 Person.java](#3222-personjava)
    - [3.2.3. Repositories](#323-repositories)
      - [3.2.3.1. GenericRepository.java](#3231-genericrepositoryjava)
      - [3.2.3.2. PersonRepository.java](#3232-personrepositoryjava)
    - [3.2.4. Restcontroller](#324-restcontroller)
      - [3.2.4.1. GenericRestController.java](#3241-genericrestcontrollerjava)
      - [3.2.4.2. PersonRestController.java](#3242-personrestcontrollerjava)
  - [3.3. Testing the server](#33-testing-the-server)
- [4. Client](#4-client)
  - [4.1. Preparation](#41-preparation)
    - [4.1.2. Create maven project with archetype](#412-create-maven-project-with-archetype)
    - [4.1.3. Delete unwanted files](#413-delete-unwanted-files)
    - [4.1.4. Customize pom.xml](#414-customize-pomxml)
  - [4.2 Implementation](#42-implementation)
    - [4.2.1. Create package structure](#421-create-package-structure)
    - [4.2.2. Configure module-info.java](#422-configure-module-infojava)
    - [4.2.3. Create App class](#423-create-app-class)
    - [4.2.4. Finalize the app class](#424-finalize-the-app-class)
    - [4.2.5. Create the FXML file for the UI hierarchy](#425-create-the-fxml-file-for-the-ui-hierarchy)
    - [4.2.6. First App test](#426-first-app-test)
    - [4.2.7. Model classes](#427-model-classes)
      - [4.2.7.1 AbstractModel.java](#4271-abstractmodeljava)
      - [4.2.7.2. Person.java](#4272-personjava)
    - [4.2.8. Service classes](#428-service-classes)
      - [4.2.8.1. GenericRestService.java](#4281-genericrestservicejava)
      - [4.2.8.2. PersonRestService.java](#4282-personrestservicejava)
    - [4.2.9. Controller class](#429-controller-class)
    - [4.2.10. Service properties](#4210-service-properties)
    - [4.2.11. View classes](#4211-view-classes)
    - [4.2.12. Set controller in fxml view](#4212-set-controller-in-fxml-view)
    - [4.2.13. Fill up the view](#4213-fill-up-the-view)
    - [4.2.14. Finale module-info.java version](#4214-finale-module-infojava-version)
- [5. Full stack test](#5-full-stack-test)


# 1. Introduction

In this internship we will create a javafx client which communicates with a server via http. We use XAMPP, Java, Maven and Spring Boot for this.
The latter is only a means to an end here and should not be discussed further.
We use the Java development environment Eclipse, but thanks to the modularity of
Maven projects any other Java development environment can also be used
like Intellij IDEA, NetBeans or Visual Studio Code Java. And gradle could also be used to include the dependencies and build the project, however this we will use eclipse and maven here.

## 1.1. Nice-to-know

- [Eclipse](https://de.wikipedia.org/wiki/Eclipse_(IDE)) or [Visual Studio Code Java](https://code.visualstudio.com/docs/languages/java)
- [Gluon Scene Builder](https://gluonhq.com/products/scene-builder)
- [Annotationen](https://de.wikipedia.org/wiki/Annotation_(Java))
- [Generics](https://de.wikipedia.org/wiki/Generische_Programmierung_in_Java)
- [Maven](https://de.wikipedia.org/wiki/Apache_Maven)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [HTTP](https://de.wikipedia.org/wiki/Hypertext_Transfer_Protocol) und [REST](https://de.wikipedia.org/wiki/Representational_State_Transfer)
- [JavaFX](https://openjfx.io/)
- [Java Moduls](https://openjdk.java.net/projects/jigsaw/quick-start)
- [Singletons](https://de.wikibooks.org/wiki/Muster:_Java:_Singleton)

## 1.2. Idea

JavaFX Client ⟹(HTTP&JSON)⟹ Spring Boot REST JPA Server ⟹ Database

JavaFX Client ⟸(HTTP&JSON)⟸ Spring Boot REST JPA Server ⟸ Database

## 1.3. Learning Goals

- Use Maven to access external java libraries
- Use abstract classes, abstract methods and generics to reduce code duplication
- Get to know spring boot
- Learn REST and HTTP
- Learn the basics of Java FX and Scene Builder

# 2. Database

## 2.1. Install XAMPP

XAMPP can be used to simplify the process of setting up a database, user and permissions. If you are familiar with mysql you may not want to use XAMPP at all.
XAMPP contains a mysql database, an apache server and PHP to control your database from within your browser with phpmyadmin.

You can install XAMPP with __either__ an installation wizard for Windows/MacOS/Linux __or__ via a docker image.

### 2.1.1 Install via Wizard

- Download the [installation wizard](https://www.apachefriends.org/de/download.html) for your operating system
- Linux users need to make the installer runnable with (version number may change...)
    ```bash
    cd ~Downloads
    chmod +x xampp-linux-x64-7.4.7-0-installer.run
    sudo ./xampp-linux-x64-7.4.7-0-installer.run
    ```
- Click through the wizard to install XAMPP and start it

### 2.1.2. Install XAMPP via docker image (Ubuntu)

- Open a terminal with CTRL + ALT + T and insert the following commands successively :
    ```bash
    sudo apt install docker.io
    sudo groupadd docker
    sudo usermod -aG docker $USER
    reboot # this restarts your machine
    docker pull cwsl/xampp # start a new terminal
    ```
- Download [this script](https://raw.githubusercontent.com/cswl/xampp-docker/master/xampp-docker.sh) to your ```~/Downloads``` directory
- Open the terminal and run the following commands successively to make this *docker-convenience-startup-script* runnable and finally to run, which starts a new docker container, containing the XAMPP instance
    ```bash
    cd ~/Downloads
    chmod +x xampp-docker.sh
    ./xampp-docker.sh
    ```
- If you do not plan to use an IDE (vscode + docker extension for example) to control the docker container, you can stop the container with:
    ```bash
    ./xampp-docker.sh stop
    ```

## 2.2. Setup user, database and permission

- navigate to http://localhost:8086/phpmyadmin
- click on "User accounts"
- click on "Add user account"
- insert a user name and password
- **IMPORTANT**: check "Create database with same name and grant all privileges." and "Grant all privileges on wildcard name (username\_%)." and "Check all"
- don't change anything else
- click "Go"

# 3. Server

## 3.1. Preparation

Eclipse differentiates between the directory in which it has its workspace
and the directories in which the projects are located.
I.e. the workspace can be in a different directory than the projects
(Maven projects, Gradle projects, simple Eclipse Java projects) -
everything can also be in a directory. In other IDEs
(Integrated Development Environment) the workspace does not necessarily have to be selected
 (Visual Studio Code). Once projects have been created, they can be used in anyone
 imported into another workspace, if you want.

### 3.1.2 Select a workspace

![](images/eclipse01_workspace.png)

### 3.1.3. Create a new maven project

![2](images/eclipse02_create_maven.png)

![3](images/eclipse03_create_maven2.png)

![4](images/eclipse04_create_maven3.png)

### 3.1.4. Include needed java libraries

![](images/eclipse11_insert_dependencies.png)

Open ```pom.xml``` and insert the following XML snippet under ```<packaging>war</packaging>``` and above ```</project>```:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### 3.1.5. Prepare the spring boot server application

Create the package ```org.hsd.inflab.se2server```

![createpackage](images/eclipse07_create_package.png)
![createpackage2](images/eclipse08_create_package2.png)


Create the class ```Application.java``` inside the package you've just created:

![createapplication](images/eclipse09_create_application_class.png)

Name it Application and click to insert main method:

![createapplication2](images/eclipse10_create_application_class2.png)

### 3.1.6. Make the application runnable

- Open ```Application.java```
- Insert ```@SpringBootApplication``` above ```public class Application```
- Insert ```SpringApplication.run(Application.class, args);``` inside the main() method to run the application
- Import missing dependencies with CTRL + SHIFT + O (ALT + SHIFT + O in vscode)
- Save the file (CTRL + S)

- Create the file ```application.properties``` inside the ```src/main/resources``` package and insert the following properties and change to your database name and user credentials:

    ```
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.datasource.url=jdbc:mysql://ADDRESS:3306/DATABASENAME
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
    spring.datasource.username=USERNAME
    spring.datasource.password=PASSWORD
    spring.jackson.serialization.fail-on-empty-beans=false
    spring.jpa.show-sql=true
    ```

- Run ```Application.java``` (Eclipse: right click -> Run as -> Java application | VsCode: click on "run" above main()) to make a first test, we will need to restart it later

## 3.2. Server implementation

### 3.2.1. Create package structure

Create inside the meta package ```org.hsd.inflab.se2server``` with

![createpackages](images/eclipse13_createpackages.png)

the following packages:

- ```org.hsd.inflab.se2server.entity```
- ```org.hsd.inflab.se2server.repository```
- ```org.hsd.inflab.se2server.restcontroller```

### 3.2.2. Entities

Create ```AbstractEntity.java``` and ```Person.java``` inside the ```entity``` package (depending on your package presentation it might be displayed with its full name ```org.hsd.inflab.se2server.entity```)

#### 3.2.2.1. AbstractEntity.java

```java
package org.hsd.inflab.se2server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true)
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
```

#### 3.2.2.2 Person.java

```java
package org.hsd.inflab.se2server.entity;

import javax.persistence.Entity;

@Entity
public class Person extends AbstractEntity {
    
    private static final long serialVersionUID = -3421268250084118586L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }
    
}
```

### 3.2.3. Repositories

Create the interfaces (not classes) ```GenericRepository.java``` and ```PersonRepository.java``` inside the ```repository``` package. The person repository works as our data access object (DAO) you might know from other stacks. Depending on your future projects, it might make sense to move away from the generic approach here and just create one repository interface for each entity.

#### 3.2.3.1. GenericRepository.java

```java
package org.hsd.inflab.se2server.repository;

import java.io.Serializable;
import org.hsd.inflab.se2server.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface GenericRepository<T extends AbstractEntity> extends JpaRepository<T, Serializable> { }
```

#### 3.2.3.2. PersonRepository.java

```java
package org.hsd.inflab.se2server.repository;

import org.hsd.inflab.se2server.entity.Person;

public interface PersonRepository extends GenericRepository<Person> { }    
```

### 3.2.4. Restcontroller

The restcontroller(s) define how the server answer to http calls from clients. Each HTTP command (GET, POST, PUT, DELETE) is translated to a CRUD (create, read/receive, update, delete) operation to store the received JSON in the database as a table row, or to retrieve information the client(s) ask(s) for from the database and send it back inside the http body in JSON.

#### 3.2.4.1. GenericRestController.java

```java
package org.hsd.inflab.se2server.restcontroller;

// import ...  organize imports (eclipse: CTRL + SHIFT + O | vscode: ALT + SHIFT + O)

public abstract class GenericRestController<E extends AbstractEntity> {
    
    // We don't know which fields the entities have so
    // the concrete classes need to implement this method
    protected abstract E updateEntity(E e, E newE);
    
    @Autowired
    protected GenericRepository<E> repository;

	@GetMapping // HTTP GET
	public List<E> list() {
		return repository.findAll();
    }
    
	@PostMapping // HTTP POST
	public E create(@RequestBody E entity) {
		return repository.save(entity);
    }
    
	@DeleteMapping("{id}") // HTTP DELETE
	public void delete(@PathVariable(value = "id") long id) {
		repository.deleteById(id);
    }
    
	@GetMapping("{id}") // HTTP GET
	public E get(@PathVariable(value = "id") long id) {
		return repository.getOne(id);
    }
    
    @PutMapping("{id}") // HTTP PUT
    public E update(@PathVariable long id, @RequestBody E newE) {

        return repository.findById(id).map(e -> {
            e = updateEntity(e, newE);

			return repository.save(e);

        }).orElseGet(() -> {
            newE.setId(id);
            return repository.save(newE);
        });        
    }
}
```

#### 3.2.4.2. PersonRestController.java

```java
package org.hsd.inflab.se2server.restcontroller;

import org.hsd.inflab.se2server.entity.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// the url suffix where the person resource
// is mapped to
@RequestMapping("/persons")
public class PersonRestController extends GenericRestController<Person> {

    // Our concrete entity person has only one field
    // So this might look a bit over engineered
    @Override
    protected Person updateEntity(Person e, Person newE) {
        e.setName(newE.getName());

        return e;
    }    
    
}
```

## 3.3. Testing the server

You could now test the server with any rest client. For example:

- ["Rested" addon for firefox](https://addons.mozilla.org/de/firefox/addon/rested/)
- ["Advanced REST client" extension for chrome/edge](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo?hl=de)
- the CLI program [curl](https://curl.haxx.se/) (ubuntu: sudo apt install curl)
- [REST client vscode extension](https://marketplace.visualstudio.com/items?itemName=humao.rest-client)

# 4. Client

## 4.1. Preparation

### 4.1.2. Create maven project with archetype

This time we create the maven project from an archetype. Normally this saves time if you know which dependencies/libraries you want to include, before you start.

Right click on a free spot inside the package explorer and create a new maven project with with *New -> Other -> Search for "Maven" -> Maven project*

![createclientproject](images/eclipse14_client_maven_project.png)

![createclientproject](images/eclipse15_client_maven_project2.png)

This time do NOT check the box for 

![createclientproject3](images/eclipse16_client_maven_project3.png)

Search for *javafx* inside the filter textfield and select the row with *org.openjfx javafx-archetype-fxml*

![createclientproject4](images/eclipse17_client_maven_project4.png)

Now insert again the groupId *org.hsd.inflab* but the artifactId *se2fxclient*. The package needs to be named *org.hsd.inflab.se2fxclient*

![createclientprojet5](images/eclipse18_client_maven_project5.png)

### 4.1.3. Delete unwanted files

The archetype also created four files which we do not need now. Delete:

- App.java
- Controller.java
- both .fxml files

We will create similar files in the next steps.

### 4.1.4. Customize pom.xml

Even though we've created the maven project from the archetype, we still need to add two dependencies (external java libraries) for creating a HTTPClient and JSONObjects into ```pom.xml```. Inside ```<depedencies>...</dependencies>``` add the following lines (depending on the time you read this tutorial, you might need to adapt the version numbers...):

```xml
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.9</version>
</dependency>
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20190722</version>
</dependency>
```
**ATTENTION**: we use the apache HttpClient, because it allows us to create a CloseableHttpClient, which is closed automatically in a try-with-resource statement. This is currently not possible with the HttpClient in the JDK, so we would need to close the HttpClient ourselves. So make sure you import the correct (apache) HttpClient!

## 4.2 Implementation

### 4.2.1. Create package structure

Inside ```src/main/java``` create the following packages successively

- ```org.hsd.inflab.se2fxclient.model```
- ```org.hsd.inflab.se2fxclient.view```
- ```org.hsd.inflab.se2fxclient.controller```
- ```org.hsd.inflab.se2fxclient.service```

via

![createclientpackages](images/eclipse19_client_package.png)

and the **FOLDER** named ```view``` and ```service``` inside the ```src/main/resources``` package:

![createclientresourcefolder2](images/eclipse22_client_resource_folder2.png)

![createclientresourcefolder3](images/eclipse22_client_resource_folder3.png)

So you end up with:

![createclientresourcefolder](images/eclipse22_client_resource_folder.png)

### 4.2.2. Configure module-info.java

**IMPORTANT**: since Java9 javafx is not included in the JDK anymore, so we need to use the new module system to make the JavaFX Modules accessible. Let's start with this version of module-info.java but make sure to comment line 6 and 7 for now, because the controller package is currently empty. We will need to adapt module-info.java while we fill the packages:

![moduleinfo](images/eclipse21_client_module_info.png)

### 4.2.3. Create App class

Create the class ```App.java``` inside the ```view``` package, check the box at ```public static void main(String[] args)``` and select ```Application``` from the package ```javafx.application``` as superclass:

![createclientapp](images/eclipse20_client_app.png)

### 4.2.4. Finalize the app class

Put the following code into ```App.java``` and organize the imports:

```java
package org.hsd.inflab.se2fxclient.view;

import java.io.IOException;

// import ...  organize imports (eclipse: CTRL + SHIFT + O | vscode: ALT + SHIFT + O)

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PersonView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
```

***IMPORTANT***: ```App.java``` and ```PersonView.fxml``` need to be in the same package: ```view```. But the java file inside ```src/main/java/org/hsd/inflab/se2fxclient/view``` and the fxml file inside ```src/main/resources/org/hsd/inflab/se2fxclient/view``` !!! Otherwise the FxmlLoader will not find the fxml file.

### 4.2.5. Create the FXML file for the UI hierarchy

Open SceneBuilder and create a new file, drag and drop the container ```BorderPane``` out of the left panel (section ```Containers```) onto the drawing area and save the file afterwards into the directory ```se2fxclient/src/main/resources/org/hsd/inflab/se2fxclient/view```

![newfxml](images/eclipse23_new_fxml.png)

### 4.2.6. First App test

Switch back to your IDE and start ```App.java``` with ```Righ click -> Run as-->Java Application``` (vscode users: click on ```Run | Debug``` above ```public static void main(String[] args)```)

![testapp](images/eclipse24_test_app.png)

... and a shiny empty white window should appear

![testapp2](images/eclipse25_test_app2.png)

### 4.2.7. Model classes

Every person should be availible on each layer. First of all as a simple model class ```Person.java``` inside the client. Create the model classes ```AbstractModel.java``` and ```Person.java``` inside the ```model``` package.

#### 4.2.7.1 AbstractModel.java

```java
package org.hsd.inflab.se2fxclient.model;

public class AbstractModel {
    
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
```
#### 4.2.7.2. Person.java

```java
package org.hsd.inflab.se2fxclient.model;

public class Person extends AbstractModel {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }    
}
```

### 4.2.8. Service classes

#### 4.2.8.1. GenericRestService.java

```java
package org.hsd.inflab.se2fxclient.service;

import ... // STRG + SHIFT + O

public abstract class GenericRestService<M extends AbstractModel> {
    
    private static String baseUrl;
    private static String url;
    protected abstract String getResourceName();
    protected abstract JSONObject createJSONObjectFromModelObject(M m);
    protected abstract M createModelObjectFromJSONObject(JSONObject jsonObject);
    
    protected void createURL() {
        loadProperties();
        url = new String(baseUrl + "/" + getResourceName());
    }

    public GenericRestService() {
        createURL();
    }

    public boolean connectionIsWorking() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(baseUrl);
            HttpResponse response = client.execute(request);
            return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }        
    }

    public static void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(GenericRestService.class.getResourceAsStream("connections.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseUrl = properties.getProperty("base.url");
    }

    public M create(M m) {
        JSONObject jsonObject = createJSONObjectFromModelObject(m);
        HttpPost request = new HttpPost(url);
        HttpResponse response;
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            request.setEntity(new StringEntity(jsonObject.toString()));
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
            m.setId(personJSON.getInt("id"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<M> readAll() {
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        List<M> list = new ArrayList<>();
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            response = client.execute(request);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (JSONObject jsonObject : getJSONObjectListFromJSONArray(jsonArray)) {
                list.add(createModelObjectFromJSONObject(jsonObject));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(M m) {
        JSONObject jsonObject = createJSONObjectFromModelObject(m);
        HttpPut request = new HttpPut(url + "/" + m.getId());
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            request.setEntity(new StringEntity(jsonObject.toString()));
            client.execute(request);
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(M m) {
        HttpDelete request = new HttpDelete(url + "/" + m.getId());
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            client.execute(request);
            System.out.println("Deleted Entity with id: " + m.getId());
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
```

#### 4.2.8.2. PersonRestService.java

### 4.2.9. Controller class

### 4.2.10. Service properties

### 4.2.11. View classes

### 4.2.12. Set controller in fxml view

### 4.2.13. Fill up the view

### 4.2.14. Finale module-info.java version

# 5. Full stack test