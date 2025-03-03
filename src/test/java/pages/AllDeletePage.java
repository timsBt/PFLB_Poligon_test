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
    private static final SelenideElement DELETE_USER_FIELD = $x("//button[@value = 'user']/following-sibling::button/input");
    private static final SelenideElement DELETE_CAR_BUTTON = $x("//button[@value = 'car']");
    private static final SelenideElement DELETE_CAR_FIELD = $x("//button[@value = 'car']/following-sibling::button/input");
    private static final SelenideElement DELETE_HOUSE_BUTTON = $x("//button[@value = 'house']");
    private static final SelenideElement DELETE_HOUSE_FIELD = $x("//button[@value = 'house']/following-sibling::button/input");
    private static final String DELETE_STATUS = "//button[@value = '%s']/following-sibling::button[contains(@class,'status')]";
    private static final SelenideElement TOGGLE_NAVIGATION_ALL_DELETE = $x("//a[text() = 'All DELETE']");

    @Step("Удаление User по ID: {userId}")
    public void deleteUserId(String userId) {
        log.info("Удаление User по ID: '{}'", userId);
        TOGGLE_NAVIGATION_ALL_DELETE.shouldBe(visible, ofSeconds(10)).click();
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_USER_BUTTON.shouldBe(visible, ofSeconds(10));
        DELETE_USER_FIELD.shouldBe(visible, ofSeconds(10)).setValue(userId);
        DELETE_USER_BUTTON.click();
    }

    @Step("Удаление Car по ID: {carId}")
    public void deleteCarId(String carId) {
        log.info("Удаление Car по ID: '{}'", carId);
        TOGGLE_NAVIGATION_ALL_DELETE.shouldBe(visible, ofSeconds(10)).click();
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_CAR_BUTTON.shouldBe(visible, ofSeconds(10));
        DELETE_CAR_FIELD.shouldBe(visible, ofSeconds(10)).setValue(carId);
        DELETE_CAR_BUTTON.click();
    }

    @Step("Удаление House по ID: {houseId}")
    public void deleteHouseId(String houseId) {
        TOGGLE_NAVIGATION_ALL_DELETE.shouldBe(visible, ofSeconds(10)).click();
        sleep(2000);
        switchTo().window(1);
        sleep(2000);
        DELETE_HOUSE_BUTTON.shouldBe(visible, ofSeconds(10));
        DELETE_HOUSE_FIELD.shouldBe(visible, ofSeconds(10)).setValue(houseId);
        DELETE_HOUSE_BUTTON.click();
    }

    @Step("Проверка статуса удаления объекта '{objectName}': '{actualResult}'")
    public void checkStatus(String objectName, String actualResult) {
        log.info("Проверка статуса удаления объекта '{}' пользователем '{}'", objectName, actualResult);
        $x(String.format(DELETE_STATUS, objectName)).shouldBe(visible, ofSeconds(10)).shouldHave(text(actualResult));
    }
}
