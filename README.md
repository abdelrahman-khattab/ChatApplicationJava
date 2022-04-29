# ChatApplicationJava
ITI Chat Application  

>A desktop chatting application built using JavaFX and RMI as our first project at the Information Technology Institute. It takes full advantage of JavaFX's reactive programming model, using property binding to synchronize Models with Views.

# 🏛 Architecture
This software project was built using a layered architecture. The following diagram demonstrates an example use case that goes through all the layers. 

![image](https://user-images.githubusercontent.com/73137611/155808282-0a0ee1ab-9d35-4258-a953-c9ccad72f707.png)

# ⚙ Features
* Registration and login
* One to one and group chats
* File transfer
* Server statistics and management 
* Updating user profiles
* AIML chatbot
* Save messages to database


# ⚙ Technologies used
* Maven
* JDBC
* Sockets
* RMI
* JavaFX
* IO / NIO
* Bean Validation (Hibernate Validator)
* Logback
* HikariCP
* MySQL

# 🛠 How to run
**Maven**
* Install it using the following maven command:
```
mvn install:install-file -Df`ile=Ab.jar -DgroupId=com.google -DartifactId=Ab -Dversion=0.0.4.3 -Dpackaging=jar
```
* Run the `mvn install` phase from the chatty-parent directory to create the shaded executable jars in the `target/` directory
* Run the jars using the `java -jar` command

**MySQL**
* Use the chatty.sql script to create the database using MySQL 8.0.28
```

# 👷‍♀️ Contributors
* [Ahmed Ashraf](https://github.com/ahmedashrfhassan)
* [Abdelrahman Khattab](https://github.com/abdelrahman-khattab)
* [Abdallah](https://github.com/abdallah)
* [Hala](https://github.com/Hala-45)
* [Sally](https://github.com/sallyElbanawany)

