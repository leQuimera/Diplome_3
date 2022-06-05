package PageObject;

import Objects.User;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    private SelenideElement linkToAccountInHeader;

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    public SelenideElement fieldInputName;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    public SelenideElement fieldInputEmail;

    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    public SelenideElement fieldInputPassword;

    @FindBy(how = How.CSS, using = ".input__error ")
    public SelenideElement errorMessageWrongPassword;

    @FindBy(how = How.CSS, using = ".Auth_form__3qKeq > button")
    public SelenideElement btnRegistration;

    @Step("Нажимаем кнопку личного кабинета в шапке сайта")
    public void clickOnAccountButton() {
        linkToAccountInHeader.click();
    }

    @Step("Клик на поле Имя")
    public RegistrationPage clickFieldName() {
        fieldInputName.click();
        return this;
    }

    @Step("Вводим данные в поле Имя")
    public RegistrationPage setOnFieldName(String name) {
        fieldInputName.setValue(name);
        return this;
    }

    @Step("Клик на поле Email")
    public RegistrationPage clickFieldEmail() {
        fieldInputEmail.click();
        return this;
    }

    @Step("Вводим данные в поле Email")
    public RegistrationPage setOnFieldEmail(String email) {
        fieldInputEmail.setValue(email);
        return this;
    }

    @Step("Клик на поле пароль")
    public RegistrationPage clickFieldPassword() {
        fieldInputPassword.click();
        return this;
    }

    @Step("Вводим данные в поле пароль")
    public RegistrationPage setOnFieldPassword(String password) {
        fieldInputPassword.setValue(password);
        return this;
    }

    @Step("Нажимаем кнопку регистрации")
    public LoginPage clickOnRegistrationBtn() {
        btnRegistration.shouldBe(Condition.exist, Duration.ofMillis(800)).click();
        btnRegistration.pressEnter();
        return page(LoginPage.class);
    }

    @Step("Появилось сообщение о неверном вводе пароля")
    public boolean errorMessagePasswordIncorrect() {
        return errorMessageWrongPassword.isDisplayed();
    }

    @Step("Пройдена регистрация для пользователя")
    public RegistrationPage userRegistration(User user) {
        return setOnFieldName(user.getName())
            .setOnFieldEmail(user.getEmail())
            .setOnFieldPassword(user.getPassword());

    }
}
