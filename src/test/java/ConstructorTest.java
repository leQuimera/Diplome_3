import objects.User;
import pageobject.MainPage;
import utils.BrowserConfigurations;
import utils.EndPoints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BrowserConfigurations {
    private User user;
    MainPage mainPage;

    @Before
    public void setUp() {
        user = User.generateNewRandomUser();
        mainPage = open(EndPoints.URL_MAIN_PAGE, MainPage.class);
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    public void openBunsSectionInConstructor() {
        mainPage.clickOnSauceTab();
        mainPage.clickOnBunTab();

        assertTrue("Не отображается блок булок", mainPage.checkVisibilityBunsSection());
    }

    @Test
    public void openMainSectionInConstructor() {
        mainPage.clickOnSauceTab();

        assertTrue("Не отображается блок начинок", mainPage.checkVisibilityMainsSection());
    }

    @Test
    public void openSaucesSectionInConstructor() {
        mainPage.clickOnSauceTab();

        assertTrue("Не отображается блок соусов", mainPage.checkVisibilitySaucesSection());
    }
}
