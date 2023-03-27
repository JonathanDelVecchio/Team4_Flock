# README for Flock Backend

| Team Members| 
| ----------------|
| Andrew  |
| Row 2 Column 1  |
| Row 3 Column 1  |
| Row 3 Column 1  |

Flock is a Twitter clone backend built using Java, Spring Boot, and MySQL. This document provides an overview of the backend file structure, as well as descriptions of the individual code files.

### Addressing the Assignment Rubric (Backend Portion):
The code is organized using the MVC approach:
- The Controller layer is represented by the controller.java file.
- The Model layer consists of the tweet.java and reply.java classes.
- The View layer is not applicable here, as it's a RESTful API.

//
    Backend File Structure
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
//

# Code Files
## app.java
This is the entry point of the Flock application. It initializes the Spring Boot application and creates the required tables in the database by calling the createTables() method from the FlockServiceLayer interface.

## mvconfig.java
This class provides configuration for the Spring MVC, adding resource handlers to serve static resources.

## CORSConfig.java
This class configures the CORS policy for the application, allowing requests from specified domains and setting allowed methods and headers.

## controller.java
This class contains RESTful API endpoints for performing CRUD operations on tweets and replies. It uses the FlockServiceLayer interface to interact with the data access layer.

## datapersistenceexception.java
This class extends Exception and represents a custom exception for handling data persistence-related errors.

## datasourcefactory.java
This class provides a factory method for creating a HikariDataSource object configured to connect to a MySQL database.

## flockdao.java (interface)
This interface provides the method signatures for data access operations on tweets and replies, such as inserting, editing, deleting, and retrieving records.

## flockdaoimpl.java
FlockDaoImpl is a Java class that implements the FlockDao interface and provides methods for interacting with a MySQL database to manage tweets and their replies for a social media application. The class utilizes Spring's JdbcTemplate for database operations, and provides methods for creating tables, inserting, updating, deleting, and retrieving tweets and replies, as well as retrieving tweets with their associated replies. Additionally, this class includes utility methods for converting tweets to strings and clearing data from the tweet and reply tables.

## reply.java
This is a simple Java class representing a Reply object, containing properties like id, tweet_id, user_name, title, post, img, and date. The class has getter and setter methods for each property.

## tweet.java
Similar to Reply.java, this class represents a Tweet object with properties like id, user_name, title, post, img, date, and replies (a LinkedList of Reply objects). The class also contains getter and setter methods for each property.

## flockservicelayer.java (interface)
This is an interface that declares the methods required for managing tweets and replies, such as insert, delete, edit, and search operations. It also has methods for creating tables and clearing the tables.

## flockservicelayerimpl.java
This class implements the FlockServiceLayer interface and provides the actual implementation of the methods declared in the interface. It uses a FlockDao instance to perform operations on the data store.

## invalidtweetidexception.java
This class extends Exception and represents a custom exception for handling invalid tweet IDs.

## replyvalidationexception.java
This class extends Exception and represents a custom exception for handling reply validation errors.

## tweetvalidationexception.java
This class extends Exception and represents a custom exception for handling tweet validation errors.

## application.properties
This configuration specifies the database URL, username, password, driver class, and the server port. Make sure to replace the database URL, username, and password with your own values.

## applicationcontext.xml
This file contains the bean definitions for the application.

## flockservicelayerimpltest.java
This is the test class for the FlockServiceLayerImpl, testing the methods implemented in the FlockServiceLayerImpl class.

## index.html
In summary, this code sets up an HTML document with custom fonts from Google Fonts and ensures that the document is responsive for different screen sizes.

## pom.xml
The pom.xml is the Project Object Model (POM) file for the Maven project. It contains information about the project and its dependencies, including the groupId, artifactId, and version of the project. It also defines the parent project, dependencies, and build properties.

##  nbaactions.xml
The nbaactions.xml file contains custom actions for the NetBeans IDE. It specifies the configurations for the "run", "debug", and "profile" actions, such as the goals, properties, and main class to execute. This file helps to customize how your project is executed within the NetBeans IDE.

## nb-configuration.xml
The nb-configuration.xml file is a NetBeans-specific configuration file that holds IDE-related settings. This file helps to maintain consistency in project configuration across different developers using the NetBeans IDE. It contains properties such as the JDK platform hint, which helps the IDE determine the JDK platform to use for the project.