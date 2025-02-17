package tests.uiTests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import static pages.MainPage.checkText;

public class LoginTest extends BaseTest {

    @Test(priority = 1, testName = "Тест авторизации с корректными данными")
    @Description("Тест авторизации с корректными данными")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    @Flaky
    public void correctLogin(){
        mainPage.authAndValidate(mainPage.login, mainPage.password,"Successful authorization");
    }

    @Test(priority = 2, testName = "Тест авторизации с пустыми полями")
    @Description("Тест авторизации с пустыми полями")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void fieldsEmptyLogin(){
        mainPage.authAndValidate("", "","Incorrect input data");
    }

    @Test(priority = 3, testName = "Тест авторизации с неверным паролем")
    @Description("Тест авторизации с неверным паролем")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void fieldPasswordWrongLogin(){
        mainPage.authAndValidate(mainPage.login, mainPage.password+mainPage.password,"Bad request");
    }

    @Test(priority = 4, testName = "Тест авторизации с пустым паролем")
    @Description("Тест авторизации с пустым паролем")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void fieldPasswordEmptyLogin(){
        mainPage.authAndValidate(mainPage.login, "","Incorrect input data");
    }

    @Test(priority = 5, testName = "Тест авторизации с email вне формата")
    @Description("Тест авторизации с email вне формата")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void incorrectLogin(){
        mainPage.emailField.setValue("u");
        mainPage.passwordField.setValue("user");
        mainPage.goButton.click();
        checkText("Incorrect input data", Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        String str = Selenide.$x(String.format(mainPage.redText, "incorrect Email")).getText();
        if (!str.isEmpty()){
            mainPage.checkRedText(str);
        } else  Selenide.closeWebDriver();
    }

    @Test(priority = 6, testName = "Тест авторизации с паролем вне формата")
    @Description("Тест авторизации с паролем вне формата")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void incorrectPassword(){
        mainPage.emailField.setValue(mainPage.login);
        mainPage.passwordField.setValue("u");
        mainPage.goButton.click();
        checkText("Incorrect input data", Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        String str = Selenide.$x(String.format(mainPage.redText, "password length must be more than 3 symbols and less than 8 symbols")).getText();
        if (!str.isEmpty()){
            mainPage.checkRedText(str);
        } else  Selenide.closeWebDriver();
    }

    @Test(priority = 7, testName = "Тест авторизации с почтой и паролем вне формата")
    @Description("Тест авторизации с почтой и паролем вне формата")
    @Feature("Авторизация")
    @Story("Предоставление прав для работы с проектом")
    public void incorrectPasswordAndEmail(){
        mainPage.emailField.setValue(mainPage.password);
        mainPage.passwordField.setValue(mainPage.password+mainPage.password+mainPage.password);
        mainPage.goButton.click();
        checkText("Incorrect input data", Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        String str = Selenide.$x(String.format(mainPage.redText, "incorrect Email")).getText();
        if (!str.isEmpty()){
            mainPage.checkRedText(str);
        }
        String str2 = Selenide.$x(String.format(mainPage.redText, "password length must be more than 3 symbols and less than 8 symbols")).getText();
        if (!str2.isEmpty()){
            mainPage.checkRedText(str2);
        }
    }
}
