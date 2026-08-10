package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveUser() {
        User user = User.builder()
                .username("mammoth.isr@gmail.com")
                .password("Zaqxsw39!")
                .build();
        return user;
    }
}
