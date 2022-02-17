package org.iti.project.presistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    static private DBConnector instance= null;
    private Connection connection = null;

    private DBConnector() {
    }

    public static DBConnector getConnection() {
        if (instance == null) {
            return new DBConnector();
        }
        return instance;
    }

    public Connection connect() {
        if (connection == null) {
            PropertiesConnection properties = new PropertiesConnection();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_app_project","root" , "root");

            } catch (ClassNotFoundException e) {
                System.err.println("The Layer doesn't connect to DB  DBConnection Error ");
                e.getMessage();
            } catch (SQLException e) {
                System.err.println("Connection to DataBase Failed");
                e.getMessage();
            }
        }
        return connection;

    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) { /* Ignored */}

    }

}