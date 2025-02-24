package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class AllDeletePage {

    public SelenideElement deleteUserButton = $x("//button[@value = 'user']");
    public SelenideElement deleteUserField = $x("//button[@value = 'user']//..//input[@id = 'house_input']");
    public SelenideElement deleteUserStatus = $x("//button[@value = 'user']//..//button[@class = 'status btn btn-secondary']");
    public SelenideElement deleteCarButton = $x("//button[@value = 'car']");
    public SelenideElement deleteCarField = $x("//button[@value = 'car']//..//input[@id = 'house_input']");
    public SelenideElement deleteCarStatus = $x("//button[@value = 'car']//..//button[@class = 'status btn btn-secondary']");
    public SelenideElement deleteHouseButton = $x("//button[@value = 'house']");
    public SelenideElement deleteHouseField = $x("//button[@value = 'house']//..//input[@id = 'house_input']");
    public SelenideElement deleteHouseStatus = $x("//button[@value = 'house']//..//button[@class = 'status btn btn-secondary']");

    @Step("Удаление User")
    public void deleteUserId(String userId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        switchTo().window(1);
        deleteUserField.click();
        deleteUserField.setValue(userId);
        deleteUserButton.click();
    }

    @Step("Удаление Car")
    public void deleteCarId(String carId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        switchTo().window(1);
        deleteCarField.click();
        deleteCarField.setValue(carId);
        deleteCarButton.click();
    }

    @Step("Удаление House")
    public void deleteHouseId(String houseId) {
        MainPage mainPage = new MainPage();
        mainPage.toggleNavigationClick("All DELETE");
        switchTo().window(1);
        deleteHouseField.setValue(houseId);
        deleteHouseButton.click();
    }
}
