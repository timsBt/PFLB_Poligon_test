package adapters;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.PropertyReader.getProperty;

public class UserAdapter {

    @Step("Создание пользователя")
    public static String createUser(String name, String lastName, String age, String sex, String money) {
        RestAssured.baseURI = System.getProperty("urlApi", getProperty("urlApi"));
        String token = AuthAdapter.getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        String requestBody = "{"
                + "\"id\": 0,"
                + "\"firstName\": \"" + name + "\","
                + "\"secondName\": \"" + lastName + "\","
                + "\"age\":" + age + ","
                + "\"sex\": \"" + sex + "\","
                + "\"money\":" + money + "}";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .statusCode(201)
                .extract()
                .response();
        return response.jsonPath().getString("id");
    }

    @Step("Чтение пользователя")
    public static String readUser(String id) {
        RestAssured.baseURI = System.getProperty("urlApi", getProperty("urlApi"));
        String token = AuthAdapter.getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        Response response = given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.asString();
    }

    @Step("Редактирование пользователя")
    public static void updateUser(String id, String name, String lastName, String age, String sex, String money) {
        RestAssured.baseURI = System.getProperty("urlApi", getProperty("urlApi"));
        String token = AuthAdapter.getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        String requestBody = "{"
                + "\"id\":" + id + ","
                + "\"firstName\": \"" + name + "\","
                + "\"secondName\": \"" + lastName + "\","
                + "\"age\":" + age + ","
                + "\"sex\": \"" + sex + "\","
                + "\"money\":" + money + "}";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                // .log().all()
                .when()
                .put("/user/" + id)
                .then()
                .statusCode(202)
                //.log().all()
                .extract()
                .response();
        response.jsonPath().getString("id");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String id) {
        RestAssured.baseURI = System.getProperty("urlApi", getProperty("urlApi"));
        String token = AuthAdapter.getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        Response response = given()
                .header("accept", "*/*")
                .header("Authorization", "Bearer " + token)
                //.log().all()
                .when()
                .delete("/user/" + id)
                .then()
                .statusCode(204)
                // .log().all()
                .extract().response();
    }

    @Step("Проверка, что пользователя не существует")
    public static void verifyEntityNotExists(String id) {
        RestAssured.baseURI = System.getProperty("urlApi", getProperty("urlApi"));
        String token = AuthAdapter.getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        Response response = given()
                .header("Content-Type", "application/json")
                .header("host", "82.142.167.37")
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }
}
