package tests.apiTests;

import adapters.AuthAdapter;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.userModels.PersonDto;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

@Log4j2
public class BaseClassApiTest {
    String token;
    protected static Properties properties;

    @BeforeClass
    public void setUp() {
        token = AuthAdapter.getToken();
        loadProperties();
    }

    @Step("Проверка количества машин у {userId}")
    public void checkUserCars(final PersonDto person, final int expectedQuantityCar) {
        log.info("Проверка количества машин у '{}'", person);
        final int actualQuantityCar = person.getCars().size();
        assertEquals(expectedQuantityCar, actualQuantityCar);
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурационного файла", e);
        }
    }
}
