package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.id;

public class CreateUserPage {

    private static final SelenideElement FIRST_NAME_SEND = $(id("first_name_send"));
    private static final SelenideElement LAST_NAME_SEND = $(id("last_name_send"));
    private static final SelenideElement AGE_SEND = $(id("age_send"));
    private static final SelenideElement MONEY_SEND = $(id("money_send"));
    private static final SelenideElement PUSH_TO_API = $x("//div//button[@class = 'tableButton btn btn-primary']");
    private static final SelenideElement USER_ID = $x("//div//button[@class='newId btn btn-secondary']");
    private static final SelenideElement STATUS = $x("//button[@class = 'status btn btn-secondary']");
    private static final String sexSend = "//input[@value = '%s']";

    @Step("Создание нового юзера c полями: {firstName}, {lastName}, {age}, {sex}, {money}")
    public String createNewUser(String firstName, String lastName, String age, String sex, String money) {
        sleep(2000);
        FIRST_NAME_SEND.setValue(firstName);
        LAST_NAME_SEND.setValue(lastName);
        AGE_SEND.setValue(age);
        if (!sex.isEmpty()) {
            $x(String.format(sexSend, sex)).click();
        }
        MONEY_SEND.setValue(money);
        PUSH_TO_API.click();
        sleep(3000);
        return USER_ID.shouldBe(visible, Duration.ofSeconds(10)).text().replace("New user ID: ", "");
    }

    @Step("Проверка статуса: {actualResult}")
    public void checkStatus(String actualResult) {
        STATUS.shouldBe(visible, ofSeconds(10)).shouldHave(text(actualResult));
    }
}
