package adapters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.UserDto;

import static adapters.AuthAdapter.auth;


public class UserAdapter {

    @Step("Создание пользователя")
    public static String createUser(String firstName, String secondName, String age, String sex, String money) {
        UserDto userDto = UserDto.builder()
                .firstName(firstName)
                .secondName(secondName)
                .age(Integer.parseInt(age))
                .sex(sex)
                .money(Double.parseDouble(money))
                .build();
        Response response = auth()
                .body(userDto)
                .when()
                .post("/user")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();

        return String.valueOf(response.jsonPath().getInt("id"));
    }

    @Step("Чтение пользователя")
    public static String readUser(String id) {
        Response response = auth()
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.asString();
    }

    @Step("Редактирование пользователя")
    public static void updateUser(String id, String firstName, String secondName, String age, String sex, String money) {
        UserDto userDto = UserDto.builder()
                .id(Integer.parseInt(id))
                .firstName(firstName)
                .secondName(secondName)
                .age(Integer.parseInt(age))
                .sex(sex)
                .money(Double.parseDouble(money))
                .build();
        Response response = auth()
                .body(userDto)
                // .log().all()
                .when()
                .put("/user/" + id)
                .then()
                .statusCode(202)
                .log().all()
                .extract()
                .response();
        response.jsonPath().getString("id");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String userId) {
        auth()
                .when()
                .delete("/user/" + userId)
                .then()
                .log().all()
                .statusCode(204)
                .extract().response();
    }

    @Step("Проверка, что пользователя не существует")
    public static void verifyEntityNotExists(String id) {
        Response response = auth()
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }
}
