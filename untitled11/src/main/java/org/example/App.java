package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        Dao dao=new Dao();
        dao.createTables();
        SpringApplication.run(App.class, args);

    }
}