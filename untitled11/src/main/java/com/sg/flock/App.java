package com.sg.flock;

import com.sg.flock.dao.FlockDao;
import com.sg.flock.dao.FlockDaoImpl;
import com.sg.flock.service.FlockServiceLayer;
import com.sg.flock.service.FlockServiceLayerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        //FlockDao dao=new FlockDaoImpl();
        //dao.createTables();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlockServiceLayer sl = ctx.getBean("serviceLayer", FlockServiceLayerImpl.class);
        
        sl.createTables();
        SpringApplication.run(App.class, args);

    }
}