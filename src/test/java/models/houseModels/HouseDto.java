package models.houseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import models.userModels.UserDto;

import java.util.List;

@Data
@Builder
public class HouseDto {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("floorCount")
    @Expose
    private double floorCount;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("parkingPlaces")
    @Expose
    private List<ParkingPlaceDto> parkingPlaces;
    @SerializedName("lodgers")
    @Expose
    private List<UserDto> lodgers;
}
