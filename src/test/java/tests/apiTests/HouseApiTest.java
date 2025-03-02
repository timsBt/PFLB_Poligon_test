package tests.apiTests;

import adapters.HouseAdapter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import models.houseModels.ParkingPlaceDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

@Epic("Api tests")
public class HouseApiTest {

    private static String houseId;
    private static List<ParkingPlaceDto> parkingPlaces = new ArrayList<>();
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void addParkingPlaces() {
        parkingPlaces.add(new ParkingPlaceDto(0,true,true,1));
        parkingPlaces.add(new ParkingPlaceDto(0,false,true,2));
        parkingPlaces.add(new ParkingPlaceDto(0,true,false,3));
        parkingPlaces.add(new ParkingPlaceDto(0,false,false,4));
    }

    @Test(testName = "Проверка создания нового дома с корректными параметрами")
    @Description("Проверка создания нового дома с корректными параметрами")
    @Feature("Действия с домами")
    @Story("Создание дома")
    public void createHouse() {
        houseId = HouseAdapter.createHouse(2,500.12,parkingPlaces);
        JsonPath houseDto = HouseAdapter.getHouseInfo(houseId,200);
        softAssert.assertEquals(houseDto.getInt("id"),houseId,"Значения id совпадают");
        softAssert.assertEquals(houseDto.getInt("floorCount"),2,"Значения floorCount совпадают");
        softAssert.assertEquals(houseDto.getDouble("price"),500.12,"Значения price совпадают");
        HouseAdapter.deleteHouse(houseId);
    }

    @Test(testName = "Проверка удаления нового дома с корректными параметрами")
    @Description("Проверка удаления нового дома с корректными параметрами")
    @Feature("Действия с домами")
    @Story("Удаление дома")
    public void deleteHouse() {
        houseId = HouseAdapter.createHouse(2,500.12,parkingPlaces);
        HouseAdapter.deleteHouse(houseId);
        JsonPath houseDto = HouseAdapter.getHouseInfo(houseId,204);
    }

    @Test(testName = "Проверка изменения параметров дома")
    @Description("Проверка изменения параметров дома")
    @Feature("Действия с домами")
    @Story("Изменение дома")
    public void updateHouse() {
        houseId = HouseAdapter.createHouse(2,500.12,parkingPlaces);
        List<ParkingPlaceDto> parkingPlacesUpdate = new ArrayList<>();
        parkingPlacesUpdate.add(new ParkingPlaceDto(0,true,true,5));
        HouseAdapter.updateHouse(houseId,5,300,parkingPlacesUpdate);
        JsonPath houseDto = HouseAdapter.getHouseInfo(houseId,200);
        softAssert.assertEquals(houseDto.getInt("id"),houseId,"Значение id совпадает с новым");
        softAssert.assertEquals(houseDto.getInt("floorCount"),5,"Значение floorCount совпадает с новым");
        softAssert.assertEquals(houseDto.getDouble("price"),300,"Значение price совпадает с новым");
        HouseAdapter.deleteHouse(houseId);
    }
}
