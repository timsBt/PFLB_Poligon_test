package tests.uiTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.RetryUtils;

import static com.codeborne.selenide.Condition.text;

public class CreateNewCarTest extends BaseTest {

    String carId = "";
    String carStatusError = "";
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void createCarPreparation() {
        mainPage.authorization()
                .toggleNavigationClick("Cars")
                .selectDropDownMenu("Create new");
    }

    @Test(priority = 1, testName = "Тест создания автомобиля с валидными данными", retryAnalyzer = RetryUtils.class)
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

    @Test(priority = 2, testName = "Тест создания автомобиля с пустым полем Engine Type", retryAnalyzer = RetryUtils.class)
    public void createNewCarWithEmptyEngineTypeField() {
        createCar.createNewCar("",
                "VedroS",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
        carStatusError = createCar.carCreateStatus();
    }

    @Test(priority = 3, testName = "Тест создания автомобиля с не валидными данными в поле Engine Type", retryAnalyzer = RetryUtils.class)
    public void createNewCarWithNotValidEngineTypeField() {
        createCar.createNewCar("sdgwegwge",
                "VedroS",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: AxiosError: Request failed with status code 400",
                "Сообщение об ошибке отсутствует");
        carStatusError = createCar.carCreateStatus();
    }

    @Test(priority = 4, testName = "Тест создания автомобиля с пустым полем Mark", retryAnalyzer = RetryUtils.class)
    public void createNewCarWithEmptyMarkField() {
        createCar.createNewCar("Electric",
                "",
                "Gaykamy",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
        carStatusError = createCar.carCreateStatus();
    }

    @Test(priority = 5, testName = "Тест создания автомобиля с пустым полем Model", retryAnalyzer = RetryUtils.class)
    public void createNewCarWithEmptyModelField() {
        createCar.createNewCar("Diesel",
                "VedroS",
                "",
                "1000");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
        carStatusError = createCar.carCreateStatus();
    }

    @Test(priority = 6, testName = "Тест создания автомобиля с пустым полем Price", retryAnalyzer = RetryUtils.class)
    public void createNewCarWithEmptyPriceField() {
        createCar.createNewCar("CNG",
                "VedroS",
                "Gaykamy",
                "");
        softAssert.assertEquals(createCar.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
        carStatusError = createCar.carCreateStatus();
    }

    @AfterMethod
    public void deleteCar() {
        if ("Status: Invalid request data".equals(carStatusError) ||
                "Status: AxiosError: Request failed with status code 400".equals(carStatusError)) {
            return; // Прерываем выполнение метода, если carStatusError равно одной из указанных строк
        }
        allDeletePage.deleteCarId(carId);
        allDeletePage.deleteCarStatus.shouldHave(text("Status: 204"));
    }
}
