package db;

import io.qameta.allure.Step;

import java.sql.*;

import static utils.PropertyReader.getProperty;

public class DbConnect {

    private static Connection connect;

    @Step("Подключение к БД")
    public static Connection connectToDb() throws SQLException {
        String dbLogin = System.getProperty("dbLogin", getProperty("dbLogin"));
        String dbPass = System.getProperty("dbPass", getProperty("dbPass"));
        String dbUrl = System.getProperty("dbUrl", getProperty("dbUrl"));
        return DriverManager.getConnection(dbUrl, dbLogin, dbPass);
    }

    @Step("Выполняем запрос к БД и возвращаем результат")
    public static ResultSet executeSqlQuery(String query) throws SQLException {
        connect = connectToDb();
        Statement st = connect.createStatement();
        return st.executeQuery(query);
    }

    @Step("Отключение соединения БД")
    public static void closeConnect() throws SQLException {
        connect.close();
    }
}
