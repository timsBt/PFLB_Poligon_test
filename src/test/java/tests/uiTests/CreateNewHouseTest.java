package tests.uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

@Epic("UI tests")
public class CreateNewHouseTest extends BaseTest{

    String houseId;
    String floorCountHouseOnTable = "//div/table/tbody/tr/td[1][text()='%s']/../td[2]";

    @BeforeMethod
    public void openPageCreateHouse() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Houses")
                .selectDropDownMenu("Create new");
    }

    @Test(testName = "Проверка создания дома со всеми заполнеными полями",
            description = "Проверка создания дома со всеми заполненными полями")
    @Description("Проверка создания дома со всеми заполненными полями")
    @Feature("Действия с домами")
    @Story("Создание дома")
    public void createNewHouse() {
        houseId = createHousePage.createNewHouse(
                "5",
                "200.01",
                "5",
                "4",
                "10",
                "11");
        createHousePage.createHouseStatus.shouldHave(exactText("Status: Successfully pushed, code: 201"));
    }

    @Test(testName = "Проверка создания дома без паркинга",
            description = "Проверка создания дома без паркинга")
    @Description("Проверка создания дома без паркинга")
    @Feature("Действия с домами")
    @Story("Создание дома")
    public void createNewHouseNoParking() {
        houseId = createHousePage.createNewHouse(
                "5",
                "100",
                "",
                "",
                "",
                "");
        createHousePage.createHouseStatus.shouldHave(exactText("Status: Successfully pushed, code: 201"));
    }

    @Test(testName = "Проверка округления дробного кол-ва этажей при создании", enabled = false,
            description = "Проверка создания дома с дробным количеством этажей - округление этажности до целого в меньшую сторону")
    @Description("Проверка создания дома с дробным количеством этажей - округление до целого в меньшую сторону")
    @Feature("Действия с домами")
    @Story("Создание дома")
    public void createNewHouseFractionalNumberOfFloors() {
        houseId = createHousePage.createNewHouse(
                "5,9",
                "100",
                "",
                "",
                "",
                "");
        mainPage.toggleNavigationClick("Houses")
                .selectDropDownMenu("Read all");
        sleep(2000);
        $x(String.format(floorCountHouseOnTable,houseId)).shouldHave(exactText("5"));
    }

    @Test(testName = "Проверка отказа создания дома с невалидными данными",
            description = "Проверка отказа создания дома с невалидными данными",
            dataProvider = "createHouseData")
    @Description("Проверка отказа создания дома с невалидными данными")
    @Feature("Действия с домами")
    @Story("Создание дома")
    public void createNewHouseNegative(String floor, String price, String parkingFirst,
                                       String parkingSecond, String parkingThird,
                                       String parkingFourth, String createStatus) {
        houseId = createHousePage.createNewHouse(
                floor,
                price,
                parkingFirst,
                parkingSecond,
                parkingThird,
                parkingFourth);
        createHousePage.createHouseStatus.shouldHave(exactText(createStatus));
    }

    @DataProvider(name = "createHouseData")
    public Object[][] createHouseData() {
        return new Object[][] {
                {"","100","1","2","3","4","Status: Invalid input data"},
                {"5","","1","2","3","4","Status: Invalid input data"},
                {"-5","100","","","","","Status: Invalid input data"},
                {"5","100","-5","","","","Status: Invalid input data"},
                {"5","-10","","","","","Status: Invalid input data"}
        };
    }

    @AfterMethod
    public void deleteHouse() {
        if (!houseId.isEmpty()) {
            allDeletePage.deleteHouseId(houseId);
            allDeletePage.deleteHouseStatus.shouldHave(text("204"));
        }
    }
}
