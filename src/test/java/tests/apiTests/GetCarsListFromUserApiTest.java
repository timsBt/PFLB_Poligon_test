package tests.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.userModels.UserInfoDto;
import org.testng.annotations.Test;
import service.OperationWithCar;
import service.RestService;

public class GetCarsListFromUserApiTest extends BaseClassApiTest {

    @Test(testName = "API: Получение списка машин по userId",
            description = "Проверка, что API возвращает список машин")
    @Description("Сравнение списка машин пользователя")
    @Feature("API-тестирование")
    @Story("GET /cars?userId={userId}")
    public void getCarsListApiTest() {
        RestService restService = new RestService();
        UserInfoDto getListCars = restService.getUserWithCars("1");
        checkUserCars(getListCars, 2);
        UserInfoDto sellCar =  restService.buyOrSellCar("1", "1", OperationWithCar.BUY, token);
    }
}
