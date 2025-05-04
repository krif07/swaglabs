package edit.hooks;

import edit.driver.DriverFactory;
import edit.utils.ReadProperties;
import org.testng.annotations.*;

public class Hooks {

    public Hooks() {
        super();
    }

    @BeforeSuite
    public void beforeSuite() {
        ReadProperties.setupProperties();
    }
    @BeforeTest
    @Parameters("browser")
    public void beforeTest(String browser) {
        System.out.println("2 ----------------------------------------------");
        System.out.println(ReadProperties.getBrowserType());
        ReadProperties.setBrowserType(browser);
        System.out.println(ReadProperties.getBrowserType());
        System.out.println("2 ----------------------------------------------");
    }
    @BeforeMethod
    public void setup() {
        DriverFactory.getDriver();
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.cleanupDriver();
    }
}
