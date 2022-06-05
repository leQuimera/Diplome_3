package PageObject;

import Objects.User;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class LoginPage {

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    public SelenideElement fieldInputEmail;

    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    public SelenideElement fieldInputPassword;

    @FindBy(how = How.CSS, using = ".Auth_form__3qKeq > button")
    public SelenideElement btnEnter;

    @Step("Клик на поле Email")
    public void clickFieldEmail() {
        fieldInputEmail.click();
    }

    @Step("Вводим данные в поле Email")
    public void setOnFieldEmail(String email) {
        fieldInputEmail.setValue(email);
    }

    @Step("Клик на поле пароль")
    public void clickFieldPassword() {
        fieldInputPassword.click();
    }

    @Step("Вводим данные в поле пароль")
    public void setOnFieldPassword(String password) {
        fieldInputPassword.setValue(password);
    }

    @Step("Нажимаем кнопку регистрации")
    public void clickOnEnterBtn() {
        btnEnter.shouldBe(Condition.exist, Duration.ofMillis(1000)).pressEnter();
    }

    @Step("Входим под зарегистрированным пользователем")
    public void loginToSiteByAuthorizedUser(User user) {
        setOnFieldEmail(user.getEmail());
        setOnFieldPassword(user.getPassword());
        clickOnEnterBtn();
    }

}
