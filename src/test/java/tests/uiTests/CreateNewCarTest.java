package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Epic("UI tests")
public class CreateNewCarTest extends BaseTest {

    String carId;

    @BeforeMethod
    public void createCarPreparation() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Cars")
                .selectDropDownMenu("Create new");
    }

    @Test(priority = 1, testName = "Тест создания автомобиля с валидными данными",
            description = "Тест создания автомобиля с валидными данными")
    @Description("Тест создания автомобиля с валидными данными")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithValidData() {
        carId = createCarPage.createNewCar(
                "Gasoline",
                "VedroS",
                "Gaykamy",
                "1000");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: Successfully pushed, code: 201",
                "Возникла ошибка при создании автомобиля");
    }

    @Test(priority = 1, testName = "Тест удаления созданного автомобиля",
            description = "Тест удаления созданного автомобиля")
    @Description("Тест удаления созданного автомобиля")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void deleteNewCarWithValidData() {
        String car = createCarPage.createNewCar("Gasoline",
                "VedroS",
                "Gaykamy",
                "1000");
        allDeletePage.deleteCarId(car);
        assertEquals(allDeletePage.deleteStatus.text(),
                "Status: 204",
                "Возникла ошибка при удалении автомобиля");
    }

    @Test(priority = 1, testName = "Тест удаления не существующего автомобиля",
            description = "Тест удаления не существующего автомобиля")
    @Description("Тест удаления не существующего автомобиля")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void deleteNewCarWithNotValidData() {
        allDeletePage.deleteCarId("9999");
        assertEquals(allDeletePage.notPushedStatus.text(), "Status: not pushed");
    }

    @Test(priority = 2, testName = "Тест создания автомобиля с пустым полем Engine Type",
            description = "Тест создания автомобиля с пустым полем Engine Type")
    @Description("Тест создания автомобиля с пустым полем Engine Type")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyEngineTypeField() {
        createCarPage.createNewCar("",
                "VedroS",
                "Gaykamy",
                "1000");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 3, testName = "Тест создания автомобиля с не валидными данными в поле Engine Type",
            description = "Тест создания автомобиля с не валидными данными в поле Engine Type")
    @Description("Тест создания автомобиля с не валидными данными в поле Engine Type")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithNotValidEngineTypeField() {
        createCarPage.createNewCar("sdgwegwge",
                "VedroS",
                "Gaykamy",
                "1000");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: AxiosError: Request failed with status code 400",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 4, testName = "Тест создания автомобиля с пустым полем Mark",
            description = "Тест создания автомобиля с пустым полем Mark")
    @Description("Тест создания автомобиля с пустым полем Mark")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyMarkField() {
        createCarPage.createNewCar("Electric",
                "",
                "Gaykamy",
                "1000");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 5, testName = "Тест создания автомобиля с пустым полем Model",
            description = "Тест создания автомобиля с пустым полем Model")
    @Description("Тест создания автомобиля с пустым полем Model")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyModelField() {
        createCarPage.createNewCar("Diesel",
                "VedroS",
                "",
                "1000");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @Test(priority = 6, testName = "Тест создания автомобиля с пустым полем Price",
            description = "Тест создания автомобиля с пустым полем Price")
    @Description("Тест создания автомобиля с пустым полем Price")
    @Feature("Взаимодействие с автомобилем")
    @Story("Создание нового автомобиля")
    public void createNewCarWithEmptyPriceField() {
        createCarPage.createNewCar("CNG",
                "VedroS",
                "Gaykamy",
                "");
        assertEquals(createCarPage.carCreateStatus(),
                "Status: Invalid request data",
                "Сообщение об ошибке отсутствует");
    }

    @AfterMethod
    public void deleteCar() {
        if (carId != null && !carId.isEmpty()) {
            allDeletePage.deleteUserId(carId);
        }
    }
}
