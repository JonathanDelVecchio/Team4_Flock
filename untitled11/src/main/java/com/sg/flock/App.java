package com.sg.flock;

import com.sg.flock.dao.FlockDao;
import com.sg.flock.dao.FlockDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        FlockDao dao=new FlockDaoImpl();
        dao.createTables();
        SpringApplication.run(App.class, args);

    }
}