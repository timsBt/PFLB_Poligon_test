package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static org.testng.Assert.assertEquals;
import static utils.PropertyReader.getProperty;

@Epic("UI tests")
public class SellAndBuyCarTest extends BaseTest {
    private String userId;
    private String carId;

    @BeforeMethod
    public void openCreateUserPage() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Create new");
    }

    @Test(testName = "Проверка покупки машины",
            description = "Проверка покупки машины")
    @Description("Проверка покупки машины")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на покупку машины по userID и CarID")
    public void buyCarTest() {
        // Создаем пользователя
        userId = createUserPage.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        createUserPage.checkStatus("Status: Successfully pushed, code: 201");
        mainPage.toggleNavigationClick("Cars")
                .selectDropDownMenu("Create new");
        // Создаем машину
        carId = createCarPage.createNewCar(
                "Gasoline",
                "VedroS",
                "Gaykamy",
                "1000");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: Successfully pushed, code: 201",
                "Ошибка при создании автомобиля");
        // Покупаем машину
        mainPage.toggleNavigationClick("Cars")
                .selectDropDownMenu("Buy or sell car");
        sellAndBuyCarPage.buyCar(userId, carId)
                .carCreateStatus();
        sellAndBuyCarPage.saleStatus.shouldHave(exactText("Status: Successfully pushed, code: 200"));
        // Продаем машину
        sellAndBuyCarPage.sellCar(userId, carId)
                .carCreateStatus();
        sellAndBuyCarPage.saleStatus.shouldHave(exactText("Status: Successfully pushed, code: 200"));
    }
}
