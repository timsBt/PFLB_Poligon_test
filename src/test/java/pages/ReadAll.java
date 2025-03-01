package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class ReadAll {

    private static final String USER_ON_TABLE = "//td[text() = '%s ']/ancestor::tr//td";
    private static final SelenideElement SORT_BUTTON = $x("//button[@class = 'btn btn-secondary'][2]");

    @Step("Поля пользователя {userId} в таблице")
    public ElementsCollection getFieldsUserOnTableList(String userId) {
        return $$x(String.format(USER_ON_TABLE, userId));
    }

    @Step("Клик по кнопке Sort")
    public ReadAll clickOnSortButton() {
        SORT_BUTTON.shouldBe(visible, ofSeconds(10)).click();
        return this;
    }
}
