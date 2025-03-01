package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

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

    @Step("Удаление User {userId}")
    public void deleteUserId(String userId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_USER_FIELD.shouldBe(visible, ofSeconds(10)).click();
        DELETE_USER_FIELD.setValue(userId);
        sleep(2000);
        DELETE_USER_BUTTON.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Удаление Car {carId}")
    public void deleteCarId(String carId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        sleep(3000);
        switchTo().window(1);
        sleep(2000);
        deleteCarField.shouldBe(visible, ofSeconds(10)).click();
        deleteCarField.setValue(carId);
        sleep(2000);
        deleteCarButton.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Удаление House {houseId}")
    public void deleteHouseId(String houseId) {
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
