package edit.pages;

import edit.driver.DriverFactory;
import edit.utils.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(ReadProperties.getWaitTimeOut()));
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, ReadProperties.getWaitTimeOut()), this);
    }
    public void navigateTo(String url) {
        driver.get(url);
    }
    public void click(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
    public void click(By button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
    public void sendKeys(WebElement input, String text) {
        wait.until(ExpectedConditions.visibilityOf(input));
        input.sendKeys(text);
    }
    public String getText(WebElement textElement) {
        return wait.until(ExpectedConditions.visibilityOf(textElement)).getText();
    }
    public String getText(By textElement) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textElement));
        return element.getText();
    }
}
