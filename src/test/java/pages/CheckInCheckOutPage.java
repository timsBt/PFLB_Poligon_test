package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import tests.uiTests.BaseTest;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class CheckInCheckOutPage {

    private static final SelenideElement USER_ID_TEXT = $x("//th[contains(text(), 'User')]");
    private static final SelenideElement DROP_DOWN_USER = $x("//*[text()=\"Users\"]");
    private static final SelenideElement CHECKIN_CHECKOUT_LINK = $x("//*[text()=\"Settle to house\"]");
    private static final SelenideElement USER_FIELD = $x("//*[@id=\"id_send\"]");
    private static final SelenideElement HOUSE_FIELD = $x("//*[@id=\"house_send\"]");
    private static final SelenideElement SETTLE_CHECK_BOX = $x("//*[@value=\"settle\"]");
    private static final SelenideElement EVICT_CHECK_BOX = $x("//*[@value=\"evict\"]");
    private static final SelenideElement PUSH_TO_API = $x("//*[@id=\"root\"]/div/section/div/div/button[1]");
    private static final SelenideElement CHECK_TEXT = $x("//*[@class=\"status btn btn-secondary\"]");
    MainPage mainPage = new MainPage();

    @Step("Вход с главной страницы на страницу заселения и выселения")
    public CheckInCheckOutPage goToLink() {
        log.info("Вход с главной страницы на страницу заселения и выселения");
        DROP_DOWN_USER.shouldBe(visible, Duration.ofSeconds(10)).click();
        CHECKIN_CHECKOUT_LINK.click();
        return this;
    }

    @Step("Авторизация под пользователем с достаточным количеством прав")
    public CheckInCheckOutPage auth() {
        log.info("Авторизация под пользователем с достаточным количеством прав");
        mainPage.authAndValidate(BaseTest.login, BaseTest.password, "Successful authorization");
        return this;
    }

    @Step("Заселение пользователя '{user}' в дом '{house}'")
    public CheckInCheckOutPage addUserToHouse(String user, String house) {
        log.info("Заселение пользователя '{}' в дом '{}'", user, house);
        sleep(2000);
        USER_ID_TEXT.shouldBe(visible, Duration.ofSeconds(10));
        USER_FIELD.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(user);
        HOUSE_FIELD.sendKeys(house);
        SETTLE_CHECK_BOX.click();
        PUSH_TO_API.click();
        return this;
    }

    @Step("Выселение пользователя '{user}' из дома '{house}'")
    public CheckInCheckOutPage evictUserFromHouse(String user, String house) {
        log.info("Выселение пользователя '{}' в дом '{}'", user, house);
        sleep(2000);
        USER_ID_TEXT.shouldBe(visible, Duration.ofSeconds(10));
        USER_FIELD.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(user);
        HOUSE_FIELD.sendKeys(house);
        EVICT_CHECK_BOX.click();
        PUSH_TO_API.click();
        return this;
    }
    @Step("Проверка ожидаемого текста '{expectedString}' и выведенного текста '{actualString}'")
    public static void checkText( String expectedString) {
        log.info("Проверка ожидаемого текста '{}' и выведенного текста '{}'", expectedString, CHECK_TEXT.shouldBe(visible).getText());
        if (!CHECK_TEXT.shouldBe(visible).getText().equals(expectedString)) {
            throw new AssertionError("Expected: " + expectedString + ", but got: " + CHECK_TEXT.shouldBe(visible).getText());
        }
    }
}
