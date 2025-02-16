package tests.uiTests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.PlusMoneyPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.*;

public class PlusMoneyTest extends BaseTest{
    private PlusMoneyPage plusMoneyPage;

    @BeforeMethod
    public void setUp() {

        open("http://82.142.167.37:4881/");
        $("input[name='email']").setValue("user@pflb.ru");
        $("input[name='password']").setValue("user");
        $("button[class='Nav-btn btn btn-primary']").click();
        switchTo().alert().accept();
        $("#basic-nav-dropdown").click();
        $("a[href='#/update/users/plusMoney']").click();
        plusMoneyPage = new PlusMoneyPage();
    }

    @Test
    public void testAddMoneyToUser() {
        plusMoneyPage.enterUserId("2180");
        plusMoneyPage.enterAmount("1000");
        plusMoneyPage.submit();
        plusMoneyPage.verifySuccessMessage();
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
