package edit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {
    @FindBy(css = ".header_secondary_container span.title")
    private WebElement titleSpan;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    public String getTitle() {
        return getText(titleSpan);
    }

    public void waitUntilShoppingCartBadgeIs(int total) {
        wait.until(ExpectedConditions.textToBePresentInElement(shoppingCartBadge, String.valueOf(total)));
    }

    public String getShoppingCartBadge() {
        return getText(shoppingCartBadge);
    }
    public String addToCartByPositionAndGetTitle(int position) {
        String xpathToBtn = String.format(
                "//div[@class='inventory_item'][%d]//button", position);
        String xpathToTitle = String.format(
                "//div[@class='inventory_item'][%d]//div[@data-test='inventory-item-name']", position);
        click(By.xpath(xpathToBtn));
        return getText(By.xpath(xpathToTitle));
    }
    public CartPage navigateToCartPage() {
        click(shoppingCartLink);
        return new CartPage();
    }
}
