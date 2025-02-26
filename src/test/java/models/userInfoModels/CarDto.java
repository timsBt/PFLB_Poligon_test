package models.userInfoModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    public int id;
    public String engineType;
    public String mark;
    public String model;
    public int price;
}
