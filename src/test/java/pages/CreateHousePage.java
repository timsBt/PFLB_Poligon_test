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

    public SelenideElement floorSend = $(id("floor_send"));
    public SelenideElement priceSend = $(id("price_send"));
    public SelenideElement parkingFirstSend = $(id("parking_first_send"));
    public SelenideElement parkingSecondSend = $(id("parking_second_send"));
    public SelenideElement parkingThirdSend = $(id("parking_third_send"));
    public SelenideElement parkingFourthSend = $(id("parking_fourth_send"));
    public SelenideElement buttonPushToApi = $($x("//button[@class='tableButton btn btn-primary']"));
    public SelenideElement createHouseStatus = $($x("//button[@class='status btn btn-secondary']"));
    public SelenideElement newHouseId = $($x("//button[@class='newId btn btn-secondary']"));

    @Step("Создание нового дома с данными: {floor}, {price}, {parkingFirst}, {parkingSecond}, {parkingThird}, {parkingFourth}")
    public String createNewHouse(String floor, String price, String parkingFirst,
                                 String parkingSecond, String parkingThird, String parkingFourth) {
        log.info("Создание нового дома с данными: '{}', '{}', '{}', '{}', '{}', '{}'", floor, price,
                parkingFirst, parkingSecond, parkingThird, parkingFourth);
        floorSend.shouldBe(visible, ofSeconds(10)).setValue(floor);
        priceSend.setValue(price);
        parkingFirstSend.setValue(parkingFirst);
        parkingSecondSend.setValue(parkingSecond);
        parkingThirdSend.setValue(parkingThird);
        parkingFourthSend.setValue(parkingFourth);
        buttonPushToApi.click();
        return newHouseId.shouldBe(visible).text().replace("New house ID: ","");
    }

}
