package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

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

    public MainPage authorization(String login, String password) {
        emailField.setValue(login);
        passwordField.setValue(password);
        goButton.click();
        Selenide.switchTo().alert().accept();
        return this;
    }
}
