package stepDefinitions.hooks;

import edit.driver.DriverFactory;
import edit.utils.ReadProperties;
import io.cucumber.java.Before;
import org.testng.annotations.*;

public class Hooks {

    public Hooks() {
        super();
    }

    @Before
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
        ReadProperties.setupProperties();
    }
    @BeforeTest
    @Parameters("browser")
    public void beforeTest(String browser) {
        ReadProperties.setBrowserType(browser);
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
