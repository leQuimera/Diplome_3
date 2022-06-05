package Utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

public class BrowserConfigurations {

    public static <PageObjectClass> PageObjectClass openYandex(String relativeOrAbsoluteUrl, Class<PageObjectClass> pageObjectClassClass) {
        System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1200x600");
        options.setBinary(System.getenv("ENV_Name") + "/Yandex/YandexBrowser/Application/browser.exe");
        try {
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
        } catch (Exception e) {
            System.out.println("ERROR setWebDriver : " + e);
        }

        return open(relativeOrAbsoluteUrl, pageObjectClassClass);
    }
}
