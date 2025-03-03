package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.carsData.Cars;
import models.carsData.SellingCar;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class ReadUserWithCarsPage {

    final SelenideElement userId = $x(
            "//table[@class='tableUser table table-striped table-bordered table-hover']//td"
    );

    final SelenideElement carId = $x(
            "//table[@class='tableCars table table-striped table-bordered table-hover']//td"
    );

    final ElementsCollection cars = $$x(
            "//table[contains(@class, 'tableCars')]//tbody/tr"
    );

    final SelenideElement inputId = $x("//input[@type='number' and @id='user_input']");
    final SelenideElement readButton = $x("//button[@class='tableButton btn btn-primary']");

    @Step("Чтение id {userId} и {carId} для покупки и продажи машины")
    public SellingCar readUserWithCars() {
        log.info("Чтение id {userId} и {carId} для покупки и продажи машины");
        return new SellingCar(userId.getText(), carId.getText());
    }

    @Step("Проверка количества машин у User по Id: {id}")
    public List<Cars> getCarsIdList(String id) {
        log.info("Проверка количества машин у User по Id: '{}'", id);
        inputId.sendKeys(id);
        readButton.click();

        return cars.stream().map(row -> {
            ElementsCollection columns = row.$$("td");
            return new Cars(
                    getColumnText(columns, 0),
                    getColumnText(columns, 1),
                    getColumnText(columns, 2),
                    getColumnText(columns, 3),
                    getColumnText(columns, 4)
            );
        }).collect(Collectors.toList());
    }

    @Step("Получение данных {userId} из ячеек")
    private String getColumnText(ElementsCollection columns, int index) {
        log.info("Получение данных {userId} из ячеек");
        return columns.size() > index ? columns.get(index).getText() : "";
    }
}
