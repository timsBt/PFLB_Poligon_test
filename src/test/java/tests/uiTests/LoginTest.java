package tests.uiTests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import static pages.MainPage.checkText;

public class LoginTest extends BaseTest {

    @Test(priority = 1, testName = "Тест авторизации с корректными данными")
    public void CorrectLogin(){
        setUp();
        mainPage.authAndValidate(mainPage.login, mainPage.password,"Successful authorization");
    }

    @Test(priority = 2, testName = "Тест авторизации с пустыми полями")
    public void FieldsEmptyLogin(){
        setUp();
        mainPage.authAndValidate("", "","Incorrect input data");
    }

    @Test(priority = 3, testName = "Тест авторизации с неверным паролем")
    public void FieldPasswordWrongLogin(){
        setUp();
        mainPage.authAndValidate(mainPage.login, mainPage.password+mainPage.password,"Bad request");
    }

    @Test(priority = 3, testName = "Тест авторизации с пустым паролем")
    public void FieldPasswordEmptyLogin(){
        setUp();
        mainPage.authAndValidate(mainPage.login, "","Incorrect input data");
    }

    @Test(priority = 4, testName = "Тест авторизации с email вне формата")
    public void incorrectLogin(){
        setUp();
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

    @Test(priority = 5, testName = "Тест авторизации с паролем вне формата")
    public void incorrectPassword(){
        setUp();
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

    @Test(priority = 6, testName = "Тест авторизации с почтой и паролем вне формата")
    public void incorrectPasswordAndEmail(){
        setUp();
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
