package stepDefinitions;

import edit.pages.InventoryPage;
import edit.pages.LoginPage;
import edit.utils.ReadProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDefinitions {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Given("the user access the login page")
    public void userAccessLoginPage() {
        loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
    }
    @When("he enters the username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        inventoryPage = loginPage.loginSwagLabs(username, password);
    }
    @Then("he should see the title {string}")
    public void userShouldSeeTitle(String title) {
        Assert.assertEquals("Products", inventoryPage.getTitle());
    }
}
