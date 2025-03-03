package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.houseModels.HouseDto;
import models.houseModels.ParkingPlaceDto;

import java.util.List;

import static adapters.AuthAdapter.auth;

public class HouseAdapter {

    @Step("Создание и получение id нового дома c данными {floorCount}, {price}, {parkingPlaces}")
    public static String createHouse(double floorCount, double price, List<ParkingPlaceDto> parkingPlaces) {
        Gson gson = new Gson();
        HouseDto houseDto = HouseDto.builder()
                .floorCount(floorCount)
                .price(price)
                .parkingPlaces(parkingPlaces)
                .build();
        Response response = auth()
                .body(gson.toJson(houseDto,HouseDto.class))
                .when()
                .post("/house")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response();
        return String.valueOf(response.jsonPath().getInt("id"));
    }

    @Step("Получение данных дома с ID: '{houseId}'")
    public static JsonPath getHouseInfo(String houseId, int getStatus) {
        return auth()
                .when()
                .get("/house/"+houseId)
                .then()
                .statusCode(getStatus)
                .log().all()
                .extract().response().jsonPath();
    }

    @Step("Удаление данных дома с ID: '{houseId}'")
    public static void deleteHouse(String houseId){
        auth()
                .when()
                .delete("/house/"+houseId)
                .then()
                .statusCode(204)
                .log().all()
                .extract().response();
    }

    @Step("Редактирование дома c ID: '{houseId}'")
    public static void updateHouse(String houseId,double floorCount, double price,List<ParkingPlaceDto> parkingPlacesUpdate) {
        Gson gson = new Gson();
        HouseDto houseDto = HouseDto.builder()
                .floorCount(floorCount)
                .price(price)
                .parkingPlaces(parkingPlacesUpdate)
                .build();
        auth()
                .body(gson.toJson(houseDto,HouseDto.class))
                .when()
                .put("/house/"+houseId)
                .then()
                .log().all()
                .statusCode(202)
                .extract().response();
    }
}
