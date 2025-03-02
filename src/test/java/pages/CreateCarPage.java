package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.id;
import static utils.PropertyReader.getProperty;

@Log4j2
public class CreateCarPage {

    private static final SelenideElement ENGINE_TYPE_TEXT = $x("//th[contains(text(), 'Engine')]");
    public SelenideElement carEngineTypeSend = $(id("car_engine_type_send"));
    public SelenideElement carMarkSend = $(id("car_mark_send"));
    public SelenideElement carModelSend = $(id("car_model_send"));
    public SelenideElement carPriceSend = $(id("car_price_send"));
    public SelenideElement pushToApi = $x("//button[@class = 'tableButton btn btn-primary']");
    public SelenideElement carId = $x("//button[contains(text(), 'New')]");
    public SelenideElement carStatus = $x("//button[@class='status btn btn-secondary']");

    @Step("Создание нового Автомобиля с данными: {engineType}, {mark}, {model}, {price}")
    public String createNewCar(String engineType, String mark, String model, String price) {
        String car = null;
        try {
            log.info("Создание нового автомобиля c данными: '{}', '{}', '{}', '{}'", engineType, mark, model, price);
            sleep(2000);
            ENGINE_TYPE_TEXT.shouldBe(visible, Duration.ofSeconds(10));
            carEngineTypeSend.shouldBe(visible, Duration.ofSeconds(10)).setValue(engineType);
            carMarkSend.setValue(mark);
            carModelSend.setValue(model);
            carPriceSend.setValue(price);
            pushToApi.shouldBe(visible, ofSeconds(10)).click();
            sleep(2000);
            car = carId.shouldBe(visible, ofSeconds(10)).text().replace("New car ID: ", "");
            log.info("Car создался, Car = {}", car);
        } catch (ElementNotFound e) {
            if (Stream.of(engineType, mark, model, price).anyMatch(String::isEmpty)) {
                log.info("Car не создался потому что есть пустые поля, Car = {}", car);
            } else if (engineType.equals(getProperty("notExistentEngineType"))) {
                log.info("Car не создался потому что поле 'engineType' не валидное, Car = {}", car);
            } else log.error("Car НЕ создался, Car = {}", car);
        }
        return car;
    }

    @Step("Получения сообщения о статусе создания автомобиля")
    public String carCreateStatus() {
        log.info("Получения сообщения о статусе создания автомобиля");
        return carStatus.getText();
    }
}
