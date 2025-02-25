package tests.apiTests;

import carsData.SellingCar;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SellCarTest {
    @Test
    public void testSellCar() {
        given()
                .baseUri("http://82.142.167.37:4881/")
                .contentType("application/json")
                .body(new SellingCar("123", "456"))
                .when()
                .post("/sellCar")
                .then()
                .statusCode(200)
                .body("status", equalTo("Successfully pushed"))
                .body("code", equalTo(200));
    }
}
