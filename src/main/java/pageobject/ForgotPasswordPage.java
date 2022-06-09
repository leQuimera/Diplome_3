package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    public static SelenideElement lincEnterIfNotForgotLogin;

    public void clickLoginFromResetPasswordPageButton() {
        lincEnterIfNotForgotLogin.shouldBe(Condition.visible).click();
    }
}
