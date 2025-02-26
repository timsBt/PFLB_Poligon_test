package utils;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

import java.sql.*;

import static utils.PropertyReader.getProperty;

public class DbConnect {

    @Description("Подключение к БД")
    public static Connection connectToDb() throws SQLException {
        String dbLogin = System.getProperty("dbLogin", getProperty("dbLogin"));
        String dbPass = System.getProperty("dbPass", getProperty("dbPass"));
        String dbUrl = System.getProperty("dbUrl", getProperty("dbUrl"));
        return DriverManager.getConnection(dbUrl, dbLogin, dbPass);
    }

    @Step("Выполняем запрос к БД и возвращаем результат")
    public static ResultSet executeSqlQuery(String query) throws SQLException {
        Connection db = connectToDb();
        Statement st = db.createStatement();
        return st.executeQuery(query);
    }
}
