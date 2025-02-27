package carsData;

import lombok.Data;


public class SellingCar {
    private final String userId;
    private final String carId;

    public SellingCar(String userId, String carId) {
        this.userId = userId;
        this.carId = carId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCarId() {
        return carId;
    }
}
