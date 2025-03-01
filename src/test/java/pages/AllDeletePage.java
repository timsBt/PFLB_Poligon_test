package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

@Log4j2
public class AllDeletePage {

    private static final SelenideElement DELETE_USER_BUTTON = $x("//button[@value = 'user']");
    private static final SelenideElement DELETE_USER_FIELD = $x("(//button[@value = 'user']/ancestor::" +
            "div//input[@id = 'house_input'])[1]");
    private static final SelenideElement DELETE_USER_STATUS = $x("(//button[@value = 'user']/ancestor::" +
            "div//button[@class = 'status btn btn-secondary'])[1]");
    public SelenideElement deleteCarButton = $x("//button[@value = 'car']");
    public SelenideElement deleteCarField = $x("(//button[@value = 'car']/ancestor::div//input[@id =" +
            " 'house_input'])[3]");
    public SelenideElement deleteCarStatus = $x("(//button[@value = 'car']/ancestor::div//button[@class" +
            " = 'status btn btn-secondary'])[3]");
    public SelenideElement deleteHouseButton = $x("//button[@value = 'house']");
    public SelenideElement deleteHouseField = $x("(//button[@value = 'house']/ancestor::div//input[@id =" +
            " 'house_input'])[2]");
    public SelenideElement deleteHouseStatus = $x("(//button[@value = 'house']/ancestor::div//button[@class" +
            " = 'status btn btn-secondary'])[2]");

    @Step("Удаление User по ID: {userId}")
    public void deleteUserId(String userId) {
        log.info("Удаление User по ID: '{}'", userId);
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_USER_FIELD.shouldBe(visible, ofSeconds(10)).setValue(userId);
        DELETE_USER_BUTTON.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Удаление Car по ID: {carId}")
    public void deleteCarId(String carId) {
        log.info("Удаление Car по ID: '{}'", carId);
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        deleteCarField.shouldBe(visible, ofSeconds(10)).setValue(carId);
        deleteCarButton.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Удаление House по ID: {houseId}")
    public void deleteHouseId(String houseId) {
        log.info("Удаление House по ID: '{}'", houseId);
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        switchTo().window(1);
        deleteHouseField.shouldBe(visible, ofSeconds(10)).setValue(houseId);
        deleteHouseButton.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Проверка статуса: {actualResult}")
    public void checkUserStatus(String actualResult) {
        DELETE_USER_STATUS.shouldBe(visible, ofSeconds(10)).shouldHave(text(actualResult));
    }
}
