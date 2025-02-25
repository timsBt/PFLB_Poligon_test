package dto;

import lombok.Data;

@Data
public class CarDto {
    public int id;
    public String engineType;
    public String mark;
    public String model;
    public double price;
}
