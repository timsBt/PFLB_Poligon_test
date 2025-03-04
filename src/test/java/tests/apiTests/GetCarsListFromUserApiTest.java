package tests.apiTests;

import adapters.CarAdapter;
import adapters.UserAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.userModels.PersonDto;
import org.testng.annotations.Test;
import service.OperationWithCar;
import service.RestService;

@Epic("Api tests")
public class GetCarsListFromUserApiTest extends BaseClassApiTest {

    @Test(testName = "API: Получение списка машин по userId",
            description = "Проверка, что API возвращает список машин")
    @Description("Сравнение списка машин пользователя")
    @Feature("API-тестирование")
    @Story("GET /cars?userId={userId}")
    public void getCarsListApiTest() {
        RestService restService = new RestService();

        // Читаем данные из пропертей
        String firstName = properties.getProperty("firstName");
        String lastName = properties.getProperty("lastName");
        String age = properties.getProperty("age");
        String sex = properties.getProperty("sex");
        String money = properties.getProperty("money");

        String engineType = properties.getProperty("engineType");
        String mark = properties.getProperty("mark");
        String model = properties.getProperty("model");
        String price = properties.getProperty("price");

        // Создание пользователя
        String userId = UserAdapter.createUser(firstName, lastName, age, sex, money);

        // Создание двух машин
        String carId1 = CarAdapter.createCar(engineType, mark, model, price);
        String carId2 = CarAdapter.createCar(engineType, "BMW", "X5", "30000");

        // Покупка машин пользователем
        restService.buyOrSellCar(userId, carId1, OperationWithCar.BUY, token);
        restService.buyOrSellCar(userId, carId2, OperationWithCar.BUY, token);

        // Получение списка машин у пользователя и проверка
        PersonDto getListCars = restService.getUserWithCars(userId);
        checkUserCars(getListCars, 2);
    }
}
