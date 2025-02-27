package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
    public int id;
    public String engineType;
    public String mark;
    public String model;
    public double price;
}
