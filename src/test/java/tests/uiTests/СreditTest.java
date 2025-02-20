package tests.uiTests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PlusMoneyPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static utils.PropertyReader.getProperty;
@Epic("UI tests")
public class СreditTest extends BaseTest {

    String userId = "";

    @BeforeMethod
    public void openCredit() {
        mainPage.authorization()
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Create new");
        userId = createUser.createNewUser(getProperty("firstName"), getProperty("lastName"), getProperty("age"),
                getProperty("sex"), getProperty("money"));
        mainPage.toggleNavigationClick("Users")
                .selectDropDownMenu("Issue a loan");
    }

    @Test(priority = 1, testName = "Проверка на взятие кредита.",
            description = "Ввод корректного id и суммы.")
    public void testLoanFailure() {
        plusMoneyPage.enterUserId("2180").enterAmount("100000").submit()
                .verifySuccessMessage("Status: AxiosError: Request failed with status code 408");
    }

    @Test(priority = 2, testName = "Проверка на кредит несуществующему пользователю.",
            description = "Ввод некорректного id и суммы.")
    public void testAddMoneyNonExistentUser() {
        plusMoneyPage.enterUserId("2180000").enterAmount("1000").submit()
                .verifySuccessMessage("Status: AxiosError: Request failed with status code 404");
    }

    @Test(priority = 3, testName = "Проверка на кредит некорректной суммы.",
            description = "Ввод корректного id и отрицательной суммы.")
    public void testEnteringIncorrectAmount() {
        plusMoneyPage.enterUserId("2180").enterAmount("-1000").submit()
                .verifySuccessMessage("Status: Incorrect input data");
    }
    @AfterMethod
    public void deleteUser() {
        allDeletePage.deleteUserId(userId);
        allDeletePage.deleteUserStatus.shouldHave(text("Status: 204"));
    }
}
