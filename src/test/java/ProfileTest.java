import Objects.User;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.ProfilePage;
import PageObject.RegistrationPage;
import Utils.BrowserConfigurations;
import Utils.EndPoints;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ProfileTest extends BrowserConfigurations {
    private User user;

    @Before
    public void setUp() {
        user = User.generateNewRandomUser();
        RegistrationPage registrationPage = openYandex(EndPoints.URL_REGISTRATION_PAGE, RegistrationPage.class);
        //RegistrationPage registrationPage = open(EndPoints.URL_REGISTRATION_PAGE, RegistrationPage.class);
        registrationPage.userRegistration(user)
                .clickOnRegistrationBtn();

        LoginPage loginPage = open(EndPoints.URL_LOGIN_PAGE, LoginPage.class);
        loginPage.loginToSiteByAuthorizedUser(user);

        MainPage mainPage = page(MainPage.class);
        mainPage.clickOnAccountButton();
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Переход из кабинета в конструктор по нажатию на кнопку конструктора")
    public void transitionToMainPageByConstructorButton() {
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickOnConstructorLinkToReturnOnMainPage();

        Selenide.webdriver().shouldHave(url(EndPoints.URL_MAIN_PAGE));
    }

    @Test
    @DisplayName("Переход из кабинета в конструктор по нажатию на лого")
    public void transitionToMainPageByLogo() {
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickOnLogoToReturnOnMainPage();

        Selenide.webdriver().shouldHave(url(EndPoints.URL_MAIN_PAGE));
    }

    @Test
    @DisplayName("Выход из аккаунта по нажатию на кнопку выхода")
    public void userLogoutFromSiteByButtonInProfile() {
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.userLogout();

        Selenide.webdriver().shouldHave(url(EndPoints.URL_LOGIN_PAGE));
    }

    }
