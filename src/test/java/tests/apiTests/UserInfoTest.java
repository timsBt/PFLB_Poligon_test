package tests.apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.carModels.CarDto;
import models.userModels.UserDto;
import models.userModels.UserInfoDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.RestService;

import static adapters.AuthAdapter.getToken;
import static adapters.CarAdapter.createCar;
import static adapters.UserAdapter.createUser;
import static adapters.UserAdapter.getUserInfo;
import static service.OperationWithCar.BUY;
import static utils.PropertyReader.getProperty;

@Epic("Api tests")
public class UserInfoTest {

    private String userId;
    private String carId;
    UserDto userDto;
    SoftAssert softAssert = new SoftAssert();
    RestService restService = new RestService();

    @BeforeMethod
    public void setUpCreateUser() {
        userId = createUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        carId = createCar(
                getProperty("engineType"),
                getProperty("mark"),
                getProperty("model"),
                getProperty("price"));
        userDto = restService.buyOrSellCar(userId, carId, BUY, getToken());
    }

    @Test(testName = "Проверка информации по ID пользователя",
            description = "Проверка информации по ID пользователя")
    @Description("Проверка информации по ID пользователя")
    @Feature("Проверка Информации по пользователю")
    @Story("Проверка денег, и машины у пользователя")
    public void getUserInfoTest() {
        UserInfoDto userInfoDto = getUserInfo(userId);
        softAssert.assertEquals(String.valueOf(userInfoDto.getMoney()), "190000.0", "Значение money не совпадает");
        for (CarDto car : userInfoDto.cars) {
            softAssert.assertEquals(String.valueOf(car.getId()), carId, "Значение carId не совпадает");
            softAssert.assertEquals(car.getEngineType(), getProperty("engineType"), "Значение EngineType не совпадает");
            softAssert.assertEquals(car.getMark(), getProperty("mark"), "Значение Mark не совпадает");
            softAssert.assertEquals(car.getModel(), getProperty("model"), "Значение Model не совпадает");
            softAssert.assertEquals(String.valueOf(car.getPrice()), getProperty("price"), "Значение Price не совпадает");
        }
        softAssert.assertAll();
    }
}
