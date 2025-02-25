package pages;

import carsData.Cars;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import carsData.SellingCar;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

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

    public SellingCar readUserWithCars() {
        return new SellingCar(userId.getText(), carId.getText());
    }
    public List<Cars> getCarsIdList(SellingCar sellingCar) {
       return getCarsIdList(sellingCar.getUserId());
    }

    public List<Cars> getCarsIdList(String id) {
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

    private String getColumnText(ElementsCollection columns, int index) {
        return columns.size() > index ? columns.get(index).getText() : "";
    }
}
