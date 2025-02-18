package tests.uiTests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PlusMoneyPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.*;

public class PlusMoneyTest extends BaseTest {
    private PlusMoneyPage plusMoneyPage;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        plusMoneyPage = new PlusMoneyPage();
        mainPage = new MainPage();
        open("http://82.142.167.37:4881/");
        mainPage.authorization()
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Add money");

    }

    @Test(priority = 1, testName = "Проверка на добавление денег пользователю.",
            description = "Ввод корректного id и суммы.")
    public void testAddMoneyToUser() {
        plusMoneyPage.enterUserId("2180").enterAmount("1000").submit()
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
        plusMoneyPage.enterUserId("2180").enterAmount("-1000").submit()
                .verifySuccessMessage("Status: Incorrect input data");
    }


}
