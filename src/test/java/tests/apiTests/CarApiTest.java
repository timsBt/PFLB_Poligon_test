package tests.apiTests;

import adapters.CarAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.carModels.CarDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static adapters.CarAdapter.*;
import static utils.PropertyReader.getProperty;

@Epic("Api tests")
public class CarApiTest {

    private static String carId;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public static void setUpCreateCar() {
        carId = CarAdapter.createCar(
                getProperty("engineType"),
                getProperty("mark"),
                getProperty("model"),
                getProperty("price"));       // создание автомобиля и возврат id
    }

    @Test(testName = "CRUD автомобиля")
    @Story("Создание, чтение, изменение, удаление автомобиля")
    @Description("Создание, чтение, изменение, удаление автомобиля")
    @Feature("Создание, чтение, изменение, удаление автомобиля")
    public void CarCRUDTest() {
        readCar(carId);                    // проверка, что сущность возвращается, статус код 200
        double currentPrice = Double.parseDouble(getProperty("price")); // Преобразование строки в число
        double newPrice = currentPrice + 100.00;
        updateCar(carId,
                "Gasoline",
                getProperty("mark") + "Tachka",
                getProperty("model") + "Cyber",
                String.valueOf(newPrice)); // Обновляем автомобиль с новой ценой
        CarDto carDto = readCar(carId);
        // Проверка автомобиля после Update
        softAssert.assertEquals(carDto.getEngineType(), "Gasoline", "Значения engineType не совпадают");
        softAssert.assertEquals(carDto.getMark(), getProperty("mark") + "Tachka", "Значения mark не совпадают");
        softAssert.assertEquals(carDto.getModel(), getProperty("model") + "Cyber", "Значения model не совпадают");
        softAssert.assertEquals(carDto.getPrice(), newPrice, "Значения price не совпадают");
        softAssert.assertAll();
        deleteCar(carId);                 // удаление автомобиля
        verifyCarEntityNotExists(carId);      //проверка, что автомобиля не существует, возвращается статус код 204
    }

    @Test(testName = "Проверка получения атрибутов по ID автомобиля",
            description = "Проверка получения атрибутов по ID автомобиля")
    @Description("Проверка получения атрибутов по ID автомобиля")
    @Feature("Проверка получения атрибутов автомобиля")
    @Story("Получения атрибутов по ID автомобиля")
    public void getCarTest() {
        CarDto carDto = readCar(carId);
        softAssert.assertEquals(String.valueOf(carDto.getId()), carId, "Значения carId не совпадают");
        softAssert.assertEquals(carDto.getEngineType(), getProperty("engineType"), "Значения engineType не совпадают");
        softAssert.assertEquals(carDto.getMark(), getProperty("mark"), "Значения mark не совпадают");
        softAssert.assertEquals(carDto.getModel(), getProperty("model"), "Значения model не совпадают");
        softAssert.assertEquals(carDto.getPrice(), Double.parseDouble(getProperty("price")), "Значения price не совпадают");
        softAssert.assertAll();
        deleteCar(carId);
    }
}
