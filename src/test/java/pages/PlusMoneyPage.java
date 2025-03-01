package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

public class PlusMoneyPage {
    private static final SelenideElement button = $("button[class='tableButton btn btn-primary']");
    private static final SelenideElement inputUserId = $(id("id_send"));
    private static final SelenideElement inputAmount = $(id("money_send"));
    private static final SelenideElement massage = $("button[class='status btn btn-secondary']");

    @Step("Ввод пользователя {userId}")
    public PlusMoneyPage enterUserId(String userId) {
        inputUserId.setValue(userId);
        return this;
    }

    @Step("Ввод суммы {amount}")
    public PlusMoneyPage enterAmount(String amount) {
        inputAmount.setValue(amount);
        return this;
    }

    @Step("Нажатие кнопки отправки")
    public PlusMoneyPage submit() {
        button.click();
        return this;
    }

    @Step("Вывод сообщения {expectedMessage}")
    public PlusMoneyPage verifySuccessMessage(String expectedMessage) {
        massage.shouldHave(text(expectedMessage), Duration.ofSeconds(130));
        return this;
    }

}
