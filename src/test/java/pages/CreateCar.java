package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

public class CreateCar {

    public SelenideElement carEngineTypeSend = $(id("car_engine_type_send"));
    public SelenideElement carMarkSend = $(id("car_mark_send"));
    public SelenideElement carModelSend = $(id("car_model_send"));
    public SelenideElement carPriceSend = $(id("car_price_send"));
    public SelenideElement pushToApi = $x("//button[@class = 'tableButton btn btn-primary']");
    public SelenideElement carId = $x("//button[@class='newId btn btn-secondary']");
    public SelenideElement carStatus = $x("//button[@class='status btn btn-secondary']");

    @Step("Создание нового Автомобиля и получение его ID")
    public String createNewCar(String engineType, String mark, String model, String price) {
        carEngineTypeSend.setValue(engineType);
        carMarkSend.setValue(mark);
        carModelSend.setValue(model);
        carPriceSend.setValue(price);
        pushToApi.click();
        Selenide.sleep(1000);
        return carId.shouldBe(visible, Duration.ofSeconds(10)).text().replace("New car ID: ", "");
    }

    @Step("Получения сообщения о статусе создания автомобиля")
    public String carCreateStatus() {
        return carStatus.getText();
    }
}