package edit.tests;

import stepDefinitions.hooks.Hooks;
import edit.pages.*;
import edit.utils.ExcelHelper;
import edit.utils.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPreOrderWithExcelData extends Hooks {
    @DataProvider(name = "excelData")
    public Object[][] getExcelData() throws IOException {
        return ExcelHelper.readExcel(
                "./src/test/java/data/preorderdata.xlsx",
                "Sheet0");
    }
    @Test(dataProvider = "excelData")
    public void generatePreOrderWithExcelData(String username, String password,
                                              String firstname, String lastname, String zipcode) {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        InventoryPage inventoryPage = loginPage.loginSwagLabs(username, password);
        String product1Title = inventoryPage.addToCartByPositionAndGetTitle(4);
        String product2Title = inventoryPage.addToCartByPositionAndGetTitle(5);
        Assert.assertEquals("2", inventoryPage.getShoppingCartBadge());

        CartPage cartPage = inventoryPage.navigateToCartPage();
        Assert.assertEquals(product1Title, cartPage.getProductTitle(1));
        Assert.assertEquals(product2Title, cartPage.getProductTitle(2));

        CheckoutStepOne checkoutStepOne = cartPage.checkoutProducts();
        CheckoutStepTwo checkoutStepTwo = checkoutStepOne.startCheckoutAndContinue(firstname, lastname, zipcode);

        Assert.assertEquals("Total: $62.62", checkoutStepTwo.getSummaryTotal());

        CheckoutComplete checkoutComplete = checkoutStepTwo.finishCheckout();

        Assert.assertEquals("Thank you for your order!", checkoutComplete.getTitle());
        Assert.assertEquals(
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                checkoutComplete.getCompleteText());

    }
}
