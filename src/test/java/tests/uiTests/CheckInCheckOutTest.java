package tests.uiTests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static pages.CheckInCheckOutPage.checkText;

@Epic("UI tests")
public class CheckInCheckOutTest extends BaseTest {

    @Test(priority = 1, testName = "Заселение пользователя в дом")
    @Description("Заселение пользователя в дом")
    @Feature("Заселение и выселение пользователя")
    @Story("Заселение пользователя")
    public void settleUser() {
        checkInCheckOutPage.auth().goToLink().addUserToHouse("1", "1");
        Selenide.sleep(3000);
        checkText("Status: Successfully pushed, code: 200");
    }

    @Test(priority = 2, testName = "Выселение пользователя из дома")
    @Description("Выселение пользователя из дома")
    @Feature("Заселение и выселение пользователя")
    @Story("Выселение пользователя")
    public void evictUser() {
        checkInCheckOutPage.auth().goToLink().evictUserFromHouse("1", "1");
        Selenide.sleep(3000);
        checkText("Status: Successfully pushed, code: 200");
    }

    @Test(priority = 3, testName = "Заселение пользователя с пустым полем пользователь")
    @Description("Заселение пользователя с пустым полем пользователь")
    @Story("Заселение и выселение пользователя")
    @Feature("Заселение пользователя")
    public void incorrectSettleUserTest() {
        checkInCheckOutPage.auth().goToLink().addUserToHouse("", "1");
        Selenide.sleep(3000);
        checkText("Status: Incorrect input data");
    }

    @Test(priority = 4, testName = "Заселение пользователя с пустым полем дом")
    @Description("Заселение пользователя с пустым полем дом")
    @Story("Заселение и выселение пользователя")
    @Feature("Заселение пользователя")
    public void incorrectSettleHouseTest() {
        checkInCheckOutPage.auth().goToLink().addUserToHouse("1", "");
        Selenide.sleep(3000);
        checkText("Status: Incorrect input data");
    }

    @Test(priority = 5, testName = "Выселение пользователя с пустым полем пользователь")
    @Description("Заселение пользователя с пустым полем пользователь")
    @Story("Заселение и выселение пользователя")
    @Feature("Выселение пользователя")
    public void incorrectEvictUserTest() {
        checkInCheckOutPage.auth().goToLink().evictUserFromHouse("", "1");
        Selenide.sleep(3000);
        checkText("Status: Incorrect input data");
    }

    @Test(priority = 6, testName = "Выселение пользователя с пустым полем дом")
    @Description("Заселение пользователя с пустым полем дом")
    @Story("Заселение и выселение пользователя")
    @Feature("Выселение пользователя")
    public void incorrectEvictHouseTest() {
        checkInCheckOutPage.auth().goToLink().evictUserFromHouse("1", "");
        Selenide.sleep(3000);
        checkText("Status: Incorrect input data");
    }
}
