package dto;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PersonDto {
    @JsonProperty("id")
    public int id;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("second_name")
    public String secondName;
    @JsonProperty("age")
    public int age;
    @JsonProperty("sex")
    public String sex;
    @JsonProperty("money")
    public double money;
    @JsonProperty("house")
    public Object house;
    @JsonProperty("cars")
    public ArrayList<CarDto> cars;
}
