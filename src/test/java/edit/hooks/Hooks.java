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
    @BeforeMethod
    public void setup() {
        DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.cleanupDriver();
    }
}
