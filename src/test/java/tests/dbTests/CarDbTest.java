package tests.dbTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.RestService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static adapters.AuthAdapter.getToken;
import static adapters.CarAdapter.createCar;
import static adapters.UserAdapter.createUser;
import static db.DbConnect.closeConnect;
import static db.DbConnect.executeSqlQuery;
import static service.OperationWithCar.BUY;
import static utils.PropertyReader.getProperty;

@Epic("DB tests")
public class CarDbTest {

    private String carId;
    private String userId;
    RestService restService = new RestService();
    SoftAssert softAssert = new SoftAssert();
    String sqlQuery = "SELECT * FROM car WHERE id = '";

    @BeforeMethod
    public void createCarAndUser() {
        carId = createCar(
                getProperty("engineType"),
                getProperty("mark"),
                getProperty("model"),
                getProperty("price"));
        userId = createUser(
                getProperty("firstName"),
                getProperty("lastName"),
                getProperty("age"),
                getProperty("sex"),
                getProperty("money"));
        restService.buyOrSellCar(userId, carId, BUY, getToken());
    }

    @Test(testName = "Проверка информации по Car в БД",
            description = "Проверка информации по Car в БД")
    @Description("Проверка информации по Car в БД")
    @Feature("Проверка Car в БД")
    @Story("Проверка сохранения Car в БД")
    public void checkUserDBTest() throws SQLException {
        ResultSet rs = executeSqlQuery(sqlQuery + carId + "'");
        while (rs.next()) {
            softAssert.assertEquals(rs.getString("id"), carId);
            softAssert.assertEquals(rs.getString("mark"), getProperty("mark"));
            softAssert.assertEquals(rs.getString("model"), getProperty("model"));
            softAssert.assertEquals(rs.getString("price"), getProperty("price"));
            softAssert.assertEquals(rs.getString("person_id"), userId);
            softAssert.assertAll();
        }
    }

    @AfterMethod
    public void closeConnection() throws SQLException {
        closeConnect();
    }
}
