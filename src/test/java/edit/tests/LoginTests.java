package edit.tests;

import edit.hooks.Hooks;
import edit.pages.InventoryPage;
import edit.pages.LoginPage;
import edit.utils.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends Hooks {

    @Test(priority = 1,
            description = "Successful Login")
    void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        InventoryPage inventoryPage = loginPage.loginSwagLabs("standard_user", "secret_sauce");
        Assert.assertEquals("Products", inventoryPage.getTitle());
    }
    @Test(priority = 2,
            description = "Login Fallido")
    void testUnSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        InventoryPage inventoryPage = loginPage.loginSwagLabs("standard_user", "secret_sauc");
        Assert.assertEquals("Products", inventoryPage.getTitle());
    }
}
