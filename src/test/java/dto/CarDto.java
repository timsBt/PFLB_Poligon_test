package dto;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarDto {
    @JsonProperty("id")
    public int id;
    @JsonProperty("engine_type")
    public String engineType;
    @JsonProperty("mark")
    public String mark;
    @JsonProperty("model")
    public String model;
    @JsonProperty("price")
    public double price;
}
