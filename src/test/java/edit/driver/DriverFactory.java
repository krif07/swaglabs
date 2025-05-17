package edit.driver;

import edit.utils.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverInfo;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static WebDriver createDriver() {
        String browser = System.getProperty("browserType");
        if(browser == null || browser.isEmpty()) {
            browser = ReadProperties.getBrowserType();
        }
        WebDriver driver;

        Map<String, String> prefs = new HashMap<>();
        prefs.put("download.default_directory", "src/test/resources");
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("incognito");
                driver = new FirefoxDriver(options);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                driver = new EdgeDriver(options);
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("incognito");
                options.addArguments("disable-extensions");
                options.addArguments("make-default-browser");
                options.addArguments("--no-sandbox");
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

    public static void cleanupDriver() {
        if(driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        if(driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }
}
