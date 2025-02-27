package models;

import lombok.Builder;
import lombok.Data;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public int id;
    public String firstName;
    public String secondName;
    public int age;
    public String sex;
    public double money;
}
