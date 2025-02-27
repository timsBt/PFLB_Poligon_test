package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static java.time.Duration.ofSeconds;
import static utils.PropertyReader.getProperty;

@Epic("UI tests")
public class CreateNewUserTest extends BaseTest {

    String userId;

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
        userId = createUserPage.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        createUserPage.successStatus.shouldBe(visible, ofSeconds(10)).shouldHave(text("Status: Successfully pushed, code: 201"));
    }

    @Test(testName = "Проверка отображения созданного пользователя в таблице Read all",
            description = "Проверка отображения созданного пользователя в таблице Read all")
    @Description("Проверка отображения созданного пользователя в таблице Read all")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на создание нового пользователя")
    public void checkUserOnTableTest() {
        userId = createUserPage.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        mainPage.toggleNavigationClick("Users")
                .selectDropDownMenu("Read all");
        readAll.sortButton.doubleClick();
        readAll.getFieldsUserOnTableList(userId).shouldHave(texts(
                userId,
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money")));
    }

    @Test(testName = "Проверка удаления пользователя с корректным ID",
            description = "Проверка удаления пользователя с корректным ID", enabled = false)
    @Description("Проверка удаления пользователя с корректным ID")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на удаление пользователя")
    public void deleteUserTest() {
        userId = createUserPage.createNewUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        allDeletePage.deleteUserId(userId);
        allDeletePage.deleteUserStatus.shouldBe(visible, ofSeconds(10)).shouldHave(text("Status: 204"));
    }

    @Test(testName = "Проверка удаления пользователя с Некорректным ID",
            description = "Проверка удаления пользователя с Некорректным ID")
    @Description("Проверка удаления пользователя с Некорректным ID")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на удаление пользователя")
    public void deleteNonExistUserTest() {
        userId = getProperty("notExistentID");
        allDeletePage.deleteUserId(getProperty("notExistentID"));
        allDeletePage.notPushedStatus.shouldBe(visible, ofSeconds(10)).shouldHave(text("Status: not pushed"));
    }

    @Test(testName = "Проверка отсутствия создания пользователя с Некорректными данными",
            description = "Проверка отсутствия создания пользователя с Некорректными данными",
            dataProvider = "CreateUserFalseData")
    @Description("Проверка отсутствия создания пользователя с Некорректными данными")
    @Feature("Взаимодействие с пользователем")
    @Story("Проверка на отсутствие результата создания пользователя")
    public void checkNotCreatedUserTest(String firstName, String lastName, String age, String sex, String money,
                                        String actualResults) {
        userId = createUserPage.createNewUser(firstName, lastName, age, sex, money);
        createUserPage.invalidStatus.shouldBe(visible, ofSeconds(10)).shouldHave(text(actualResults));
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
                        "Status: Invalid request data"}
        };
    }

    @AfterMethod
    public void deleteUser() {
        if (userId != null && !userId.isEmpty() && !userId.equals(getProperty("notExistentID"))) {
            allDeletePage.deleteUserId(userId);
        }
    }
}
