package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SellAndBuyCarPage {
    public final SelenideElement saleStatus = $x("//button[@class='status btn btn-secondary']");
    final SelenideElement userIdSend = $x("//input[@id='id_send']");
    final SelenideElement carIdSend = $x("//input[@id='car_send']");
    final SelenideElement sellButton = $x("//input[@value='sellCar']");
    final SelenideElement buyButton = $x("//input[@value='buyCar']");
    final SelenideElement pushToApi = $x("//button[@class='tableButton btn btn-primary']");

    @Step("Продажа автомобиля '{carId}' пользователем '{userId}'")
    public SellAndBuyCarPage sellCar(String userId, String carId) {
        log.info("Продажа автомобиля '{}' пользователем '{}'", carId, userId);
        userIdSend.setValue(userId);
        carIdSend.setValue(carId);
        sellButton.click();
        pushToApi.click();
        Selenide.sleep(1000);
        saleStatus.shouldBe(visible, Duration.ofSeconds(10)).text();
        return this;
    }

    @Step("Покупка автомобиля '{carId}' пользователем '{userId}'")
    public SellAndBuyCarPage buyCar(String userId, String carId) {
        log.info("Покупка автомобиля '{}' пользователем '{}'", carId, userId);
        userIdSend.setValue(userId);
        carIdSend.setValue(carId);
        buyButton.click();
        pushToApi.click();
        Selenide.sleep(1000);
        saleStatus.shouldBe(visible, Duration.ofSeconds(10)).text();
        return this;
    }

    @Step("Получения сообщения о статусе продажи автомобиля")
    public SellAndBuyCarPage carCreateStatus() {
        log.info("Получения сообщения о статусе продажи автомобиля");
        saleStatus.getText();
        return this;
    }
}
