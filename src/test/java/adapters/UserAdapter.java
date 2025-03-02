package adapters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.userModels.UserDto;
import models.userModels.UserInfoDto;

import static adapters.AuthAdapter.auth;

public class UserAdapter {

    @Step("Создание нового юзера c данными: {firstName}, {lastName}, {age}, {sex}, {money}")
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

    @Step("Чтение пользователя по ID: {id}")
    public static UserDto readUser(String id) {
        return auth()
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .extract().as(UserDto.class);
    }

    @Step("Редактирование пользователя по ID: {id}")
    public static UserDto updateUser(String id, String firstName, String secondName, String age, String sex, String money) {
        UserDto userDto = UserDto.builder()
                .id(Integer.parseInt(id))
                .firstName(firstName)
                .secondName(secondName)
                .age(Integer.parseInt(age))
                .sex(sex)
                .money(Double.parseDouble(money))
                .build();
        return auth()
                .body(userDto)
                // .log().all()
                .when()
                .put("/user/" + id)
                .then()
                .statusCode(202)
                .log().all()
                .extract().as(UserDto.class);
    }

    @Step("Удаление пользователя по ID: {id}")
    public static void deleteUser(String userId) {
        auth()
                .when()
                .delete("/user/" + userId)
                .then()
                .log().all()
                .statusCode(204)
                .extract().response();
    }

    @Step("Проверка, что пользователя не существует по ID: {id}")
    public static void verifyEntityNotExists(String id) {
        Response response = auth()
                .when()
                .get("/user/" + id)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }

    @Step("Информация о пользователе по ID: {id}")
    public static UserInfoDto getUserInfo(String id) {
        return auth()
                .when()
                .get("/user/" + id + "/info")
                .then()
                .statusCode(200)
                .log().body()
                .extract().as(UserInfoDto.class);
    }
}
