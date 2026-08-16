package utils;

import dto.User;
import net.datafaker.Faker;
import static utils.PropertiesReader.*;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveRegistrationUser() {
      User user = User.builder()
              .firstName(faker.name().firstName())
              .lastName(faker.name().lastName())
              .username(faker.internet().emailAddress())
              .password(PropertiesReader.getProperty("base.properties","password_for_registration"))
              .build();
      return user;
    };

    public static User positiveUser() {
        User user = User.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
        return user;
    }

    public static User wrongPasswordUser() {
        return User.builder()
                .username(getProperty("base.properties","email"))
                .password("123")
                .build();
    }

}
