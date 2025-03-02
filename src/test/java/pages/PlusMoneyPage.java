package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.id;

@Log4j2
public class PlusMoneyPage {
    private static final SelenideElement BUTTON = $("button[class='tableButton btn btn-primary']");
    private static final SelenideElement INPUT_USER_ID = $(id("id_send"));
    private static final SelenideElement INPUT_AMOUNT = $(id("money_send"));
    private static final SelenideElement MESSAGE = $("button[class='status btn btn-secondary']");
    private static final SelenideElement USER_ID_TEXT = $x("//th[contains(text(), 'USER')]");

    @Step("Ввод пользователя: {userId}")
    public PlusMoneyPage enterUserId(String userId) {
        log.info("Ввод пользователя: '{}'", userId);
        USER_ID_TEXT.shouldBe(visible, ofSeconds(10));
        INPUT_USER_ID.setValue(userId);
        return this;
    }

    @Step("Ввод суммы: {amount}")
    public PlusMoneyPage enterAmount(String amount) {
        log.info("Ввод суммы: '{}'", amount);
        INPUT_AMOUNT.setValue(amount);
        return this;
    }

    @Step("Нажатие кнопки отправки")
    public PlusMoneyPage submit() {
        log.info("Нажатие кнопки отправки");
        BUTTON.click();
        return this;
    }

    @Step("Вывод сообщения: {expectedMessage}")
    public PlusMoneyPage verifySuccessMessage(String expectedMessage) {
        log.info("Вывод сообщения: '{}'", expectedMessage);
        MESSAGE.shouldHave(text(expectedMessage), Duration.ofSeconds(130));
        return this;
    }
}
