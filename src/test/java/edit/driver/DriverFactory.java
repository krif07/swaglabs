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

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static WebDriver createDriver() {
        String browser = System.getProperty("browserType");
        if(browser == null || browser.isEmpty()) {
            browser = ReadProperties.getBrowserType();
        }
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("headless");
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
                options.addArguments("--no-sandbox");
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
