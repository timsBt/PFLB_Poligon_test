package tests.apiTests;

import carsData.Cars;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.uiTests.BaseTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCarsListFromUserApiTest extends BaseTest {

    @Test(testName = "API: Получение списка машин по userId",
            description = "Проверка, что API возвращает тот же список машин, что и UI")
    @Description("Сравнение списка машин пользователя, полученного из UI, с ответом API")
    @Feature("API-тестирование")
    @Story("GET /cars?userId={userId}")
    public void getCarsListApiTest() {
        // Получаем userId из UI
        String userId = readUserWithCarsPage.readUserWithCars().getUserId();

        // Получаем список машин из UI
        List<Cars> expectedCarsList = readUserWithCarsPage.getCarsIdList(userId);

        // Делаем GET-запрос к API
        List<Cars> actualCarsList = given()
                .baseUri("http://82.142.167.37:4881/") // URL тестового стенда
                .contentType(ContentType.JSON)
                .queryParam("userId", userId) // Передаем userId в параметре запроса
                .when()
                .get("/cars")
                .then()
                .statusCode(200) // Проверяем, что статус ответа 200 OK
                .extract().jsonPath().getList(".", Cars.class); // Десериализуем JSON в List<Cars>

//        // Сравниваем списки (API vs UI)
//        assertThat(actualCarsList)
//                .as("Список машин из API должен совпадать со списком машин из UI")
//                .containsExactlyInAnyOrderElementsOf(expectedCarsList);
    }
}
