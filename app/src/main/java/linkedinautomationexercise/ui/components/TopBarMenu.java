package linkedinautomationexercise.ui.components;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.BasePageObject;
import linkedinautomationexercise.ui.pages.CartPage;
import linkedinautomationexercise.ui.pages.ProductPage;
import linkedinautomationexercise.utils.LoggerManager;

public class TopBarMenu extends BasePageObject {
    private static final LoggerManager log = LoggerManager.getInstance();

    @FindBy(partialLinkText = "Products")
    WebElement productsLink;
    @FindBy(partialLinkText = "Cart")
    WebElement cartLink;
    @FindBy(css = "a[href = '/login']")
    WebElement loginLink;
    @FindBy(css = "a[href = '/logout']")
    WebElement logoutLink;

    @FindBy(xpath = "//header[@id='header']/div/div/div/div[2]/div/ul/li[4]/a")
    WebElement labelLogoutLogin;

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        log.info("Waiting for Products and Cart links to be clickable.");
        productsLink = wait.until(ExpectedConditions.elementToBeClickable(productsLink));
        cartLink = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
    }

    public ProductPage clickProductsLink() {
        log.info("Clicking on Products link.");
        productsLink.click();
        return new ProductPage();
    }

    public CartPage clickCartLink() {
        log.info("Clicking on Cart link.");
        cartLink.click();
        return new CartPage();
    }

    public void clickLogoutLink() {
        log.info("Checking if user is logged in before clicking on Logout link.");
        if (!labelLogoutLogin.getText().contains("Signup")) {
            log.info("Clicking on Logout link.");
            logoutLink.click();
        }
    }

    public String getStatusLogoutLogin() {
        log.debug("Getting status of Login/Logout link.");
        return labelLogoutLogin.getText();
    }
}
