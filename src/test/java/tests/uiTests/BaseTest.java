package tests.uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.PropertyReader.getProperty;

public class BaseTest {
    MainPage mainPage;
    CreateUser createUser;
    AllDeletePage allDeletePage;
    CheckInCheckOutPage checkInCheckOutPage;
    CreateCarPage createCarPage;
    ReadAll readAll;
    SellAndBuyCarPage sellAndBuyCarPage;
    ReadUserWithCarsPage readUserWithCarsPage;


    String url = System.getProperty("url", getProperty("url"));
    String login = System.getProperty("login", getProperty("login"));
    String password = System.getProperty("password", getProperty("password"));

    @BeforeMethod
    public void setUp() {
        Configuration.headless = true;
        Configuration.timeout = 10000;
        open(url);
        getWebDriver().manage().window().maximize();
        mainPage = new MainPage();
        allDeletePage = new AllDeletePage();
        checkInCheckOutPage = new CheckInCheckOutPage();
        readAll = new ReadAll();
        createCarPage = new CreateCarPage();
        sellAndBuyCarPage = new SellAndBuyCarPage();
        readUserWithCarsPage = new ReadUserWithCarsPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
