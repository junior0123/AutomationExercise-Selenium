package linkedinautomationexercise.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.BasePageObject;
import linkedinautomationexercise.ui.components.ConfirmationPopUp;
import linkedinautomationexercise.ui.components.TopBarMenu;
import linkedinautomationexercise.utils.LoggerManager;

import java.io.IOException;
import java.util.List;

public class CartPage extends BasePageObject {
    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    WebElement proceedToCheckoutButton;

    @FindBy(id = "cart_info")
    WebElement cartInfo;

    @FindBy(xpath = "//a[@href=\"/products\"]/u")
    WebElement linkHere;
    private static final LoggerManager log = LoggerManager.getInstance();
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
    }
    public void deleteItemButton(String productName) {
        String productNameLocator = String.format("//h4/a[contains(text(), '%s')]/parent::h4/parent::td/following-sibling::td/a[@class='cart_quantity_delete']", productName);
        WebElement deleteItemButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productNameLocator)));
        deleteItemButton.click();
        log.info("Item '" + productName + "' deleted from cart");
    }
    public CheckoutPage clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
        log.info("Clicked on Proceed to Checkout button");
        return new CheckoutPage();
    }
    public boolean isTheProductAdded(String productName) {
        String productNameLocator = String.format("//h4/a[contains(text(), '%s')]/parent::h4/parent::td[@class='cart_description']", productName);
        WebElement productNameElement = driver.findElement(By.xpath(productNameLocator));
        return wait.until(ExpectedConditions.visibilityOf(productNameElement)) != null;
    }
    public String getQuantityOfTheProduct(String productName) {
        String productNameLocator = String.format("//h4/a[contains(text(), '%s')]/parent::h4/parent::td/following-sibling::td[@class='cart_quantity']/button", productName);
        WebElement productNameElement = driver.findElement(By.xpath(productNameLocator));
        return productNameElement.getText();
    }
    public int getTotalQuantityOfProductsAdded() {
        if (cartInfo.isDisplayed()) {
            WebElement table = cartInfo.findElement(By.id("cart_info_table"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            return rows.size() - 2;
        }
        return 0;
    }
    public String getMessageText() {
        WebElement emptyCart = driver.findElement(By.id("empty_cart"));
        return emptyCart.findElement(By.tagName("p")).getAttribute("textContent");
    }
    public boolean isDisplayInformativePopUp() {
        boolean isDisplayed = false;
        TopBarMenu topBarMenu = new TopBarMenu();
        System.out.println(topBarMenu.getStatusLogoutLogin());
        if (!topBarMenu.getStatusLogoutLogin().contains("Logout")) {
            isDisplayed = true;
            WebElement modal = driver.findElement(By.className("modal-content"));
            WebElement continueOnCartButton = driver.findElement(By.xpath("//button[@class='btn btn-success close-checkout-modal btn-block' and @data-dismiss='modal']\n"));
            continueOnCartButton.click();
        }
        return isDisplayed;
    }
    public ProductPage clickOnTheHereLink() {
        linkHere.click();
        return new ProductPage();
    }
}
