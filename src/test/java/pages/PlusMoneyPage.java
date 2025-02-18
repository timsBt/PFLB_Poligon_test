package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

public class PlusMoneyPage {
    SelenideElement button = $("button[class='tableButton btn btn-primary']");
    SelenideElement inputUserId = $(id("id_send"));
    SelenideElement inputAmount = $(id("money_send"));
    SelenideElement massage = $("button[class='status btn btn-secondary']");

    @Step("Ввод пользователя")
    public PlusMoneyPage enterUserId(String userId) {
        inputUserId.setValue(userId);
        return this;
    }

    @Step("Ввод суммы")
    public PlusMoneyPage enterAmount(String amount) {
        inputAmount.setValue(amount);
        return this;
    }

    @Step("Нажатие кнопки отправки")
    public PlusMoneyPage submit() {
        button.click();
        return this;
    }

    @Step("Вывод сообщения")
    public PlusMoneyPage verifySuccessMessage(String expectedMessage) {
        massage.shouldHave(text(expectedMessage));
        return this;
    }

}
