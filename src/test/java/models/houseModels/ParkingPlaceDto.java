package models.houseModels;

import lombok.Data;

@Data
public class ParkingPlaceDto {
    private int id;
    private boolean isWarm;
    private boolean isCovered;
    private int placesCount;

    public ParkingPlaceDto(int id, boolean isWarm, boolean isCovered, int placesCount) {
        this.id=id;
        this.isWarm=isWarm;
        this.isCovered=isCovered;
        this.placesCount=placesCount;
    }
}
