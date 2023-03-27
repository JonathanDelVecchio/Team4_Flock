# README for Flock Backend
The backend of Flock is built using Java, Spring Boot, and MySQL. This document provides an overview of the backend file structure, as well as descriptions of the individual code files.

|Team Members| 
|----------------|
|Andrew Wang|
|Nicholas Tan|
|Jonathan Del Vecchio|
|Deniz Childir|

# General Requirements

Java version: JDK 11 or later is required, as the pom.xml specifies a source and target version of Java 20. However, it is recommended to use JDK 11, which is the latest LTS (Long Term Support) release, since Java 20 is not a valid version.

Build tool: Maven (version 3.6.0 or later is recommended)

IDE: Any Java-supported IDE can be used, such as IntelliJ IDEA, Eclipse, or NetBeans. The project includes configuration files for NetBeans.

Dependencies: The following dependencies are required and specified in the pom.xml file:

#### Spring Boot 3.0.4

- spring-boot-starter-web
- spring-boot-starter-jdbc
- spring-boot-devtools
- MySQL Connector/J 8.0.32 (MySQL Java driver)

#### Spring JDBC 6.0.6

#### HikariCP 5.0.1 (Connection pool)

#### JUnit Jupiter (JUnit 5) for unit testing

Database: MySQL 8.0 or later is required for the project.

Configuration: Update the application.properties file with the correct database URL, username, and password for your MySQL server.

# Backend File Structure:

    -Flock
    --Source Packages
    ---com.sg.flock
    ----app.java
    ----mvconfig.java
    ---com.sg.flock.config
    ----CORSConfig.java
    ---com.sg.flock.controller
    ----controller.java
    ---com.sg.flock.dao
    ----datapersistenceexception.java
    ----datasourcefactory.java
    ----flockdao.java (interface)
    ----flockdaoimpl.java
    ---com.sg.flock.dto
    ----reply.java
    ----tweet.java
    ---com.sg.flock.service
    ----flockservicelayer.java (interface)
    ----flockservicelayerimpl.java
    ----invalidtweetidexception.java
    ----replyvalidationexception.java
    ----tweetvalidationexception.java
    --Test Packages
    ---com.sg.flock.service
    ----flockservicelayerimpltest.java
    --Other Sources
    ---src/main/resources
    ----application.properties
    ----applicationcontext.xml
    ---static
    ----3rdpartylicenses.txt
    ----favicon.ico
    ----index.html
    ----main.ab5b08a6712688e6
    ----polyfills.a348946cf6c5bf93.js
    ----runtime.0eced33022643abb.js
    ----styles.3073e10a0bddda7f.css
    --Project Files
    ----pom.xml
    ----nbaactions.xml
    ----nb-configuration.xml


# Code Files and Descriptions:

### app.java
This is the entry point of the Flock application. It initializes the Spring Boot application and creates the required tables in the database by calling the createTables() method from the FlockServiceLayer interface.

### mvconfig.java
This class provides configuration for the Spring MVC, adding resource handlers to serve static resources.

### CORSConfig.java
This class configures the CORS policy for the application, allowing requests from specified domains and setting allowed methods and headers.

### controller.java
This class contains RESTful API endpoints for performing CRUD operations on tweets and replies. It uses the FlockServiceLayer interface to interact with the data access layer.

### datapersistenceexception.java
This class extends Exception and represents a custom exception for handling data persistence-related errors.

### datasourcefactory.java
This class provides a factory method for creating a HikariDataSource object configured to connect to a MySQL database.

### flockdao.java (interface)
This interface provides the method signatures for data access operations on tweets and replies, such as inserting, editing, deleting, and retrieving records.

### flockdaoimpl.java
FlockDaoImpl is a Java class that implements the FlockDao interface and provides methods for interacting with a MySQL database to manage tweets and their replies for a social media application. The class utilizes Spring's JdbcTemplate for database operations, and provides methods for creating tables, inserting, updating, deleting, and retrieving tweets and replies, as well as retrieving tweets with their associated replies. Additionally, this class includes utility methods for converting tweets to strings and clearing data from the tweet and reply tables.

### reply.java
This is a simple Java class representing a Reply object, containing properties like id, tweet_id, user_name, title, post, img, and date. The class has getter and setter methods for each property.

### tweet.java
Similar to Reply.java, this class represents a Tweet object with properties like id, user_name, title, post, img, date, and replies (a LinkedList of Reply objects). The class also contains getter and setter methods for each property.

