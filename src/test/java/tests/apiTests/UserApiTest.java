package tests.apiTests;

import adapters.UserAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static adapters.UserAdapter.*;
import static utils.PropertyReader.getProperty;

@Epic("Api tests")
public class UserApiTest {

    public static String userId;

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
        deleteUser(userId);                 // удаление юзера
        verifyEntityNotExists(userId);      //проверка, что пользователя не существует, возвращается статус код 204
    }
}
