package tests.apiTests;

import adapters.AuthAdapter;
import io.qameta.allure.Step;
import models.userModels.PersonDto;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

public class BaseClassApiTest {
    String token;

    @BeforeClass
    public void setUp() {
      token = AuthAdapter.getToken();
    }

    @Step("Проверка количества машин у {userId}")
    public void checkUserCars(final PersonDto person, final int expectedQuantityCar) {
        final int actualQuantityCar = person.getCars().size();
        assertTrue(actualQuantityCar == expectedQuantityCar);
    }
}
