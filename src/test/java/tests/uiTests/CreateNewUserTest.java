package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static utils.PropertyReader.getProperty;

@Epic("UI tests")
public class CreateNewUserTest extends BaseTest {

    String userId = "";

    @BeforeMethod
    public void openCreateUserPage() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Create new");
    }

    @Test(testName = "Проверка создания пользователя с корректными данными",
            description = "Проверка создания пользователя с корректными данными")
    @Description("Проверка создания пользователя с корректными данными")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на создание нового пользователя")
    public void createUserTest() {
        userId = createUser.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        createUser.createStatus.shouldHave(exactText("Status: Successfully pushed, code: 201"));
    }

    @Test(testName = "Проверка отображения созданного пользователя в таблице Read all",
            description = "Проверка отображения созданного пользователя в таблице Read all")
    @Description("Проверка отображения созданного пользователя в таблице Read all")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на создание нового пользователя")
    public void checkUserOnTableTest() {
        userId = createUser.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        mainPage.toggleNavigationClick("Users")
                .selectDropDownMenu("Read all");
        readAll.getFieldsUserOnTableList(userId).shouldHave(exactTexts(
                userId,
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money")));
    }

    @Test(testName = "Проверка удаления пользователя с корректным ID",
            description = "Проверка удаления пользователя с корректным ID")
    @Description("Проверка удаления пользователя с корректным ID")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на удаление пользователя")
    public void deleteUserTest() {
        userId = createUser.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        allDeletePage.deleteUserId(userId);
        allDeletePage.deleteUserStatus.shouldHave(exactText("Status: 204"));
    }

    @Test(testName = "Проверка удаления пользователя с Некорректным ID",
            description = "Проверка удаления пользователя с Некорректным ID")
    @Description("Проверка удаления пользователя с Некорректным ID")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на удаление пользователя")
    public void deleteNonExistUserTest() {
        userId = getProperty("notExistentID");
        allDeletePage.deleteUserId(userId);
        allDeletePage.notDeleteUserStatus.shouldHave(exactText("Status: not pushed"));
    }

    @Test(testName = "Проверка отсутствия создания пользователя с Некорректными данными",
            description = "Проверка отсутствия создания пользователя с Некорректными данными",
            dataProvider = "CreateUserFalseData")
    @Description("Проверка отсутствия создания пользователя с Некорректными данными")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на отсутствие результата создания пользователя")
    public void checkNotCreatedUserTest(String firstName, String lastName, String age, String sex, String money, String actualResults) {
        userId = createUser.createNewUser(firstName, lastName, age, sex, money);
        createUser.createStatus.shouldHave(exactText(actualResults));
    }

    @DataProvider(name = "CreateUserFalseData")
    public Object[][] createUserFalseData() {
        return new Object[][]{
                {getProperty("firstName"), getProperty("lastName"), getProperty("age"), getProperty("sex"), "",
                        "Status: Invalid request data"},
                {getProperty("firstName"), getProperty("lastName"), getProperty("age"), "", getProperty("money"),
                        "Status: Invalid request data"},
                {getProperty("firstName"), getProperty("lastName"), "", getProperty("sex"), getProperty("money"),
                        "Status: Invalid request data"},
                {getProperty("firstName"), "", getProperty("age"), getProperty("sex"), getProperty("money"),
                        "Status: Invalid request data"},
                {"", getProperty("lastName"), getProperty("age"), getProperty("sex"), getProperty("money"),
                        "Status: Invalid request data"},
                {getProperty("firstName"), getProperty("lastName"), "0", getProperty("sex"), getProperty("money"),
                        "Status: Invalid request data"},
                {getProperty("firstName"), getProperty("lastName"), "-1", getProperty("sex"), getProperty("money"),
                        "Status: Invalid request data"},
                {"111", getProperty("lastName"), getProperty("age"), getProperty("sex"), getProperty("money"),
                        "Status: AxiosError: Request failed with status code 400"},
                {getProperty("firstName"), "111", getProperty("age"), getProperty("sex"), getProperty("money"),
                        "Status: AxiosError: Request failed with status code 400"},
        };
    }

    @AfterMethod
    public void deleteUser() {
        if (!userId.isEmpty() && !userId.equals(getProperty("notExistentID"))) {
            allDeletePage.deleteUserId(userId);
            allDeletePage.deleteUserStatus.shouldHave(text("204"));
        }
    }
}
