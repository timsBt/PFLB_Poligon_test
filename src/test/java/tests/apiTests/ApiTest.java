package tests.apiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utils.PropertyReader.getProperty;

@Epic("Авторизация")
public class ApiTest {

    //метод для получения токена номер 1
    public static String getToken(String login, String password) {
        RestAssured.baseURI = System.getProperty("url", getProperty("urlSwagger"));
        String requestBody = "{"
                + "\"username\": " + "\"" + login + "\","
                + "\"password\": " + "\"" + password + "\""
                + "}";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .statusCode(202)
                .extract()
                .response();
        return response.jsonPath().getString("access_token");
    }

    //метод для получения токена номер 2
    public static String getToken2(String login, String password) {
        RestAssured.baseURI = System.getProperty("url", getProperty("urlSwagger"));

        Response response2 = given()
                .accept("application/json")
                .header("host", "82.142.167.37")
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", password)
                .formParam("username", login)
                .when()
                .post("/login")
                .then()
                .statusCode(202)
                .extract()
                .response();
        return response2.jsonPath().getString("access_token");
    }

    public static boolean isValidJwt(String token) {
        // Регулярное выражение для проверки формата JWT
        String jwtPattern = "^[A-Za-z0-9_-]+.[A-Za-z0-9_-]+.[A-Za-z0-9_-]+$";
        return token != null && token.matches(jwtPattern);
    }

    @Test(testName = "Получение токена авторизации первым способом")
    @Description("Получение токена авторизации первым способом")
    @Story("Авторизация")
    @Feature("Авторизация")
    public void getAccessToken() {
        String token = getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        if (isValidJwt(token)) {
            System.out.println("Строка содержит валидный JWT токен.");
        } else {
            System.out.println("Строка не содержит валидный JWT токен.");
        }
    }

    @Test(testName = "Получение токена авторизации вторым способом")
    @Description("Получение токена авторизации вторым способом")
    @Story("Авторизация")
    @Feature("Авторизация")
    public void getAccessToken2() {
        String token = getToken2(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));

        if (isValidJwt(token)) {
            System.out.println("Строка содержит валидный JWT токен.");
        } else {
            System.out.println("Строка не содержит валидный JWT токен.");
        }
    }
}


