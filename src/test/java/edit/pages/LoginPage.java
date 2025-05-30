package edit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessageText;

    public InventoryPage loginSwagLabs(String username, String password) {
        sendKeys(usernameInput, username);
        sendKeys(passwordInput, password);
        click(loginButton);
        return new InventoryPage();
    }

    public String getErrorMessage() {
        return getText(errorMessageText);
    }
}
