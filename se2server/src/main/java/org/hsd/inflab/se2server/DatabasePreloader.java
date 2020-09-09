package org.hsd.inflab.se2server;

import org.hsd.inflab.se2server.entity.Drink;
import org.hsd.inflab.se2server.entity.Person;
import org.hsd.inflab.se2server.repository.DrinkRepository;
import org.hsd.inflab.se2server.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabasePreloader {

    Logger log = LoggerFactory.getLogger(DatabasePreloader.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, DrinkRepository drinkRepository) {
        return args -> {
            
            Drink cola = new Drink();
            cola.setName("cola");
            Person jens = new Person();
            jens.setName("jens");
            cola.setPerson(jens);

            
            personRepository.save(jens);
            drinkRepository.save(cola);
        };
    }
}
