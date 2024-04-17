package linkedinautomationexercise.ui.pages;

import linkedinautomationexercise.framework.CredentialsManager;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.BasePageObject;
import linkedinautomationexercise.utils.LoggerManager;


public class LoginPage extends BasePageObject {

    @FindBy(css = "button[data-qa='login-button']")
    WebElement loginButton;

    @FindBy(name = "email")
    WebElement emailTextBox;

    @FindBy(name = "password")
    WebElement passwordTextBox;

    private static final LoggerManager log = LoggerManager.getInstance();

    public LoginPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        loginButton = wait.until(ExpectedConditions.visibilityOf(loginButton));
        emailTextBox = wait.until(ExpectedConditions.visibilityOf(emailTextBox));
        passwordTextBox = wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
        log.info("Login page loaded successfully");
    }

    public void setEmailTextBox(String email) {
        emailTextBox.click();
        emailTextBox.clear();
        emailTextBox.sendKeys(email);

    }

    public void setPasswordTextBox(String password) {
        passwordTextBox.click();
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);

    }

    public void clickLoginButton() {
        loginButton.click();
        log.info("Login button clicked");
    }

    public HomePage loginWithUser() {
        String userEmail = CredentialsManager.getInstance().getEmail("user");
        String userPassword = CredentialsManager.getInstance().getPassword("user");

        setEmailTextBox(userEmail);
        setPasswordTextBox(userPassword);
        clickLoginButton();

        log.info("Logged in with user: " + userEmail);
        return new HomePage();
    }
}
