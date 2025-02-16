package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PlusMoneyPage {
    SelenideElement button = $x("button[class='tableButton btn btn-primary']");
    SelenideElement inputUserId = $("input[id = 'id_send']");
    SelenideElement inputAmount = $("input[id='money_send']");
    SelenideElement massage = $("button.status.btn.btn-secondary");
    public void enterUserId(String userId) {
        inputUserId.setValue(userId);
    }

    public void enterAmount(String amount) {
        inputAmount.setValue(amount);
    }

    public void submit() {
        button.click();
    }

    public void verifySuccessMessage() {
        massage.shouldHave(text("Status: Successfully pushed, code: 200"));
    }
}
