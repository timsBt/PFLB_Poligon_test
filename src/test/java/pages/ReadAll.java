package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ReadAll {

    String userOnTable = "//td[text() = '%s ']/ancestor::tr//td";

    public SelenideElement sortButton = $x("//button[@class = 'btn btn-secondary'][2]");

    @Step("Поля пользователя {userId} в таблице")
    public ElementsCollection getFieldsUserOnTableList(String userId) {
        return $$x(String.format(userOnTable, userId));
    }
}
