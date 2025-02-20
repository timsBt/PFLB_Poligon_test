package tests.apiTests;

import adapters.UserAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static adapters.UserAdapter.*;

@Epic("Api tests")
public class UserApiTest {

    @Test(testName = "CRUD юзера")
    @Story("Создание, чтение, изменение, удаление пользователя")
    @Description("Создание, чтение, изменение, удаление пользователя")
    @Feature("Создание, чтение, изменение, удаление пользователя")
    public void workFlowTest() {
        String name = "Ivan";
        String lastName = "Ivanov";
        String age = "19";
        String sex = "MALE";
        String money = "100";
        String id = UserAdapter.createUser(name, lastName, age, sex, money); // создание юзера и возврат id

        readUser(id); // проверка, что сущность возвращается, статус код 200
        updateUser(id, name + name, lastName + lastName, age, sex, money + money);  //Обновление юзера
        deleteUser(id); // удаление юзера
        verifyEntityNotExists(id); //проверка, что пользователя не существует, возвращается статус код 204
    }
}
