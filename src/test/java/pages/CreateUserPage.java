package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.id;

@Log4j2
public class CreateUserPage {

    private static final SelenideElement FIRST_NAME_SEND = $(id("first_name_send"));
    private static final SelenideElement LAST_NAME_SEND = $(id("last_name_send"));
    private static final SelenideElement AGE_SEND = $(id("age_send"));
    private static final SelenideElement MONEY_SEND = $(id("money_send"));
    private static final SelenideElement PUSH_TO_API = $x("//div//button[@class = 'tableButton btn btn-primary']");
    private static final SelenideElement USER_ID = $x("//button[contains(text(), 'New')]");
    private static final SelenideElement STATUS = $x("//button[@class = 'status btn btn-secondary']");
    private static final SelenideElement FIRST_NAME_TEXT = $x("//th[contains(text(), 'First')]");
    private static final String SEX_SEND = "//input[@value = '%s']";

    @Step("Создание нового юзера c данными: {firstName}, {lastName}, {age}, {sex}, {money}")
    public String createNewUser(String firstName, String lastName, String age, String sex, String money) {
        String user = null;
        try {
            log.info("Создание нового юзера c данными: '{}', '{}', '{}', '{}', '{}'", firstName, lastName, age, sex, money);
            sleep(2000);
            FIRST_NAME_TEXT.shouldBe(visible, ofSeconds(10));
            FIRST_NAME_SEND.shouldBe(visible, ofSeconds(10)).setValue(firstName);
            LAST_NAME_SEND.setValue(lastName);
            AGE_SEND.setValue(age);
            if (!sex.isEmpty()) {
                $x(String.format(SEX_SEND, sex)).click();
            }
            MONEY_SEND.setValue(money);
            PUSH_TO_API.shouldBe(visible, ofSeconds(10)).click();
            sleep(2000);
            user = USER_ID.shouldBe(visible, ofSeconds(10)).text().replace("New user ID: ", "");
            log.info("User создался, User = {}", user);
        } catch (ElementNotFound e) {
            if (Stream.of(firstName, lastName, age, sex, money).anyMatch(String::isEmpty)) {
                log.info("User не создался потому что есть пустые поля, User = {}", user);
            } else if (age.startsWith("-") || age.equals("0")) {
                log.info("User не создался потому что поле 'age' не валидное, User = {}", user);
            } else log.error("User НЕ создался, User = {}", user);
        }
        return user;
    }

    @Step("Проверка статуса: {actualResult}")
    public void checkStatus(String actualResult) {
        log.info("Проверка статуса: '{}'", actualResult);
        STATUS.shouldBe(visible, ofSeconds(10)).shouldHave(text(actualResult));
    }
}
