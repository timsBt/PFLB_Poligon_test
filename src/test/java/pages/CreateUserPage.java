package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

public class CreateUserPage {

    public SelenideElement firstNameSend = $(id("first_name_send"));
    public SelenideElement lastNameSend = $(id("last_name_send"));
    public SelenideElement ageSend = $(id("age_send"));
    public SelenideElement moneySend = $(id("money_send"));
    public SelenideElement pushToApi = $x("//div//button[@class = 'tableButton btn btn-primary']");
    public SelenideElement usersId = $x("//div//button[@class='newId btn btn-secondary']");
    public SelenideElement successStatus = $x("//div//button[text() = 'Status: Successfully pushed, code: 201']");
    public SelenideElement invalidStatus = $x("//div//button[text() = 'Status: Invalid request data']");

    String sexSend = "//input[@value = '%s']";

    @Step("Создание нового юзера")
    public String createNewUser(String firstName, String lastName, String age, String sex, String money) {
        firstNameSend.setValue(firstName);
        lastNameSend.setValue(lastName);
        ageSend.setValue(age);
        if (!sex.isEmpty()) {
            $x(String.format(sexSend, sex)).click();
        }
        moneySend.setValue(money);
        pushToApi.click();
        Selenide.sleep(1000);
        return usersId.shouldBe(visible, Duration.ofSeconds(10)).text().replace("New user ID: ", "");
    }
}
