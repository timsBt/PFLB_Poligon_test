package service;

public enum OperationWithCar {
    SELL("sellCar"),
    BUY("buyCar");

    private final String sendName;

    OperationWithCar(String sendName) {
        this.sendName = sendName;
    }

    public String getSendName() {
        return sendName;
    }
}
