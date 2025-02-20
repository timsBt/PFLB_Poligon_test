package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    public int id;
    public String firstName;
    public String secondName;
    public int age;
    public String sex;
    public double money;
}
