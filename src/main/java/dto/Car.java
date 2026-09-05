package dto;

import lombok.*;
import utils.enums.Fuel;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String city;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private Integer seats;
    private String carClass;
    private String serialNumber;
    private Double pricePerDay;
    private String about;

}
