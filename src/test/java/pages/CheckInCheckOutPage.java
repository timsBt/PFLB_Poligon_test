package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.uiTests.BaseTest;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class CheckInCheckOutPage {

    MainPage mainPage= new MainPage();

    public SelenideElement dropDownUser = $x("//*[text()=\"Users\"]");
    public SelenideElement checkInCheckOutLink = $x("//*[text()=\"Settle to house\"]");
    public SelenideElement userField = $x("//*[@id=\"id_send\"]");
    public SelenideElement houseField = $x("//*[@id=\"house_send\"]");
    public SelenideElement settleCheckBox = $x("//*[@value=\"settle\"]");
    public SelenideElement evictCheckBox = $x("//*[@value=\"evict\"]");
    public SelenideElement pushToApi = $x("//*[@id=\"root\"]/div/section/div/div/button[1]");
    public SelenideElement checkText = $x("//*[@class=\"status btn btn-secondary\"]");


   @Step("Войти с главное страницы на странизу заселение и выселения")
   public CheckInCheckOutPage goToLink() {
        dropDownUser.click();
        checkInCheckOutLink.click();
       sleep(2000);
       return this;
   }

   @Step("Авторизация под пользователем с достаточным количеством прав")
   public CheckInCheckOutPage auth() {
       mainPage.authAndValidate(BaseTest.login, BaseTest.password, "Successful authorization");
       return this;
   }

    @Step("Заселение пользователя {user} в дом {house}")
    public CheckInCheckOutPage addUserToHouse(String user, String house) {
       sleep(2000);
       userField.sendKeys(user);
       houseField.sendKeys(house);
       settleCheckBox.click();
       pushToApi.click();
        return this;
    }

    @Step("Выселение пользователя {user} из дома {house}")
    public CheckInCheckOutPage evictUserFromHouse(String user, String house) {
        sleep(2000);
        userField.sendKeys(user);
        houseField.sendKeys(house);
        evictCheckBox.click();
        pushToApi.click();
        return this;
    }
}
