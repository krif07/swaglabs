package edit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static String BROWSER_TYPE;
    private static String URL;
    private static int WAIT_TIME_OUT;
    private static String TAKE_PICTURES;
    private static String ENVIRONMENT;

    public static void setupProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/java/properties/config.properties"
            );
            properties.load(file);
            BROWSER_TYPE = properties.getProperty("browser.type").trim();
            URL = properties.getProperty("url").trim();
            String time = properties.getProperty("wait.timeout.seconds").trim();
            WAIT_TIME_OUT = Integer.parseInt(time);
            TAKE_PICTURES = properties.getProperty("take.pictures");
            ENVIRONMENT = properties.getProperty("environment");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getURL() {
        return URL;
    }
    public static String getBrowserType() {
        return BROWSER_TYPE;
    }
    public static int getWaitTimeOut() {
        return WAIT_TIME_OUT;
    }
    public static String getTakePictures() {
        return TAKE_PICTURES;
    }
    public static String getEnvironment() { return ENVIRONMENT; }
}
