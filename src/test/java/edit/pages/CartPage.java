package edit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public String getProductTitle(int position) {
        String xpath = String.format("//div[@class='cart_item'][%d]//div[@class='inventory_item_name']", position);
        return getText(By.xpath(xpath));
    }

    public CheckoutStepOne checkoutProducts() {
        click(checkoutButton);
        return new CheckoutStepOne();
    }
}
