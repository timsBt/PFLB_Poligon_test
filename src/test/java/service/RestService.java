package service;

import dto.PersonDto;
import io.restassured.response.Response;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class RestService {
    public PersonDto getUserWithCars(String personId) {
        Response response = given()
                .baseUri(PropertyReader.getProperty("url"))
                .when()
                .get(String.format("user/%s/info", personId))
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();


        return new PersonDto();
    }
}