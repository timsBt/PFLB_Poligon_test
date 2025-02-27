package tests.apiTests;

import adapters.UserAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.carModels.CarDto;
import models.userModels.UserInfoDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static adapters.UserAdapter.deleteUser;
import static adapters.UserAdapter.getUserInfo;
import static utils.PropertyReader.getProperty;

@Epic("Api tests")
public class UserInfoTest {

    public static String userId;
    ArrayList<String> userInfoAdded = new ArrayList<>();
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUpCreateUser() {
        userId = UserAdapter.createUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        // Тут будет Метод добавления дома юзеру (а перед этим нужно сначало создать дом) и знать сколько денег потратится
        // Тут будет Метод добавления машины юзеру (а перед этим нужно создать машину) и знать сколько денег потратится
        userInfoAdded.add(userId);
//        userInfoAdded.add(houseId);
//        userInfoAdded.add(carId);
    }

    @Test(testName = "Проверка информации по ID пользователя",
            description = "Проверка информации по ID пользователя")
    @Description("Проверка информации по ID пользователя")
    @Feature("Проверка Информации по пользователю")
    @Story("Проверка денег, машины, дома у пользователя")
    public void getUserInfoTest() {
        // Заменить значения после того как будут реализованны методы по добавлению дома и машины пользователю

        UserInfoDto userInfoDto = getUserInfo("2754");
        softAssert.assertEquals(String.valueOf(userInfoDto.getMoney()), "198770.0", "Значение money не совпадает");
        softAssert.assertEquals(String.valueOf(userInfoDto.getHouse()), "9", "Значение house не совпадает");
        for (CarDto car : userInfoDto.cars) {
            softAssert.assertEquals(String.valueOf(car.getId()), "349", "Значение money не совпадает");
            softAssert.assertEquals(car.getEngineType(), "Gasoline", "Значение EngineType не совпадает");
            softAssert.assertEquals(car.getMark(), "VedroS", "Значение Mark не совпадает");
            softAssert.assertEquals(car.getModel(), "Gaykamy", "Значение Model не совпадает");
            softAssert.assertEquals(String.valueOf(car.getPrice()), "1000.0", "Значение Price не совпадает");
        }
        softAssert.assertAll();
        deleteUser(userId);
    }
}
