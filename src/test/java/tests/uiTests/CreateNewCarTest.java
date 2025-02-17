package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.RetryUtils;

import static com.codeborne.selenide.Condition.text;

@Epic("UI tests")
public class CreateNewCarTest extends BaseTest {

    String carId = "";
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void createCarPreparation() {
        mainPage.authorization()
                .toggleNavigationClick("Cars")
                .selectDropDownMenu("Create new");
    }

    @Test(priority = 1, testName = "Тест создания автомобиля с валидными данными", retryAnalyzer = RetryUtils.class)
    @Description("Тест создания автомобиля с валидными данными")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithValidData() {
        createCar.createNewCar("Gasoline",
                "VedroS",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Successfully pushed, code: 201",
                "Возникла ошибка при создании автомобиля");
        carId = createCar.carIdGet();
    }

    @Test(priority = 1, testName = "Тест удаления созданного автомобиля", retryAnalyzer = RetryUtils.class)
    @Description("Тест удаления созданного автомобиля")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void deleteNewCarWithValidData() {
        createCar.createNewCar("Gasoline",
                "VedroS",
                "Gaykamy",
                "1000");
        carId = createCar.carIdGet();
        allDeletePage.deleteCarId(carId);
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: 204",
                "Возникла ошибка при удалении автомобиля");
    }

    @Test(priority = 2, testName = "Тест создания автомобиля с пустым полем Engine Type", retryAnalyzer = RetryUtils.class)
    @Description("Тест создания автомобиля с пустым полем Engine Type")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyEngineTypeField() {
        createCar.createNewCar("",
                "VedroS",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 3, testName = "Тест создания автомобиля с не валидными данными в поле Engine Type", retryAnalyzer = RetryUtils.class)
    @Description("Тест создания автомобиля с не валидными данными в поле Engine Type")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithNotValidEngineTypeField() {
        createCar.createNewCar("sdgwegwge",
                "VedroS",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: AxiosError: Request failed with status code 400",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 4, testName = "Тест создания автомобиля с пустым полем Mark", retryAnalyzer = RetryUtils.class)
    @Description("Тест создания автомобиля с пустым полем Mark")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyMarkField() {
        createCar.createNewCar("Electric",
                "",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 5, testName = "Тест создания автомобиля с пустым полем Model", retryAnalyzer = RetryUtils.class)
    @Description("Тест создания автомобиля с пустым полем Model")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyModelField() {
        createCar.createNewCar("Diesel",
                "VedroS",
                "",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 6, testName = "Тест создания автомобиля с пустым полем Price", retryAnalyzer = RetryUtils.class)
    @Description("Тест создания автомобиля с пустым полем Price")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyPriceField() {
        createCar.createNewCar("CNG",
                "VedroS",
                "Gaykamy",
                "");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @AfterMethod
    public void deleteCar() {
        if (!carId.isEmpty() && !carId.equals("9999")) {
            allDeletePage.deleteCarId(carId);
            allDeletePage.deleteCarStatus.shouldHave(text("204"));
        }
    }
}
