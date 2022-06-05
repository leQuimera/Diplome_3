package Objects;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    @Step("Create random user")
    public static User generateNewRandomUser() {
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(6, 8, true);
        final String name = faker.name().username();
        Allure.addAttachment("Email: ", email);
        Allure.addAttachment("Пароль: ", password);
        Allure.addAttachment("Имя: ", name);
        return new User(email, password, name);
    }

}
