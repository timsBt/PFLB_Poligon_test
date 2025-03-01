package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.id;

public class CreateUserPage {

    public SelenideElement firstNameSend = $(id("first_name_send"));
    public SelenideElement lastNameSend = $(id("last_name_send"));
    public SelenideElement ageSend = $(id("age_send"));
    public SelenideElement moneySend = $(id("money_send"));
    public SelenideElement pushToApi = $x("//div//button[@class = 'tableButton btn btn-primary']");
    public SelenideElement usersId = $x("//div//button[@class='newId btn btn-secondary']");
    public SelenideElement status = $x("//button[@class = 'status btn btn-secondary']");

    String sexSend = "//input[@value = '%s']";

    @Step("Создание нового юзера c полями: {firstName}, {lastName}, {age}, {sex}, {money}")
    public String createNewUser(String firstName, String lastName, String age, String sex, String money) {
        sleep(2000);
        firstNameSend.setValue(firstName);
        lastNameSend.setValue(lastName);
        ageSend.setValue(age);
        if (!sex.isEmpty()) {
            $x(String.format(sexSend, sex)).click();
        }
        moneySend.setValue(money);
        pushToApi.click();
        sleep(3000);
        return usersId.shouldBe(visible, Duration.ofSeconds(10)).text().replace("New user ID: ", "");
    }
}
