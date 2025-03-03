package tests.apiTests;

import adapters.AuthAdapter;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.userModels.PersonDto;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BaseClassApiTest {
    String token;

    @BeforeClass
    public void setUp() {
        token = AuthAdapter.getToken();
    }

    @Step("Проверка количества машин у {userId}")
    public void checkUserCars(final PersonDto person, final int expectedQuantityCar) {
        log.info("Проверка количества машин у '{}'", person);
        final int actualQuantityCar = person.getCars().size();
        assertEquals(expectedQuantityCar, actualQuantityCar);
    }
}
