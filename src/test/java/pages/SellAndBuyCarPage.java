package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SellAndBuyCarPage {
    final SelenideElement userIdSend = $x("//input[@id='id_send']");
    final SelenideElement carIdSend = $x("//input[@id='car_send']");
    final SelenideElement sellButton = $x("//input[@value='sellCar']");
    final SelenideElement buyButton = $x("//input[@value='buyCar']");
    final SelenideElement pushToApi = $x("//button[@class='tableButton btn btn-primary']");
    public final SelenideElement saleStatus = $x("//button[@class='status btn btn-secondary']");

    @Step("Продажа автомобиля {carId} пользователем {userId}")
    public SellAndBuyCarPage sellCar(String userId, String carId) {
        userIdSend.setValue(userId);
        carIdSend.setValue(carId);
        sellButton.click();
        pushToApi.click();
        Selenide.sleep(1000);
        saleStatus.shouldBe(visible, Duration.ofSeconds(10)).text();
        return this;
    }

    @Step("Продажа автомобиля {carId} пользователем {userId}")
    public SellAndBuyCarPage buyCar(String userId, String carId) {
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
        saleStatus.getText();
        return this;
    }
}
