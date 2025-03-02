package tests.uiTests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.PropertyReader.getProperty;

public class BaseTest {
    public static String url = System.getProperty("url", getProperty("url"));
    public static String login = System.getProperty("login", getProperty("login"));
    public static String password = System.getProperty("password", getProperty("password"));
    MainPage mainPage;
    CreateUserPage createUserPage;
    AllDeletePage allDeletePage;
    PlusMoneyPage plusMoneyPage;
    CheckInCheckOutPage checkInCheckOutPage;
    CreateCarPage createCarPage;
    ReadAll readAll;
    CreateHousePage createHousePage;
    SellAndBuyCarPage sellAndBuyCarPage;
    ReadUserWithCarsPage readUserWithCarsPage;
    ReadOneHousePage readOneHousePage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("Chrome") String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            Configuration.browser = "Chrome";
        } else if (browser.equalsIgnoreCase("Edge")) {
            Configuration.browser = "Edge";
        }
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.reportsFolder = "./target/screenshots";
        open(url);
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        mainPage = new MainPage();
        createUserPage = new CreateUserPage();
        allDeletePage = new AllDeletePage();
        plusMoneyPage = new PlusMoneyPage();
        checkInCheckOutPage = new CheckInCheckOutPage();
        readAll = new ReadAll();
        createHousePage = new CreateHousePage();
        createCarPage = new CreateCarPage();
        sellAndBuyCarPage = new SellAndBuyCarPage();
        readUserWithCarsPage = new ReadUserWithCarsPage();
        readOneHousePage = new ReadOneHousePage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getWebDriver() != null) {
            closeWebDriver();
        }
    }
}
