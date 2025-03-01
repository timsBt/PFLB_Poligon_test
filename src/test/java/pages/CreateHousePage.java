package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.id;

@Log4j2
public class CreateHousePage {

    private static final SelenideElement FLOOR_SEND = $(id("floor_send"));
    private static final SelenideElement PRICE_SEND = $(id("price_send"));
    private static final SelenideElement PARKING_FIRST_SEND = $(id("parking_first_send"));
    private static final SelenideElement PARKING_SECOND_SEND = $(id("parking_second_send"));
    private static final SelenideElement PARKING_THIRD_SEND = $(id("parking_third_send"));
    private static final SelenideElement PARKING_FOURTH_SEND = $(id("parking_fourth_send"));
    private static final SelenideElement BUTTON_PUSH_TO_API = $($x("//button[@class='tableButton btn btn-primary']"));
    private static final SelenideElement CREATE_HOUSE_STATUS = $($x("//button[@class='status btn btn-secondary']"));
    private static final SelenideElement NEW_HOUSE_ID = $($x("//button[@class='newId btn btn-secondary']"));

    @Step("Создание нового дома с данными: {floor}, {price}, {parkingFirst}, {parkingSecond}, {parkingThird}, {parkingFourth}")
    public String createNewHouse(String floor, String price, String parkingFirst,
                                 String parkingSecond, String parkingThird, String parkingFourth) {
        log.info("Создание нового дома с данными: '{}', '{}', '{}', '{}', '{}', '{}'", floor, price,
                parkingFirst, parkingSecond, parkingThird, parkingFourth);
        FLOOR_SEND.shouldBe(visible, ofSeconds(10)).setValue(floor);
        PRICE_SEND.setValue(price);
        PARKING_FIRST_SEND.setValue(parkingFirst);
        PARKING_SECOND_SEND.setValue(parkingSecond);
        PARKING_THIRD_SEND.setValue(parkingThird);
        PARKING_FOURTH_SEND.setValue(parkingFourth);
        BUTTON_PUSH_TO_API.click();
        return NEW_HOUSE_ID.shouldBe(visible).text().replace("New house ID: ", "");
    }

    @Step("Получение статуса операции по созданию дома")
    public SelenideElement getCreateHouseStatus() {
        log.info("Получение статуса операции по созданию дома");
        return CREATE_HOUSE_STATUS;
    }
}
