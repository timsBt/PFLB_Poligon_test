package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
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
    public void goToLink(){
        dropDownUser.click();
        checkInCheckOutLink.click();
   }
   @Step("Авторизация под пользователем с достаточным количеством прав")
   public void auth(){
       mainPage.authAndValidate(mainPage.login, mainPage.password,"Successful authorization");
   }

    @Step("Заселение пользователя в дом")
    public void addUserToHouse(String user, String house){
       userField.sendKeys(user);
       houseField.sendKeys(house);
       settleCheckBox.click();
       pushToApi.click();
    }

    @Step("Выселение пользователя из дома")
    public void evictUserFromHouse(String user, String house){
        userField.sendKeys(user);
        houseField.sendKeys(house);
        evictCheckBox.click();
        pushToApi.click();
    }
}
