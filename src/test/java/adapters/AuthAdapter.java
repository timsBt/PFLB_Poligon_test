package adapters;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.PropertyReader.getProperty;

@Epic("Авторизация")
public class AuthAdapter {

    @Step("Получение токена первым способом")
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

    @Step("Получение токена вторым способом")
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

    @Step("Валидация JWT токена")
    public static boolean isValidJwt(String token) {
        // Регулярное выражение для проверки формата JWT
        String jwtPattern = "^[A-Za-z0-9_-]+.[A-Za-z0-9_-]+.[A-Za-z0-9_-]+$";
        return token != null && token.matches(jwtPattern);
    }
}
