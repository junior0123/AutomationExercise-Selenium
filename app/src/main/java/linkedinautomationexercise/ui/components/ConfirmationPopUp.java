package linkedinautomationexercise.ui.components;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.pages.CartPage;
import linkedinautomationexercise.ui.pages.ProductPage;
import linkedinautomationexercise.utils.LoggerManager;

public class ConfirmationPopUp extends ProductPage {
    private static final LoggerManager log = LoggerManager.getInstance();

    @FindBy(xpath = "//a[@href='/view_cart']/ancestor::p[@class=\"text-center\"]")
    WebElement viewCartButton;
    @FindBy(xpath = "//*[@id=\"cartModal\"]/div/div/div[3]/button")
    WebElement continueShoppingButton;

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        log.info("Confirmation popup loaded successfully.");
    }

    public CartPage clickOnTHheViewCartButton() {
        viewCartButton.click();
        log.info("Clicked on the View Cart button.");
        return new CartPage();
    }

    public void clickOnTheContinueShoppingButton() {
        continueShoppingButton.click();
        log.info("Clicked on the Continue Shopping button.");
    }
}
