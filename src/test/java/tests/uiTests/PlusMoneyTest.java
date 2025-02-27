package tests.uiTests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PlusMoneyPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static utils.PropertyReader.getProperty;
@Epic("UI tests")
public class PlusMoneyTest extends BaseTest {
    String userId;

    @BeforeMethod
    @Description ("Открытие формы авторизации, ввод логина и пароля, создание пользователя, " +
            "переход на станицу Add money")
    public void openFunctionAddMoney() {
        mainPage.authorization()
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Create new");
        userId = createUser.createNewUser(getProperty("firstName"), getProperty("lastName"),
                getProperty("age"), getProperty("sex"), getProperty("money"));
        mainPage.toggleNavigationClick("Users")
                .selectDropDownMenu("Add money");
    }

    @Test(priority = 1, testName = "Проверка на добавление денег пользователю.",
            description = "Ввод корректного id и суммы.")
    public void testAddMoneyToUser() {
        plusMoneyPage.enterUserId(userId).enterAmount("1000").submit()
                .verifySuccessMessage("Status: Successfully pushed, code: 200");
    }

    @Test(priority = 2, testName = "Проверка на добавление денег несуществующему пользователю.",
            description = "Ввод некорректного id и суммы.")
    public void testAddMoneyNonExistentUser() {
        plusMoneyPage.enterUserId("2180000").enterAmount("1000").submit()
                .verifySuccessMessage("Status: AxiosError: Request failed with status code 404");
    }

    @Test(priority = 3, testName = "Проверка на ввод некорректной суммы.",
            description = "Ввод корректного id и отрицательной суммы.")
    public void testEnteringIncorrectAmount() {
        plusMoneyPage.enterUserId(userId).enterAmount("-1000").submit()
                .verifySuccessMessage("Status: Incorrect input data");
    }

    @AfterMethod
    @Description ("Удаление пользователя")
    public void deleteUser() {
        allDeletePage.deleteUserId(userId);
        allDeletePage.deleteUserStatus.shouldHave(text("Status: 204"));
    }
}
