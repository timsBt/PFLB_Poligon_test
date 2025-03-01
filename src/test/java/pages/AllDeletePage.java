package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class AllDeletePage {

    private static final SelenideElement DELETE_USER_BUTTON = $x("//button[@value = 'user']");
    private static final SelenideElement DELETE_USER_FIELD = $x("//button[@value = 'user']/following-sibling::button/input");
    private static final SelenideElement DELETE_CAR_BUTTON = $x("//button[@value = 'car']");
    private static final SelenideElement DELETE_CAR_FIELD = $x("//button[@value = 'car']/following-sibling::button/input");
    private static final SelenideElement DELETE_HOUSE_BUTTON = $x("//button[@value = 'house']");
    private static final SelenideElement DELETE_HOUSE_FIELD = $x("//button[@value = 'house']/following-sibling::button/input");
    private static final String DELETE_STATUS = "//button[@value = '%s']/following-sibling::button[contains(@class,'status')]";

    @Step("Удаление User {userId}")
    public void deleteUserId(String userId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_USER_FIELD.shouldBe(visible, ofSeconds(10)).setValue(userId);
        DELETE_USER_BUTTON.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Удаление Car {carId}")
    public void deleteCarId(String carId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_CAR_FIELD.shouldBe(visible, ofSeconds(10)).setValue(carId);
        DELETE_CAR_BUTTON.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Удаление House {houseId}")
    public void deleteHouseId(String houseId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        switchTo().window(1);
        DELETE_HOUSE_FIELD.shouldBe(visible, ofSeconds(10)).setValue(houseId);
        DELETE_HOUSE_BUTTON.shouldBe(visible, ofSeconds(10)).click();
    }

    @Step("Проверка статуса удаления объекта {objectName}: {actualResult}")
    public void checkStatus(String objectName, String actualResult) {
        $x(String.format(DELETE_STATUS, objectName)).shouldBe(visible, ofSeconds(10)).shouldHave(text(actualResult));
    }
}
