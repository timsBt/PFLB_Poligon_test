package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

@Log4j2
public class ReadAll {

    private static final String USER_ON_TABLE = "//td[text() = '%s ']/ancestor::tr//td";
    private static final SelenideElement SORT_BUTTON = $x("//button[@class = 'btn btn-secondary'][2]");

    @Step("Поля пользователя '{userId}' в таблице")
    public ElementsCollection getFieldsUserOnTableList(String userId) {
        log.info("Получение полей пользователя: '{}' в таблице", userId);
        return $$x(String.format(USER_ON_TABLE, userId));
    }

    @Step("Клик по кнопке Sort")
    public ReadAll clickOnSortButton() {
        log.info("Клик по кнопке Sort");
        SORT_BUTTON.shouldBe(visible, ofSeconds(10)).doubleClick();
        sleep(2000);
        return this;
    }
}
