package tests.apiTests;

import adapters.AuthAdapter;
import dto.PersonDto;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.UserDto;
import org.testng.annotations.Test;
import service.OperationWithCar;
import service.RestService;
import tests.uiTests.BaseTest;

public class GetCarsListFromUserApiTest extends BaseTest {

    @Test(testName = "API: Получение списка машин по userId",
            description = "Проверка, что API возвращает список машин")
    @Description("Сравнение списка машин пользователя, полученного из UI, с ответом API")
    @Feature("API-тестирование")
    @Story("GET /cars?userId={userId}")
    public void getCarsListApiTest() {
        RestService restService = new RestService();
        String token = AuthAdapter.getToken();
        PersonDto getListCars = restService.getUserWithCars("1");
        UserDto sellCar =  restService.buyOrSellCar("1", "1", OperationWithCar.BUY, token);
    }
}
