package tests.uiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static utils.PropertyReader.getProperty;

@Epic("UI tests")
public class PlusMoneyTest extends BaseTest {

    private String userId;

    @BeforeMethod
    public void openFunctionAddMoney() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Create new");
        userId = createUserPage.createNewUser(getProperty("firstName"), getProperty("lastName"), getProperty("age"),
                getProperty("sex"), getProperty("money"));
        mainPage.toggleNavigationClick("Users")
                .selectDropDownMenu("Add money");
    }

    @Test(priority = 1, testName = "Проверка на добавление денег пользователю.",
            description = "Ввод корректного id и суммы.")
    @Feature("Добавление денег пользователю")
    @Story("Ввод корректного id и суммы.")
    public void testAddMoneyToUser() {
        plusMoneyPage.enterUserId(userId).enterAmount("1000").submit()
                .verifySuccessMessage("Status: Successfully pushed, code: 200");
    }

    @Test(priority = 2, testName = "Проверка на добавление денег несуществующему пользователю.",
            description = "Ввод некорректного id и суммы.")
    @Feature("Добавление денег пользователю")
    @Story("Ввод некорректного id и суммы.")
    public void testAddMoneyNonExistentUser() {
        plusMoneyPage.enterUserId("2180000").enterAmount("1000").submit()
                .verifySuccessMessage("Status: AxiosError: Request failed with status code 404");
    }

    @Test(priority = 3, testName = "Проверка на ввод некорректной суммы.",
            description = "Ввод корректного id и отрицательной суммы.")
    @Feature("Добавление денег пользователю")
    @Story("Ввод корректного id и отрицательной суммы.")
    public void testEnteringIncorrectAmount() {
        plusMoneyPage.enterUserId(userId).enterAmount("-1000").submit()
                .verifySuccessMessage("Status: Incorrect input data");
    }

    @AfterMethod
    public void deleteUser() {
        allDeletePage.deleteUserId(userId);
    }
}
