package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.PropertyReader.getProperty;

public class DbConnect {

    public static Connection connectToDb() throws SQLException {
        String dbLogin = System.getProperty("dbLogin", getProperty("dbLogin"));
        String dbPass = System.getProperty("dbPass", getProperty("dbPass"));
        String dbUrl = System.getProperty("dbUrl", getProperty("dbUrl"));
        return DriverManager.getConnection(dbUrl, dbLogin, dbPass);
    }
}
