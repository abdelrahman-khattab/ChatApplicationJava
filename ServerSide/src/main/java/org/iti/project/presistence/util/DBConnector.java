package org.iti.project.presistence.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    static private DBConnector instance= null;
    private Connection connection = null;
    private static HikariDataSource dataSource;
    private DBConnector() {
    }

    public static DBConnector getConnection() {
        if (instance == null) {
            return new DBConnector();
        }
        return instance;
    }

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/chat_app_project?user=root&password=1234567890");
        dataSource = new HikariDataSource(config);
        dataSource.setMaximumPoolSize(5);
    }
    public Connection connect() {
        if (connection == null) {
            PropertiesConnection properties = new PropertiesConnection();
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                System.out.println("db pool connection fail");
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