package service;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.userModels.PersonDto;
import models.userModels.UserDto;

import static adapters.AuthAdapter.urlApi;
import static io.restassured.RestAssured.given;

public class RestService {

    @Step("Получение списка машин у {userId}")
    public PersonDto getUserWithCars(String personId) {
        String response = given()
                .baseUri(urlApi)
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("/user/%s/info", personId))
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .extract().asString();

        Gson gson = new Gson();
        return gson.fromJson(response, PersonDto.class);
    }

    @Step("Покупка или продажа машины по {userId} and {carId}")
    public UserDto buyOrSellCar(
            final String userId,
            final String carId,
            final OperationWithCar operationWithCar,
            final String token
    ) {
        String response = given()
                .baseUri(urlApi)
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body("")
                .when()
                .log().all()
                .post(String.format("/user/%s/%s/%s", userId, operationWithCar.getSendName(), carId))
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .extract().asString();

        Gson gson = new Gson();
        return gson.fromJson(response, UserDto.class);
    }
}