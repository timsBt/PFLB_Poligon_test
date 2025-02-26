package models.userModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
