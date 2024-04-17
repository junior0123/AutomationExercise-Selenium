package linkedinautomationexercise.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.utils.LoggerManager;

public class PaymentPage extends CheckoutPage {

    @FindBy(css = "a.btn.check_out")
    WebElement placeOrderButton;
    @FindBy(xpath = "//input[@name='name_on_card']")
    WebElement inputNameCard;

    @FindBy(xpath = "//input[@name='card_number']")
    WebElement inputCardNumber;

    @FindBy(xpath = "//input[@name='cvc']")
    WebElement inputFormCard;

    @FindBy(xpath = "//input[@placeholder='MM']")
    WebElement inputExpiryMonth;

    @FindBy(xpath = "//input[@name='expiry_year']")
    WebElement inputExpiryYear;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement buttonPay;

    private static final LoggerManager log = LoggerManager.getInstance();

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        inputNameCard = wait.until(ExpectedConditions.elementToBeClickable(inputNameCard));
        inputCardNumber = wait.until(ExpectedConditions.elementToBeClickable(inputCardNumber));
        inputFormCard = wait.until(ExpectedConditions.elementToBeClickable(inputFormCard));
        inputExpiryMonth = wait.until(ExpectedConditions.elementToBeClickable(inputExpiryMonth));
        inputExpiryYear = wait.until(ExpectedConditions.elementToBeClickable(inputExpiryYear));
        buttonPay = wait.until(ExpectedConditions.elementToBeClickable(buttonPay));
        log.info("Payment page loaded successfully");
    }

    public void setInputNameCard(String nameCard) {
        inputNameCard.click();
        inputNameCard.clear();
        inputNameCard.sendKeys(nameCard);
        log.info("Name on card set to " + nameCard);
    }

    public void setInputCardNumber(String cardNumber) {
        inputCardNumber.click();
        inputCardNumber.clear();
        inputCardNumber.sendKeys(cardNumber);
        log.info("Card number set to " + cardNumber);
    }

    public void setInputCVC(String cvc) {
        inputFormCard.click();
        inputFormCard.clear();
        inputFormCard.sendKeys(cvc);
        log.info("CVC set to " + cvc);
    }

    public void setInputExpiryMonth(String expiryMonth) {
        inputExpiryMonth.click();
        inputExpiryMonth.clear();
        inputExpiryMonth.sendKeys(expiryMonth);
        log.info("Expiry month set to " + expiryMonth);
    }

    public void setInputExpiryYear(String expiryYear) {
        inputExpiryYear.click();
        inputExpiryYear.clear();
        inputExpiryYear.sendKeys(expiryYear);
        log.info("Expiry year set to " + expiryYear);
    }

    public PaymentDonePage clickOnPayAndConfirmOrderButton() {
        buttonPay.click();
        log.info("Pay button clicked");
        return new PaymentDonePage();
    }

    public boolean isDisplayedTheOrderSuccesfullMessage() {
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(@id, 'message')]//div[starts-with(@class, 'alert-success')]"));
        return !successMessage.isDisplayed() || !successMessage.getCssValue("display").equals("none");
    }
}
