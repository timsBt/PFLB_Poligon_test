package pages;

import com.codeborne.selenide.SelenideElement;
import models.SellingCar;

import static com.codeborne.selenide.Selenide.$x;

public class ReadUserWithCarsPage {
    final SelenideElement userId = $x(
            "//table[@class='tableUser table table-striped table-bordered table-hover']//td"
    );
    final SelenideElement carId = $x(
            "//table[@class='tableCars table table-striped table-bordered table-hover']//td"
    );

    public SellingCar readUserWithCars() {
        return new SellingCar(userId.getText(), carId.getText());
    }
}
