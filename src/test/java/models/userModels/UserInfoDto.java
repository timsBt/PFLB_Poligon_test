package models.userModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.carModels.CarDto;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    public int id;
    public String firstName;
    public String secondName;
    public int age;
    public String sex;
    public double money;
    public int house;
    public ArrayList<CarDto> cars;
}
