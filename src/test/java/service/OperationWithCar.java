package service;

import lombok.Data;

public enum OperationWithCar {
    SELL("sellCar"),
    BUY("buyCar");

    private String sendName;

    OperationWithCar(String sendName) {
        this.sendName = sendName;
    }

    public String getSendName() {
        return sendName;
    }
}
