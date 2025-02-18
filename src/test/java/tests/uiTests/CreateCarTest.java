package tests.uiTests;

import org.testng.annotations.Test;

public class CreateCarTest extends BaseTest{

    @Test
    public void createCarTest() {
        mainPage.authorization()
                .toggleNavigationClick("Cars")
                        .selectDropDownMenu("Create new");
        createCarsPage.checkCreateCarPage()
                .createNewCar(
                        "gibrid",
                        "toyota",
                        "x5",
                        "2_000_000"
                );
    }
}
