package linkedinautomationexercise.ui.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentDonePage extends PaymentPage{

    @FindBy(css = "h2[data-qa='order-placed'] b")
    public WebElement orderPlacedMessage;

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        orderPlacedMessage = wait.until(ExpectedConditions.visibilityOf(orderPlacedMessage));
    }
    public boolean isShowedTheOrderPlaceMessage() {
        return orderPlacedMessage.isDisplayed();
    }
}
