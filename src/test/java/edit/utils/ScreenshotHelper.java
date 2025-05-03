package edit.utils;

import edit.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {

    /**
     * Captura una screenshot y la guarda en la carpeta especificada
     *
     * @param testMethodName Nombre del método de test
     * @return Ruta del archivo de screenshot
     */
    public static String captureScreenshot(WebDriver driver, String testMethodName) {
        if(driver == null) {
            return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testMethodName + "_" + timeStamp + ".png";
        String screenshotDir = "./test-results/screenshots/";

        try {
            Path path = Paths.get(screenshotDir);
            if(!Files.exists(path)) {
                Files.createDirectories(path);
            }

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path targetPath = Paths.get(screenshotDir + screenshotName);
            Files.copy(source.toPath(), targetPath);

            return targetPath.toString();
        } catch (IOException e) {
            System.err.println("Error al capturar screenshot: " + e.getMessage());
            return null;
        }
    }
}
