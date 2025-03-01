package tests.uiTests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static pages.MainPage.checkText;

@Epic("UI tests")
public class LoginTest extends BaseTest {

    @Test(priority = 1, testName = "Тест авторизации с корректными данными")
    @Description("Тест авторизации с корректными данными")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    @Flaky
    public void correctLogin() {
        mainPage.authAndValidate(login, password, "Successful authorization");
    }

    @Test(priority = 2, testName = "Тест авторизации с пустыми полями")
    @Description("Тест авторизации с пустыми полями")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void fieldsEmptyLogin() {
        mainPage.authAndValidate("", "", "Incorrect input data");
    }

    @Test(priority = 3, testName = "Тест авторизации с неверным паролем")
    @Description("Тест авторизации с неверным паролем")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void fieldPasswordWrongLogin() {
        mainPage.authAndValidate(login, password + password, "Bad request");
    }

    @Test(priority = 4, testName = "Тест авторизации с пустым паролем")
    @Description("Тест авторизации с пустым паролем")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void fieldPasswordEmptyLogin() {
        mainPage.authAndValidate(login, "", "Incorrect input data");
    }

    @Test(priority = 5, testName = "Тест авторизации с email вне формата")
    @Description("Тест авторизации с email вне формата")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void incorrectLogin() {
        mainPage.authorizationWithValidateAlert("u", "user");
        checkText("Incorrect input data", Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        mainPage.checkResult("incorrect Email");
    }

    @Test(priority = 6, testName = "Тест авторизации с паролем вне формата")
    @Description("Тест авторизации с паролем вне формата")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void incorrectPassword() {
        mainPage.authorizationWithValidateAlert(login, "u");
        checkText("Incorrect input data", Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        mainPage.checkResult("password length must be more than 3 symbols and less than 8 symbols");
    }

    @Test(priority = 7, testName = "Тест авторизации с почтой и паролем вне формата")
    @Description("Тест авторизации с почтой и паролем вне формата")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void incorrectPasswordAndEmail() {
        mainPage.authorizationWithValidateAlert(password, password+password);
        checkText("Incorrect input data", Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        mainPage.checkResult("incorrect Email");
        mainPage.checkResult("password length must be more than 3 symbols and less than 8 symbols");
    }
}
