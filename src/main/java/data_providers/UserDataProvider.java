package data_providers;

import dto.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    @DataProvider
    public Iterator<User> dataProviderWrongNameOrPasswordOrEmail() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/wrong_name_email_password.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                users.add(User.builder()
                        .firstName(fields[0])
                        .lastName(fields[1])
                        .username(fields[2])
                        .password(fields[3])
                        .build());
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
        }
        return users.listIterator();
    }
}
