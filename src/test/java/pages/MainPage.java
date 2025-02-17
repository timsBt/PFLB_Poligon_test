package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static utils.PropertyReader.getProperty;

public class MainPage {

    String login = System.getProperty("login", getProperty("login"));
    String password = System.getProperty("password", getProperty("password"));

    String toggleNavigation = "//a[text() = '%s']";
    String dropDownMenu = "//div[@class = 'dropdown-menu show']//a[text() = '%s']";

    public SelenideElement emailField = $x("//input[@name = 'email']");
    public SelenideElement passwordField = $x("//input[@name = 'password']");
    public SelenideElement goButton = $x("//button[text()= ' GO']");

    @Step("Клик по элементу в навигационной панели'")
    public MainPage toggleNavigationClick(String toggle) {
        $x(String.format(toggleNavigation, toggle)).click();
        return this;
    }

    @Step("Клик по элементу в выпадающем списке")
    public void selectDropDownMenu(String item) {
        $x(String.format(dropDownMenu, item)).click();
    }

    @Step("Авторизация")
    public MainPage authorization() {
        emailField.setValue(login);
        passwordField.setValue(password);
        goButton.click();
        Selenide.switchTo().alert().accept();
        return this;
    }
}
