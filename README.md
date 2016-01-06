## Virtual Currency Exchange Rate Publishing System

# Overview
Virtual Currency Exchange Rate Publishing System consist of a modern web client and a server. 
Client should be able to communicate with server using TCP or HTTP protocol.

The server would expose following services for the client application to consume,

 - list of exchange rates for different currencies
 - search for the exchange rate for a specific currency and 
 - a currency converter to convert a specific amount of money from a currency to another.
 
To get currency exchange rates, It uses one of the public currency converter API at server side to get latest conversion rates.

[openexchangerates](https://openexchangerates.org/signup/free) OR [currencylayer](https://currencylayer.com/documentation) , 

# Features

- Integration with database migration tool- [FlyWay](http://flywaydb.org/)
- RESTful APIs
- Database- In memory H2 database and MySQL database. You can choose either one by just changing Spring bean profile
  in *web.xml*.
- Unit Testing- Junit, EasyMock, H2 database and Spring Test Suite

# Technologies used
 - Java
 - Spring
 - Hibernate
 - H2 Database
 - MySQL database
 - Gradle
 - Flyway

# Setting up development environment (On Ubuntu)
Instructions to install and configure any prerequisites for the development environment

## Installing Java

 - sudo apt-get install python-software-properties
 
 - sudo apt-get install software-properties-common
    
 - sudo add-apt-repository ppa:webupd8team/java
 
 - sudo apt-get update
 
 - sudo apt-get install oracle-java8-installer
 
 - set JAVA_HOME in /etc/profile (if not set automatically by above commands)
 
 - Open /etc/profile and edit with following content
  
    JAVA_HOME=/usr/lib/jvm/java-8-oracle 
    PATH=$PATH:$HOME/bin:$JAVA_HOME/bin
    JRE_HOME=/usr/lib/jvm/java-8-oracle 
    PATH=$PATH:$HOME/bin:$JRE_HOME/bin
    export JAVA_HOME
    export JRE_HOME
    export PATH

## Installing Gradle

 - download gradle binary distribution from http://gradle.org/gradle-download/
 
  - unzip gradle binary and move it to /opt directory
  
  - open /etc/profile and set GRADLE_HOME, example given below
   
    - export GRADLE_HOME=/opt/gradle
   
## Database Setup

This project uses in memory H2 database for local setup. You can also use MySQL database. To use MySQL database you need to 
change spring bean profile in web.xml.
We have two spring bean profile:

 - local
    -  local profile uses in memory H2 database
  
 - dev
    - dev profile uses MySQL. 

Note: If you are using MySQL, then you need to change MySQL username and password for datasource bean in persistent-beans.xml

### Changing Spring bean profile
 You can activate spring profile in web.xml by changing context param value.
 
  ```
   <context-param>
         <param-name>spring.profiles.active</param-name>
         <param-value>local</param-value>
     </context-param>
   ``` 
   
## Database migration

 - All database migration scripts are available in db/migration directory within project.
 
 - This project has FlyWay integrated, so you don't need to run db migration scripts manually. Flyway will automatically
 run those scripts at the time of server startup.
 

# Running

  - You can run this project from the command-line from the root directory of project:
 
 ```
 $ gradle jettyRunWar 
 ```
 
 - This command creates in memory H2 database and tables with seed data and run application on embedded Jetty server.


 - After startup, your instance will be available on localhost, port 8080.

```
http://localhost:8080/
```

 - You can also run it as a webapp in Tomcat, by deploying the war file generated in the build directory.



