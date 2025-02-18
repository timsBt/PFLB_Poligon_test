package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreateCarsPage {
    final SelenideElement checkPage = $x("//th[contains(text(), 'Mark')]");
    final SelenideElement engineType = $x("//input[@id='car_engine_type_send']");
    final SelenideElement mark = $x("//input[@id='car_mark_send']");
    final SelenideElement model = $x("//input[@id='car_model_send']");
    final SelenideElement price = $x("//input[@id='car_model_send']");
    final SelenideElement create = $x("//button[@class='tableButton btn btn-primary']");

    public CreateCarsPage checkCreateCarPage() {
        checkPage.shouldHave(text("Mark:")).shouldBe(visible);
        return this;
    }

    public CreateCarsPage createNewCar(String engine, String markField, String carModel, String carPrice) {
        engineType.sendKeys(engine);
        mark.sendKeys(markField);
        model.sendKeys(carModel);
        price.sendKeys(carPrice);
        create.click();
        return this;
    }
}
