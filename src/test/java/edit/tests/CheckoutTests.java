package edit.tests;

import edit.hooks.Hooks;
import edit.pages.*;
import edit.utils.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends Hooks {
    @Test()
    void testAddToCartProducts() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        InventoryPage inventoryPage = loginPage.loginSwagLabs("standard_user", "secret_sauce");
        inventoryPage.addToCartByPositionAndGetTitle(2);
        inventoryPage.addToCartByPositionAndGetTitle(3);
        inventoryPage.addToCartByPositionAndGetTitle(4);
        inventoryPage.waitUntilShoppingCartBadgeIs(3);
        Assert.assertEquals("3", inventoryPage.getShoppingCartBadge());
    }

    @Test()
    void testCheckoutCartProducts() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        InventoryPage inventoryPage = loginPage.loginSwagLabs("standard_user", "secret_sauce");
        String product1Title = inventoryPage.addToCartByPositionAndGetTitle(2);
        String product2Title = inventoryPage.addToCartByPositionAndGetTitle(3);
        inventoryPage.waitUntilShoppingCartBadgeIs(2);
        Assert.assertEquals("2", inventoryPage.getShoppingCartBadge());

        CartPage cartPage = inventoryPage.navigateToCartPage();
        Assert.assertEquals(product1Title, cartPage.getProductTitle(1));
        Assert.assertEquals(product2Title, cartPage.getProductTitle(2));
    }

    @Test(description = "Test the generation of a purchase order")
    void testGeneratePurchaseOrder() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(ReadProperties.getURL());
        InventoryPage inventoryPage = loginPage.loginSwagLabs("standard_user", "secret_sauce");
        String product1Title = inventoryPage.addToCartByPositionAndGetTitle(1);
        String product2Title = inventoryPage.addToCartByPositionAndGetTitle(4);
        inventoryPage.waitUntilShoppingCartBadgeIs(2);
        Assert.assertEquals("2", inventoryPage.getShoppingCartBadge());

        CartPage cartPage = inventoryPage.navigateToCartPage();
        Assert.assertEquals(product1Title, cartPage.getProductTitle(1));
        Assert.assertEquals(product2Title, cartPage.getProductTitle(2));

        CheckoutStepOne checkoutStepOne = cartPage.checkoutProducts();
        CheckoutStepTwo checkoutStepTwo = checkoutStepOne.startCheckoutAndContinue(
                "Cristian Fernando", "Dávila López", "661001");

        Assert.assertEquals("Total: $86.38", checkoutStepTwo.getSummaryTotal());

        CheckoutComplete checkoutComplete = checkoutStepTwo.finishCheckout();

        Assert.assertEquals("Thank you for your order!", checkoutComplete.getTitle());
        Assert.assertEquals(
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                checkoutComplete.getCompleteText());

    }
}
