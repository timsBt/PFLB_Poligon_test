package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private static final String RED_TEXT = "//*[text() = '%s']";
    private static final SelenideElement EMAIL_FIELD = $x("//input[@name = 'email']");
    private static final SelenideElement PASSWORD_FIELD = $x("//input[@name = 'password']");
    private static final SelenideElement GO_BUTTON = $x("//button[text()= ' GO']");
    private static final String TOOGLE_NAVIGATION = "//a[text() = '%s']";
    private static final String DROP_DOWN_MENU = "//div[@class = 'dropdown-menu show']//a[text() = '%s']";

    @Step("Проверка ожидаемого и выведенного текста")
    public static void checkText(String actualString, String expectedString) {
        if (!actualString.equals(expectedString)) {
            throw new AssertionError("Expected: " + expectedString + ", but got: " + actualString);
        }
    }

    @Step("Клик по элементу в навигационной панели'")
    public MainPage toggleNavigationClick(String toggle) {
        $x(String.format(TOOGLE_NAVIGATION, toggle)).click();
        return this;
    }

    @Step("Клик по элементу в выпадающем списке")
    public void selectDropDownMenu(String item) {
        $x(String.format(DROP_DOWN_MENU, item)).click();
    }

    @Step("Авторизация")
    public MainPage authorization(String login, String password) {
        EMAIL_FIELD.setValue(login);
        PASSWORD_FIELD.setValue(password);
        GO_BUTTON.click();
        Selenide.switchTo().alert().accept();
        return this;
    }

    @Step("Авторизация")
    public MainPage authorizationWithValidateAlert(String login, String password) {
        EMAIL_FIELD.setValue(login);
        PASSWORD_FIELD.setValue(password);
        GO_BUTTON.click();
        return this;
    }

    @Step("Проверка выводимого красного текста при ошибках в валидации")
    public MainPage checkRedText(String expectText) {
        if (!expectText.equals($x(String.format(RED_TEXT, expectText)).getText())) {
            throw new AssertionError("Expected: " + expectText + ", but got: " + $x(String.format(RED_TEXT, expectText)).getText());
        }

        return this;
    }

    @Step("Авторизация с валидацией")
    public MainPage authAndValidate(String login, String password, String expect) {
        EMAIL_FIELD.setValue(login);
        PASSWORD_FIELD.setValue(password);
        GO_BUTTON.click();
        checkText(expect, Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        return this;
    }
    @Step("Валидация")
    public MainPage checkResult(String string){
        String str = Selenide.$x(String.format(RED_TEXT, string)).getText();
        if (!str.isEmpty()) {
            checkRedText(str);
        } else Selenide.closeWebDriver();
        return this;
    }
}
