import Objects.User;
import PageObject.RegistrationPage;
import Utils.BrowserConfigurations;
import Utils.EndPoints;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertTrue;

public class NewUserRegistrationTest extends BrowserConfigurations {
    private User user;
    RegistrationPage registrationPage;

    @Before
    public void setUp() {
        user = User.generateNewRandomUser();
        registrationPage = openYandex(EndPoints.URL_REGISTRATION_PAGE, RegistrationPage.class);
        //registrationPage = open(EndPoints.URL_REGISTRATION_PAGE, RegistrationPage.class);
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Регистрация нового пользователя с корректными данными")
    public void newUserRegistrationValid() {
        registrationPage.userRegistration(user)
                .clickOnRegistrationBtn();

        Selenide.webdriver().shouldHave(url(EndPoints.URL_LOGIN_PAGE));
    }

    @Test
    @DisplayName("Регистрация нового пользователя с некорректными данными")
    public void newUserRegistrationNonValid() {
        user.setPassword("12345");
        registrationPage.userRegistration(user)
                .clickOnRegistrationBtn();

        assertTrue(registrationPage.errorMessagePasswordIncorrect());
    }

    @Test
    @DisplayName("Клик по кнопке личного кабинета")
    public void transitionOnAccountPageAfterClickOnAccountButton() {
        registrationPage.clickOnAccountButton();

        Selenide.webdriver().shouldHave(url(EndPoints.URL_LOGIN_PAGE));
    }
}
