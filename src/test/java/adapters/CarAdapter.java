package adapters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.carModels.CarDto;

import static adapters.AuthAdapter.auth;

public class CarAdapter {

    @Step("Создание нового Автомобиля с данными: {engineType}, {mark}, {model}, {price}")
    public static String createCar(String engineType, String mark, String model, String price) {
        CarDto carDto = CarDto.builder()
                .engineType(engineType)
                .mark(mark)
                .model(model)
                .price(price)
                .build();
        Response response = auth()
                .body(carDto)
                .when()
                .post("/car")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();

        return String.valueOf(response.jsonPath().getInt("id"));
    }

    @Step("Удаление машины по ID: {carId}")
    public static void deleteCar(String carId) {
        auth()
                .when()
                .delete("/car/" + carId)
                .then()
                .log().all()
                .statusCode(204)
                .extract().response();
    }
}

