package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public String redText = "//*[text() = '%s']";
    public SelenideElement emailField = $x("//input[@name = 'email']");
    public SelenideElement passwordField = $x("//input[@name = 'password']");
    public SelenideElement goButton = $x("//button[text()= ' GO']");
    String toggleNavigation = "//a[text() = '%s']";
    String dropDownMenu = "//div[@class = 'dropdown-menu show']//a[text() = '%s']";

    @Step("Проверка ожидаемого {expectedString} и выведенного текста {actualString}")
    public static void checkText(String actualString, String expectedString) {
        if (!actualString.equals(expectedString)) {
            throw new AssertionError("Expected: " + expectedString + ", but got: " + actualString);
        }
    }

    @Step("Клик по элементу {toggle} в навигационной панели'")
    public MainPage toggleNavigationClick(String toggle) {
        $x(String.format(toggleNavigation, toggle)).click();
        return this;
    }

    @Step("Клик по элементу {item} в выпадающем списке")
    public void selectDropDownMenu(String item) {
        $x(String.format(dropDownMenu, item)).click();
    }

    @Step("Авторизация с Логином: {login} и паролем: {password}")
    public MainPage authorization(String login, String password) {
        emailField.setValue(login);
        passwordField.setValue(password);
        goButton.click();
        Selenide.switchTo().alert().accept();
        return this;
    }

    @Step("Проверка выводимого красного текста {expectText} при ошибках в валидации")
    public MainPage checkRedText(String expectText) {
        if (!expectText.equals($x(String.format(redText, expectText)).getText())) {
            throw new AssertionError("Expected: " + expectText + ", but got: " + $x(String.format(redText, expectText)).getText());
        }
        return this;
    }

    @Step("Авторизация с Логином: {login}, паролем: {password} и валидацией {expect}")
    public MainPage authAndValidate(String login, String password, String expect) {
        emailField.setValue(login);
        passwordField.setValue(password);
        goButton.click();
        checkText(expect, Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        return this;
    }
}
