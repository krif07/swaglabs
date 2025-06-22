import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "",
        features = {"src/test/resources/features/login.feature"},
        glue = {"stepDefinitions"},
        plugin = {})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
