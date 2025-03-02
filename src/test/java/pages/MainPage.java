package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.time.Duration.ofSeconds;

@Log4j2
public class MainPage {

    private static final String RED_TEXT = "//*[text() = '%s']";
    private static final SelenideElement EMAIL_FIELD = $x("//input[@name = 'email']");
    private static final SelenideElement PASSWORD_FIELD = $x("//input[@name = 'password']");
    private static final SelenideElement GO_BUTTON = $x("//button[text()= ' GO']");
    private static final String TOGGLE_NAVIGATION = "//a[text() = '%s']";
    private static final String DROP_DOWN_MENU = "//div[@class = 'dropdown-menu show']//a[text() = '%s']";

    @Step("Проверка ожидаемого текста '{expectedString}' и выведенного текста '{actualString}'")
    public static void checkText(String actualString, String expectedString) {
        log.info("Проверка ожидаемого текста '{}' и выведенного текста '{}'", expectedString, actualString);
        if (!actualString.equals(expectedString)) {
            throw new AssertionError("Expected: " + expectedString + ", but got: " + actualString);
        }
    }

    @Step("Клик по элементу '{toggle}' в навигационной панели")
    public MainPage toggleNavigationClick(String toggle) {
        log.info("Клик по элементу '{}' в навигационной панели", toggle);
        $x(String.format(TOGGLE_NAVIGATION, toggle)).shouldBe(visible, ofSeconds(10)).click();
        return this;
    }

    @Step("Клик по элементу '{item}' в выпадающем списке")
    public void selectDropDownMenu(String item) {
        log.info("Клик по элементу '{}' в выпадающем списке", item);
        $x(String.format(DROP_DOWN_MENU, item)).shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Авторизация с логином: {login} и паролем: {password}")
    public MainPage authorization(String login, String password) {
        log.info("Авторизация с логином: '{}' и паролем: '{}'", login, password);
        EMAIL_FIELD.setValue(login);
        PASSWORD_FIELD.setValue(password);
        GO_BUTTON.click();
        Selenide.switchTo().alert().accept();
        return this;
    }

    @Step("Авторизация с логином: {login} и паролем: {password} без закрытия Alert")
    public MainPage authorizationWithValidateAlert(String login, String password) {
        log.info("Авторизация с логином: '{}' и паролем: '{}' без закрытия Alert", login, password);
        EMAIL_FIELD.setValue(login);
        PASSWORD_FIELD.setValue(password);
        GO_BUTTON.click();
        return this;
    }

    @Step("Проверка выводимого красного текста '{expectText}' при ошибках в валидации")
    public MainPage checkRedText(String expectText) {
        log.info("Проверка выводимого красного текста '{}' при ошибках в валидации", expectText);
        if (!expectText.equals($x(String.format(RED_TEXT, expectText)).getText())) {
            throw new AssertionError("Expected: " + expectText + ", but got: " +
                    $x(String.format(RED_TEXT, expectText)).getText());
        }
        return this;
    }

    @Step("Авторизация с логином: {login} и паролем: {password} и валидацией текста: {expect}")
    public MainPage authAndValidate(String login, String password, String expect) {
        log.info("Авторизация с логином: '{}' и паролем: '{}' и валидацией текста: '{}'",
                login, password, expect);
        EMAIL_FIELD.setValue(login);
        PASSWORD_FIELD.setValue(password);
        GO_BUTTON.click();
        checkText(expect, Selenide.switchTo().alert().getText());
        Selenide.switchTo().alert().accept();
        return this;
    }

    @Step("Валидация текста: {messageText}")
    public MainPage checkResult(String messageText) {
        log.info("Валидация текста: '{}'", messageText);
        String str = Selenide.$x(String.format(RED_TEXT, messageText)).getText();
        if (!str.isEmpty()) {
            checkRedText(str);
        } else closeWebDriver();
        return this;
    }
}
