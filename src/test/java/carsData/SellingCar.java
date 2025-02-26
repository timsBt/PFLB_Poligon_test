package carsData;

import lombok.Data;

@Data
public class SellingCar {
    private final String userId;
    private final String carId;

    public SellingCar(String userId, String carId) {
        this.userId = userId;
        this.carId = carId;
    }
}
