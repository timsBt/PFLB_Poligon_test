package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class AllDeletePage {

    public SelenideElement deleteUserButton = $x("//button[@value = 'user']");
    public SelenideElement deleteUserField = $x("//button[@value = 'user']//..//input[@id = 'house_input']");
    public SelenideElement notDeleteUserStatus = $x("//button[@value = 'user']//..//button[text()= 'Status: not pushed']");
    public SelenideElement deleteUserStatus = $x("//button[@value = 'user']//..//button[text()= 'Status: 204']");
    public SelenideElement deleteCarButton = $x("//button[@value = 'car']");
    public SelenideElement deleteCarField = $x("//button[@value = 'car']//..//input[@id = 'house_input']");
    public SelenideElement deleteCarStatus = $x("//button[@value = 'car']//..//button[@class = 'status btn btn-secondary']");

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
}
