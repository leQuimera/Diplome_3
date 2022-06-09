package utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

public class BrowserConfigurations {

    public static <PageObjectClass> PageObjectClass openYandex(String relativeOrAbsoluteUrl, Class<PageObjectClass> pageObjectClassClass) {

        String PATH_YANDEX_DRIVER = "src/main/resources/yandexdriver.exe";


        System.setProperty("webdriver.chrome.driver", PATH_YANDEX_DRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1200x600");
        try {
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
        } catch (Exception e) {
            System.out.println("ERROR setWebDriver : " + e);
        }

        return open(relativeOrAbsoluteUrl, pageObjectClassClass);
    }
}
