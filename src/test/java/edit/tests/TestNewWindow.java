package edit.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class TestNewWindow {
    private WebDriver driver;
    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().window().maximize();
    }
    @AfterSuite
    void tearDown() {
        driver.quit();
    }
    @Test
    void testGotoNewWindow() {
        WebElement newTabBtn = driver.findElement(By.id("tabButton"));
        newTabBtn.click();

        Set<String> windowHandles = driver.getWindowHandles();
        String originalWindowHandle = driver.getWindowHandle();

        Iterator<String> iterator = windowHandles.iterator();
        String newWindowHandle = null;

        while (iterator.hasNext()) {
            String handle = iterator.next();
            if (!handle.equals(originalWindowHandle)) {
                newWindowHandle = handle;
                break; // Hemos encontrado la nueva ventana
            }
        }

        // 7. Cambiar el foco a la nueva ventana
        if (newWindowHandle != null) {
            driver.switchTo().window(newWindowHandle);

            WebElement titleElement = driver.findElement(By.id("sampleHeading"));
            Assert.assertEquals("This is a sample page", titleElement.getText());
            // 8. (Opcional) Cerrar la nueva ventana y volver a la original
            driver.close(); // Cierra la ventana actual (la nueva ventana)
            driver.switchTo().window(originalWindowHandle); // Vuelve a la ventana original
        } else {
            System.out.println("No se encontró una nueva ventana.");
        }

    }
}
