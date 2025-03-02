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

    MainPage mainPage= new MainPage();

    public SelenideElement dropDownUser = $x("//*[text()=\"Users\"]");
    public SelenideElement checkInCheckOutLink = $x("//*[text()=\"Settle to house\"]");
    public SelenideElement userField = $x("//*[@id=\"id_send\"]");
    public SelenideElement houseField = $x("//*[@id=\"house_send\"]");
    public SelenideElement settleCheckBox = $x("//*[@value=\"settle\"]");
    public SelenideElement evictCheckBox = $x("//*[@value=\"evict\"]");
    public SelenideElement pushToApi = $x("//*[@id=\"root\"]/div/section/div/div/button[1]");
    public SelenideElement checkText = $x("//*[@class=\"status btn btn-secondary\"]");
    private static final SelenideElement USER_ID_TEXT = $x("//th[contains(text(), 'User')]");

    @Step("Вход с главной страницы на страницу заселение и выселения")
    public CheckInCheckOutPage goToLink() {
        log.info("Вход с главной страницы на страницу заселение и выселения");
        dropDownUser.shouldBe(visible, Duration.ofSeconds(10)).click();
        checkInCheckOutLink.click();
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
        userField.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(user);
        houseField.sendKeys(house);
        settleCheckBox.click();
        pushToApi.click();
        return this;
    }

    @Step("Выселение пользователя '{user}' из дома '{house}'")
    public CheckInCheckOutPage evictUserFromHouse(String user, String house) {
        log.info("Выселение пользователя '{}' в дом '{}'", user, house);
        sleep(2000);
        USER_ID_TEXT.shouldBe(visible, Duration.ofSeconds(10));
        userField.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(user);
        houseField.sendKeys(house);
        evictCheckBox.click();
        pushToApi.click();
        return this;
    }
}
