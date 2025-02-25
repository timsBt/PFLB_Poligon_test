package tests.apiTests;

import adapters.UserAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.UserDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static adapters.UserAdapter.*;
import static utils.PropertyReader.getProperty;

@Epic("Api tests")
public class UserApiTest {

    public static String userId;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public static void setUpCreateUser() {
        userId = UserAdapter.createUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));       // создание юзера и возврат id
    }

    @Test(testName = "CRUD юзера")
    @Story("Создание, чтение, изменение, удаление пользователя")
    @Description("Создание, чтение, изменение, удаление пользователя")
    @Feature("Создание, чтение, изменение, удаление пользователя")
    public void workFlowTest() {
        readUser(userId);                    // проверка, что сущность возвращается, статус код 200
        updateUser(userId,
                getProperty("firstName") + " Ivan",
                getProperty("lastName") + " Ivanov",
                getProperty("age"),
                getProperty("sex"),
                getProperty("money") + "5");  //Обновление юзера
        UserDto userDto = readUser(userId);
        // Проверка пользователя после Update
        softAssert.assertEquals(userDto.getFirstName(), getProperty("firstName") + " Ivan", "Значения firstName не совпадают");
        softAssert.assertEquals(userDto.getSecondName(), getProperty("lastName") + " Ivanov", "Значения lastName не совпадают");
        softAssert.assertEquals(userDto.getMoney(), Double.parseDouble(getProperty("money") + "5"), "Значения money не совпадают");
        softAssert.assertAll();
        deleteUser(userId);                 // удаление юзера
        verifyEntityNotExists(userId);      //проверка, что пользователя не существует, возвращается статус код 204
    }

    @Test(testName = "Проверка получения атрибутов по ID пользователя",
            description = "Проверка получения атрибутов по ID пользователя")
    @Description("Проверка получения атрибутов по ID пользователя")
    @Feature("Проверка получения атрибутов пользователя")
    @Story("Получения атрибутов по ID пользователя")
    public void getUserTest() {
        UserDto userDto = readUser(userId);
        softAssert.assertEquals(String.valueOf(userDto.getId()), userId, "Значения userId не совпадают");
        softAssert.assertEquals(userDto.getFirstName(), getProperty("firstName"), "Значения firstName не совпадают");
        softAssert.assertEquals(userDto.getSecondName(), getProperty("lastName"), "Значения lastName не совпадают");
        softAssert.assertEquals(String.valueOf(userDto.getAge()), getProperty("age"), "Значения age не совпадают");
        softAssert.assertEquals(userDto.getSex(), getProperty("sex"), "Значения sex не совпадают");
        softAssert.assertEquals(userDto.getMoney(), Double.parseDouble(getProperty("money")), "Значения money не совпадают");
        softAssert.assertAll();
        deleteUser(userId);
    }
}
