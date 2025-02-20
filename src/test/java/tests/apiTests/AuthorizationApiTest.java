package tests.apiTests;

import adapters.AuthAdapter;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.PropertyReader.getProperty;

@Epic("AuthorizationApiTest")
public class AuthorizationApiTest extends AuthAdapter {

    @Test(testName = "Получение токена авторизации первым способом")
    @Description("Получение токена авторизации первым способом")
    @Story("Авторизация")
    @Feature("Авторизация")
    public void getAccessToken() {
        String token = getToken(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));
        Assert.assertTrue(isValidJwt(token));
    }

    @Test(testName = "Получение токена авторизации вторым способом")
    @Description("Получение токена авторизации вторым способом")
    @Story("Авторизация")
    @Feature("Авторизация")
    public void getAccessToken2() {
        String token = getToken2(
                System.getProperty("login", getProperty("login")),
                System.getProperty("password", getProperty("password")));

        Assert.assertTrue(isValidJwt(token));
    }
}
