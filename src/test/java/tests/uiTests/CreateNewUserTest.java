package tests.uiTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static utils.PropertyReader.getProperty;

public class CreateNewUserTest extends BaseTest {

    String userId = "";

    @BeforeMethod
    public void createUser() {
        mainPage.authorization(login, password)
                .toggleNavigationClick("Users")
                .selectDropDownMenu("Create new");
        userId = createUser.createNewUser(getProperty("firstName"), getProperty("lastName"), getProperty("age"),
                getProperty("sex"), getProperty("money"));
    }

    @Test
    public void showUserID() {
        createUser.createStatus.shouldHave(exactText("Status: Successfully pushed, code: 201"));
        System.out.println(userId);
    }

    @AfterMethod
    public void deleteUser() {
        allDeletePage.deleteUserId(userId);
        allDeletePage.deleteUserStatus.shouldHave(text("Status: 204"));
    }
}
