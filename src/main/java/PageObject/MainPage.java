package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')]")
    private ElementsCollection tabsOfIngredients;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer__Xu3Mo')]/h2[contains(@class, 'text')]")
    private ElementsCollection headersOfIngredients;

    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    private SelenideElement linkToAccountInHeader;

    @FindBy(how = How.CSS, using = ".BurgerConstructor_basket__totalContainer__2Z-ho ~ button")
    private SelenideElement btnInBasketForm;

    @FindBy (how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')][1]")
    private SelenideElement tabBun;

    @FindBy (how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')][2]")
    private SelenideElement tabSauce;

    @FindBy (how = How.XPATH, using = ".//div[contains(@class, 'tab_tab__1SPyG')][3]")
    private SelenideElement tabMain;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement headerBun;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement headerSauce;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement headerMain;

    @Step("Кликаем по вкладка в конструкторе")
    public void clickToTab(String nameOfTab) {
        tabsOfIngredients.findBy(Condition.text(nameOfTab)).click();
    }

    @Step("Проверяем, открыта ли вкладка в конструкторе")
    public void isCurrentTabOpen(String nameOfTab) {
        tabsOfIngredients.findBy(Condition.text(nameOfTab))
                .shouldHave(Condition.cssClass("tab_tab_type_current__2BEPc"));
    }

    @Step("Нажимаем кнопку личного кабинета в шапке сайта")
    public LoginPage clickOnAccountButton() {
        linkToAccountInHeader.shouldHave(Condition.exist, Duration.ofMillis(8000)).click();
        return page(LoginPage.class);
    }

    @Step("Проверяем, что пользователь не зашел на сайт")
    public boolean isUserLoginOff() {
        return btnInBasketForm.shouldHave(Condition.text("Войти в аккаунт")).isDisplayed();
    }

    @Step("Проверяем, что пользователь зашел на сайт")
    public boolean isUserLoginOn() {
        return btnInBasketForm.shouldHave(Condition.text("Оформить заказ")).isDisplayed();
    }

    @Step("Нажимаем кнопку в корзине заказа")
    public LoginPage clickOnAccountButtonInBasket() {
        btnInBasketForm.click();
        return page(LoginPage.class);
    }

    @Step("Выбираем закладку булок")
    public void clickOnBunTab() {
        tabBun.click();
    }

    @Step("Проверяем видимость блока с булками")
    public boolean checkVisibilityBunsSection() {
        return headerBun.isDisplayed();
    }

    @Step("Выбираем закладку соусов")
    public void clickOnSauceTab() {
        tabSauce.click();
    }

    @Step("Проверяем видимость блока с соусами")
    public boolean checkVisibilitySaucesSection() {
        return headerSauce.isDisplayed();
    }

    @Step("Выбираем закладку начинок")
    public MainPage clickOnMainTab() {
        tabMain.click();
        return this;
    }

    @Step("Проверяем видимость блока с начинками")
    public boolean checkVisibilityMainsSection() {
        return headerMain.isDisplayed();
    }

}
