package utils;

import dto.Car;
import net.datafaker.Faker;

public class CarFactory {
    public static Car positiveCar()
    {
       Car car = Car.builder()
               .location("Haifa")
               .manufacture("Tesla")
               .model("Model Y")
               .year("2026")
               .fuel("Electric")
               .seats(5)
               .carClass("Sedan")
               .carRegistrationNumber("41551302")
               .price(600)
               .build();
       return car;
    }
}