### flockservicelayer.java (interface)
This is an interface that declares the methods required for managing tweets and replies, such as insert, delete, edit, and search operations. It also has methods for creating tables and clearing the tables.

### flockservicelayerimpl.java
This class implements the FlockServiceLayer interface and provides the actual implementation of the methods declared in the interface. It uses a FlockDao instance to perform operations on the data store.

### invalidtweetidexception.java
This class extends Exception and represents a custom exception for handling invalid tweet IDs.

### replyvalidationexception.java
This class extends Exception and represents a custom exception for handling reply validation errors.

### tweetvalidationexception.java
This class extends Exception and represents a custom exception for handling tweet validation errors.

### application.properties
This configuration specifies the database URL, username, password, driver class, and the server port. Make sure to replace the database URL, username, and password with your own values.

### applicationcontext.xml
This file contains the bean definitions for the application.

### flockservicelayerimpltest.java
This is the test class for the FlockServiceLayerImpl, testing the methods implemented in the FlockServiceLayerImpl class.
This class is used to test our program functions.

#### setup()
Clears our tables, creates a tweet and reply to test with in the database.

#### FlockServiceLayerImplTest()
Initalizes our dao and service for use

#### testInsertTweetandgetTweetById()
Tests inserting a tweet by checking if the inserted tweet has correct user_name, title, post, img and date compared to what should be inserted.

#### testInsertReply()
Tests insterting a reply in the same way as testInsertTweetandgetTweetById()

#### testDeleteTweet()
Tests deleting a tweet by creating an extra tweet, deleting it and then comparing the old tweet count to new tweet count + 1 are equal.

#### testDeleteReply()
Tests deleting a reply in the same way as testDeleteTweet()

#### testEditTweet()
Tests editing a tweet by editing a tweet and checking if the edited fields were updated properly compared to the intended data.

#### testEditReply()
Tests editing a reply in the same way as testEditTweet()

#### testGetAllTweets()
Tests getting a list of tweets by checking if the size is 1, which it should be due to our setup()

#### testGetRepliesForTweetId()
Tests editing a reply in the same way as testGetRepliesForTweetId()

#### testGetTweetByUserName()
Tests getting a tweet by username by creating a new tweet, inserting it and checking if the amount of tweets grabbed based on the intended username is 1.

#### testGetTweetByUserName()
Tests editing a reply in the same way as testGetTweetByUserName()

### index.html
In summary, this code sets up an HTML document with custom fonts from Google Fonts and ensures that the document is responsive for different screen sizes.

### pom.xml
The pom.xml is the Project Object Model (POM) file for the Maven project. It contains information about the project and its dependencies, including the groupId, artifactId, and version of the project. It also defines the parent project, dependencies, and build properties.

###  nbaactions.xml
The nbaactions.xml file contains custom actions for the NetBeans IDE. It specifies the configurations for the "run", "debug", and "profile" actions, such as the goals, properties, and main class to execute. This file helps to customize how your project is executed within the NetBeans IDE.

### nb-configuration.xml
The nb-configuration.xml file is a NetBeans-specific configuration file that holds IDE-related settings. This file helps to maintain consistency in project configuration across different developers using the NetBeans IDE. It contains properties such as the JDK platform hint, which helps the IDE determine the JDK platform to use for the project.


# Addressing the Assignment Rubric (Backend Portion):
The code is organized using the MVC approach:
- The Controller layer is represented by the controller.java file.
- The Model layer consists of the tweet.java and reply.java classes.
- The View layer is not applicable here, as it's a RESTful API.

Flowcharts, UML diagrams, and ERD is included under the Team4_Flock.

The code is organized in an object-oriented style, with classes and interfaces representing different components of the application.

Proper naming conventions are used consistently for variables, classes, and interfaces throughout the project.

The layers communicate appropriately:
- The Controller layer communicates with the Service layer using the FlockServiceLayer interface.
- The Service layer communicates with the Data Access layer using the FlockDao interface.

The flockservicelayerimpltest.java file contains unit tests for the application. 

Error handling is demonstrated through custom exceptions like InvalidTweetIdException, DataPersistenceException, TweetValidationException, and ReplyValidationException. These exceptions are thrown and caught in appropriate places.

The application includes a MySQL database, with the following tables and fields:
- tweets: id, user_name, title, post, img, date
- replies: id, tweet_id, user_name, title, post, img, date

The application uses Spring tools:
- Spring Boot is used to build and run the application.
- Spring DI is utilized with appropriate @Annotations, such as @Controller, @Service, and @Autowired.
