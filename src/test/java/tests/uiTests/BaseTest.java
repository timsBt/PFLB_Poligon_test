package tests.uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AllDeletePage;
import pages.CheckInCheckOutPage;
import pages.CreateUser;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.PropertyReader.getProperty;

public class BaseTest {

    MainPage mainPage;
    CreateUser createUser;
    AllDeletePage allDeletePage;
    CheckInCheckOutPage checkInCheckOutPage;

    @BeforeMethod
    public void setUp() {
        Configuration.headless = false;
        Configuration.timeout = 10000;
        open(System.getProperty("url", getProperty("url")));
        getWebDriver().manage().window().maximize();
        mainPage = new MainPage();
        createUser = new CreateUser();
        allDeletePage = new AllDeletePage();
        checkInCheckOutPage = new CheckInCheckOutPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
