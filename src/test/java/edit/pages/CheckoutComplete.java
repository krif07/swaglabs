package edit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutComplete extends BasePage {
    @FindBy(className = "complete-header")
    private WebElement title;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backToProducts;

    public String getTitle() {
        return getText(title);
    }

    public String getCompleteText() {
        return getText(completeText);
    }

    public InventoryPage backToInventory() {
        click(backToProducts);
        return new InventoryPage();
    }
}
