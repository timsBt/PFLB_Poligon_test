package tests.dbTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.ResultSet;
import java.sql.SQLException;

import static adapters.UserAdapter.createUser;
import static adapters.UserAdapter.deleteUser;
import static db.DbConnect.closeConnect;
import static db.DbConnect.executeSqlQuery;
import static utils.PropertyReader.getProperty;

@Epic("DB tests")
public class UserDbTest {

    private String userId;
    SoftAssert softAssert = new SoftAssert();
    String sqlQuery = "SELECT * FROM person WHERE id = '";

    @BeforeMethod
    public void setUpCreateUser() {
        userId = createUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
    }

    @Test(testName = "Проверка информации по ID пользователя в БД",
            description = "Проверка информации по ID пользователя в БД")
    @Description("Проверка информации по ID пользователя в БД")
    @Feature("Проверка Пользователя в БД")
    @Story("Проверка сохранения пользователя в БД")
    public void checkUserDBTest() throws SQLException {
        ResultSet rs = executeSqlQuery(sqlQuery + userId + "'");
        while (rs.next()) {
            softAssert.assertEquals(rs.getString("id"), userId);
            softAssert.assertEquals(rs.getString("age"), getProperty("age"));
            softAssert.assertEquals(rs.getString("first_name"), getProperty("firstName"));
            softAssert.assertEquals(rs.getString("money").substring(0, rs.getString("money").indexOf('.')),
                    getProperty("money"));
            softAssert.assertEquals(rs.getString("second_name"), getProperty("lastName"));
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void deleteUserTearDown() throws SQLException {
        deleteUser(userId);
        closeConnect();
    }
}
