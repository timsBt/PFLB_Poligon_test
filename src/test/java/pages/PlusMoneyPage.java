package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class PlusMoneyPage {
    private static final SelenideElement BUTTON = $("button[class='tableButton btn btn-primary']");
    private static final SelenideElement INPUT_USER_ID = $(id("id_send"));
    private static final SelenideElement INPUT_AMOUNT = $(id("money_send"));
    private static final SelenideElement MASSEGE = $("button[class='status btn btn-secondary']");

    @Step("Ввод пользователя {userId}")
    public PlusMoneyPage enterUserId(String userId) {
        INPUT_USER_ID.setValue(userId);
        return this;
    }

    @Step("Ввод суммы {amount}")
    public PlusMoneyPage enterAmount(String amount) {
        INPUT_AMOUNT.setValue(amount);
        return this;
    }

    @Step("Нажатие кнопки отправки")
    public PlusMoneyPage submit() {
        BUTTON.click();
        return this;
    }

    @Step("Вывод сообщения {expectedMessage}")
    public PlusMoneyPage verifySuccessMessage(String expectedMessage) {
        MASSEGE.shouldHave(text(expectedMessage), Duration.ofSeconds(130));
        return this;
    }
}
