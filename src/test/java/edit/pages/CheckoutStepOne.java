package edit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOne extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(name = "postalCode")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutStepTwo startCheckoutAndContinue(
            String firstname, String lastname, String postalCode) {
        sendKeys(firstNameInput, firstname);
        sendKeys(lastNameInput, lastname);
        sendKeys(postalCodeInput, postalCode);
        click(continueButton);
        return new CheckoutStepTwo();
    }
}

