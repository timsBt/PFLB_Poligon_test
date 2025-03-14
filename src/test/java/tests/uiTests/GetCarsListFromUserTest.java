package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.carsData.Cars;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Epic("UI tests")
public class GetCarsListFromUserTest extends BaseTest {

    @BeforeMethod
    public void openCreateUserPage() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Read user with cars");
    }

    @Test(testName = "Проверка списка машин по id юзера",
            description = "Проверка списка машин по id юзера")
    @Description("Проверка списка машин по id юзера")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка списка машин по id юзера")
    public void getCarsList() {
        String userId = readUserWithCarsPage.readUserWithCars().getUserId();
        List<Cars> cars = readUserWithCarsPage.getCarsIdList(userId);
    }

}
