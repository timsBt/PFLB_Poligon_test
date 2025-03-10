package models.userModels;

import lombok.Builder;
import lombok.Data;
import models.carModels.CarDto;

import java.util.ArrayList;

@Data
@Builder
public class PersonDto {
    public int id;
    public String firstName;
    public String secondName;
    public int age;
    public String sex;
    public double money;
    public Object house;
    public ArrayList<CarDto> cars;
}