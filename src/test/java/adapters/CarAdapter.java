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
                .price(Double.parseDouble(price))
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

    @Step("Чтение автомобиля по ID: {id}")
    public static CarDto readCar(String id) {
        return auth()
                .when()
                .get("/car/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .extract().as(CarDto.class);
    }

    @Step("Редактирование автомобиля по ID: {id}")
    public static CarDto updateCar(String id, String engineType, String mark, String model, String price) {
        CarDto carDto = CarDto.builder()
                .id(Integer.parseInt(id))
                .engineType(engineType)
                .mark(mark)
                .model(model)
                .price(Double.parseDouble(price))
                .build();
        return auth()
                .body(carDto)
                // .log().all()
                .when()
                .put("/car/" + id)
                .then()
                .statusCode(202)
                .log().all()
                .extract().as(CarDto.class);
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

    @Step("Проверка, что автомобиля не существует по ID: {id}")
    public static void verifyCarEntityNotExists(String id) {
        Response response = auth()
                .when()
                .get("/car/" + id)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }
}
