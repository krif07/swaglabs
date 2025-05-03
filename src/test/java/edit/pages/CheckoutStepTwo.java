package edit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwo extends BasePage {
    @FindBy(className = "summary_total_label")
    private WebElement summaryTotal;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public String getSummaryTotal() {
        return getText(summaryTotal);
    }

    public CheckoutComplete finishCheckout() {
        click(finishButton);
        return new CheckoutComplete();
    }
}
