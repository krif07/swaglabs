package edit.tests;

import edit.hooks.Hooks;
import edit.pages.InventoryPage;
import edit.pages.LoginPage;
import edit.utils.ExcelHelper;
import edit.utils.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends Hooks {

    @DataProvider(name = "loginProvider")
    public Object[][] getUserPassword() {
        Object[][] data = new Object[6][2];
        //row 1
        data[0][0] = "standard_user";
        data[0][1] = "secret_sauce";
        //row 2
        data[1][0] = "locked_out_user";
        data[1][1] = "secret_sauce";
        //row 3
        data[2][0] = "problem_user";
        data[2][1] = "secret_sauce";
        //row 4
        data[3][0] = "performance_glitch_user";
        data[3][1] = "secret_sauce";
        //row 5
        data[4][0] = "error_user";
        data[4][1] = "secret_sauce";
        //row 6
        data[5][0] = "visual_user";
        data[5][1] = "secret_sauce";

        return data;
    }
    @DataProvider(name = "loginExcel")
    public Object[][] getUserPasswordExcel() throws IOException {
        String excelPath = "./src/test/java/data/users.xlsx";
        String sheetName = "Sheet0";
        return ExcelHelper.readExcel(excelPath, sheetName);
    }

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
    @Test(dataProvider = "loginExcel")
    void testAllInvalidUsers(String email, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        //for(Object[] row: data) {
        loginPage.loginSwagLabs(email, password);
        Assert.assertEquals(
                "Epic sadface: Username and password do not match any user in this service", loginPage.getErrorMessage());
        //}
    }
}
