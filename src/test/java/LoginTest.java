import Objects.User;
import PageObject.LoginPage;
import PageObject.MainPage;
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
import static org.junit.Assert.assertTrue;

public class LoginTest extends BrowserConfigurations {
    User user;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    //Пак тестов сделан на Яндекс браузере. Если поменять местами комментарии
    // (закомментить Яндекс и раскоментить стандартный оператор), то тесты пройдут под Chrome

    @Before
    public void setUp() {
        user = User.generateNewRandomUser();
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
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
    @DisplayName("Вход на сайт через кнопку входа в аккаунт в конструкторе")
    public void enterOnSiteFromAccountBtnInBasket() {
        registrationPage.userRegistration(user)
                .clickOnRegistrationBtn();
        open(EndPoints.URL_MAIN_PAGE);
        mainPage.clickOnAccountButtonInBasket()
                .loginToSiteByAuthorizedUser(user);

        Selenide.webdriver().shouldHave(url(EndPoints.URL_MAIN_PAGE));
        assertTrue(mainPage.isUserLoginOn());
    }

    @Test
    @DisplayName("Вход на сайт через кнопку входа в аккаунт в шапке сайта")
    public void enterOnSiteFromAccountBtn() {
        registrationPage.userRegistration(user)
                .clickOnRegistrationBtn();
        open(EndPoints.URL_MAIN_PAGE);
        mainPage.clickOnAccountButton()
                .loginToSiteByAuthorizedUser(user);

        Selenide.webdriver().shouldHave(url(EndPoints.URL_MAIN_PAGE));
        assertTrue(mainPage.isUserLoginOn());
    }

    @Test
    @DisplayName("Вход пользователя на сайт сразу после регистрации")
    public void enterOnSiteAfterRegistration() {
        registrationPage.userRegistration(user)
                .clickOnRegistrationBtn()
                .clickFieldEmail();

        open(EndPoints.URL_LOGIN_PAGE, LoginTest.class);
        loginPage.loginToSiteByAuthorizedUser(user);

        Selenide.webdriver().shouldHave(url(EndPoints.URL_MAIN_PAGE));
        assertTrue(mainPage.isUserLoginOn());

    }

}
