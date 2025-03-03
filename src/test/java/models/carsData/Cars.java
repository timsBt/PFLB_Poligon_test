package models.carsData;

public class Cars {
    private final String id;
    private final String engineType;
    private final String mark;
    private final String model;
    private final String price;

    public Cars(String id, String engineType, String mark, String model, String price) {
        this.id = id;
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }
}
