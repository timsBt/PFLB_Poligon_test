package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.carsData.SellingCar;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;

@Epic("UI tests")
public class SellAndBuyCarTest extends BaseTest {

    @BeforeMethod
    public void openCreateUserPage() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Read user with cars");
    }

    @Test(testName = "Проверка покупки машины",
            description = "Проверка покупки машины")
    @Description("Проверка покупки машины")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на покупку машины по userID и CarID")
    public void buyCarTest() {
        final SellingCar car = readUserWithCarsPage.readUserWithCars();
        mainPage.toggleNavigationClick("Cars")
                .selectDropDownMenu("Buy or sell car");
        sellAndBuyCarPage.buyCar(car.getUserId(), car.getCarId())
                .carCreateStatus();
        sellAndBuyCarPage.saleStatus.shouldHave(exactText("Status: Successfully pushed, code: 200"));
    }

    @Test(testName = "Проверка продажи машины",
            description = "Проверка продажи машины")
    @Description("Проверка продажи машины")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на продажу машины по userID и CarID")
    public void sellCarTest() {
        final SellingCar car = readUserWithCarsPage.readUserWithCars();
        mainPage.toggleNavigationClick("Cars")
                .selectDropDownMenu("Buy or sell car");
        sellAndBuyCarPage.sellCar(car.getUserId(), car.getCarId())
                .carCreateStatus();
        sellAndBuyCarPage.saleStatus.shouldHave(exactText("Status: Successfully pushed, code: 200"));
    }
}
