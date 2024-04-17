package linkedinautomationexercise.ui.pages;

import linkedinautomationexercise.framework.selenium.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.utils.LoggerManager;

public class CheckoutPage extends CartPage {
    @FindBy(css = "a.btn.check_out")
    WebElement placeOrderButton;

    @FindBy(css = "td:nth-child(4) > .cart_total_price")
    WebElement totalAmount;
    private static final LoggerManager log = LoggerManager.getInstance();
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        log.info("Waiting for place order button to be clickable");
        placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        log.info("Place order button is clickable");

        log.info("Waiting for total amount to be visible");
        totalAmount = wait.until(ExpectedConditions.visibilityOfElementLocated((By) totalAmount));
        log.info("Total amount is visible");
    }

    public PaymentPage clickOnPlaceOrder() {
        log.info("Clicking on place order button");
        placeOrderButton.click();
        return new PaymentPage();
    }
    public String getTotalAmount() {
        log.info("Getting total amount");
        String totalAmountText = totalAmount.getText();
        log.info("Total amount is: " + totalAmountText);
        return totalAmountText;
    }

    public String getAmountOfProduct(String productName) {

        String priceProductLocator = String.format("//td[@class='cart_description']//a[text()='%s']/parent::h4/parent::td/following-sibling::td[@class='cart_total']/p", productName);
        String cartInfoLocator = "cart_info";
        WebElement cartInfo = driver.findElement(By.id(cartInfoLocator));
        UIMethods.moveToWebElement(cartInfo);
        WebElement priceProduct = driver.findElement(By.xpath(priceProductLocator));
        String priceProductText = priceProduct.getText();
        log.info("Price of product " + productName + " is " + priceProductText);
        return priceProductText;
    }
}
