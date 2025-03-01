package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class ReadOneHousePage {

    private static final SelenideElement READ_HOUSE_FIELD = $x("//input[@id='house_input']");
    private static final SelenideElement READ_HOUSE_BUTTON = $x("//button[contains(@class,'tableButton')]");
    private static final SelenideElement READ_HOUSE_STATUS = $x("//button[contains(@class,'status')]");
    private static final ElementsCollection FIELDS_VALUES_OF_HOUSE = $$x("//table[contains(@class,'tableHouse')]//td");

    @Step("Получение данных об одном доме по его ID = {houseId}")
    public ElementsCollection getFieldsValuesOfHouse(String houseId) {
        READ_HOUSE_FIELD.shouldBe(visible, ofSeconds(10)).setValue(houseId);
        READ_HOUSE_BUTTON.shouldBe(visible, ofSeconds(10)).click();
        return FIELDS_VALUES_OF_HOUSE;
    }

    @Step ("Получение статуса операции по поиску дома")
    public String getStatusReadHouse() {
        return READ_HOUSE_STATUS.getText();
    }

}