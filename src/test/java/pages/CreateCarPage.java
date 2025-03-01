package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.id;

@Log4j2
public class CreateCarPage {

    public SelenideElement carEngineTypeSend = $(id("car_engine_type_send"));
    public SelenideElement carMarkSend = $(id("car_mark_send"));
    public SelenideElement carModelSend = $(id("car_model_send"));
    public SelenideElement carPriceSend = $(id("car_price_send"));
    public SelenideElement pushToApi = $x("//button[@class = 'tableButton btn btn-primary']");
    public SelenideElement carId = $x("//button[@class='newId btn btn-secondary']");
    public SelenideElement carStatus = $x("//button[@class='status btn btn-secondary']");

    @Step("Создание нового Автомобиля с данными: {engineType}, {mark}, {model}, {price}")
    public String createNewCar(String engineType, String mark, String model, String price) {
        log.info("Создание нового автомобиля c данными: '{}', '{}', '{}', '{}'", engineType, mark, model, price);
        carEngineTypeSend.shouldBe(visible, Duration.ofSeconds(10)).setValue(engineType);
        carMarkSend.setValue(mark);
        carModelSend.setValue(model);
        carPriceSend.setValue(price);
        pushToApi.shouldBe(visible, ofSeconds(10)).click();
        sleep(2000);
        return carId.shouldBe(visible, Duration.ofSeconds(10)).text().replace("New car ID: ", "");
    }

    @Step("Получения сообщения о статусе создания автомобиля")
    public String carCreateStatus() {
        log.info("Получения сообщения о статусе создания автомобиля");
        return carStatus.getText();
    }
}
