package tests.apiTests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class SellAndBuyCarApiTest {

    @Test
    public void SellAndBuyCar() {
        String response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get()
    }
}
