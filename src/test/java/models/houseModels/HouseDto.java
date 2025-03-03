package models.houseModels;

import lombok.Builder;
import lombok.Data;
import models.userModels.UserDto;

import java.util.List;

@Data
@Builder
public class HouseDto {
    private int id;
    private double floorCount;
    private double price;
    private List<ParkingPlaceDto> parkingPlaces;
    private List<UserDto> lodgers;
}
