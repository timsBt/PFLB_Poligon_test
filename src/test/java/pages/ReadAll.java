package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class ReadAll {

    String userOnTable = "//td[text() = '%s ']/ancestor::tr//td";

    @Step("Поля пользователя в таблице")
    public ElementsCollection getFieldsUserOnTableList(String userId) {
        return $$x(String.format(userOnTable, userId));
    }
}
