package linkedinautomationexercise.ui.pages;


import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.BasePageObject;
import linkedinautomationexercise.ui.components.ConfirmationPopUp;

import java.util.logging.Logger;

public class ProductDetailsPage extends BasePageObject {
    @FindBy(xpath = "//*[@id='quantity']")
    WebElement quantityField;

    @FindBy(xpath = "//button[@type='button']")
    WebElement buttonAddCart;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2")
    WebElement nameProduct;

    private static final Logger log = Logger.getLogger(ProductDetailsPage.class.getName());

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        quantityField = wait.until(ExpectedConditions.elementToBeClickable(quantityField));
        buttonAddCart = wait.until(ExpectedConditions.elementToBeClickable(buttonAddCart));
        //nameProduct = wait.until(ExpectedConditions.visibilityOf(nameProduct));
        log.info("ProductDetailsPage loaded successfully");
    }

    public void setQuantityField(String quantity) {
        quantityField.click();
        quantityField.clear();
        quantityField.sendKeys(quantity);
        log.info("Product quantity set to " + quantity);
    }

    public ConfirmationPopUp clickOnButtonAddCart() {
        buttonAddCart.click();
        log.info("Add to cart button clicked");
        return new ConfirmationPopUp();
    }

    public String getTheProductName() {
        return nameProduct.getText();
    }
}
