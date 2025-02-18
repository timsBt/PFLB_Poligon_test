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
        open("http://82.142.167.37:4881/");
        mainPage = new MainPage();
        mainPage.authorization()
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Add money");
        plusMoneyPage = new PlusMoneyPage();
    }

    @Test(priority = 1, testName = "Проверка на добавление денег пользователю.")
    public void testAddMoneyToUser() {
        plusMoneyPage.enterUserId("2180").enterAmount("1000").submit()
                .verifySuccessMessage("Status: Successfully pushed, code: 200");
    }

    @Test(priority = 1, testName = "Проверка на добавление денег несуществующему пользователю.")
    public void testAddMoneyNonExistentUser() {
        plusMoneyPage.enterUserId("2180000").enterAmount("1000").submit()
                .verifySuccessMessage("Status: AxiosError: Request failed with status code 404");
    }

    @Test(priority = 1, testName = "Проверка на ввод некорректной суммы.")
    public void testEnteringIncorrectAmount() {
        plusMoneyPage.enterUserId("2180").enterAmount("-1000").submit()
                .verifySuccessMessage("Status: Incorrect input data");
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
