package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;


public class ProfilePage {

    @FindBy(how = How.XPATH, using = "//*[text()='Конструктор']")
    private SelenideElement linkConstructor;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement linkLogo;

    @FindBy (how = How.XPATH,using = ".//button[text()='Выход']")
    private SelenideElement btnExitAccount;

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    public SelenideElement fieldInputName;

    @Step("Выход из аккаунта")
    public void userLogout() {
        btnExitAccount.shouldBe(Condition.exist, Duration.ofMillis(1000)).pressEnter();
    }

    @Step("Возврат на основную страницу по нажатию на лого")
    public void clickOnLogoToReturnOnMainPage() {
        linkLogo.shouldBe(Condition.exist, Duration.ofMillis(1000)).click();
    }

    @Step("Возврат на основную страницу по нажатию на кнопку конструктора")
    public void clickOnConstructorLinkToReturnOnMainPage() {
        linkConstructor.shouldBe(Condition.exist, Duration.ofMillis(1000)).click();
    }

}
