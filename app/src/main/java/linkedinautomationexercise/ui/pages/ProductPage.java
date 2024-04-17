package linkedinautomationexercise.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.BasePageObject;
import linkedinautomationexercise.ui.components.ConfirmationPopUp;
import linkedinautomationexercise.utils.LoggerManager;

import java.util.List;

public class ProductPage extends BasePageObject {
    private static final LoggerManager log = LoggerManager.getInstance();
    @FindBy(id = "search_product")
    WebElement inputSearchProduct;
    @FindBy(css = "button[id^='submit']")
    WebElement searchButton;

    @FindBy(css = "a[href^='/product_details/']")
    WebElement viewProductButton;

    @FindBy(xpath = "//div[@class='features_items']")
    WebElement allProducts;

    @FindBy(css = "h2[class$='text-center']")
    WebElement titleCategory;

    public ProductPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        allProducts = wait.until(ExpectedConditions.visibilityOf(allProducts));
        inputSearchProduct = wait.until(ExpectedConditions.elementToBeClickable(inputSearchProduct));
    }

    public void setSearchBox(String productName) {
        inputSearchProduct.click();
        inputSearchProduct.clear();
        inputSearchProduct.sendKeys(productName);
    }

    public void performASearch(String productName) {
        setSearchBox(productName);
        searchButton.click();
    }

    public ProductDetailsPage goToProductDetailsPage(String productName) {
        String productNameLocator = String.format("//*[contains(text(),'%s')]/ancestor::div[@class='product-image-wrapper']//a[contains(@href,'/product_details')]", productName);
        WebElement viewProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productNameLocator)));
        viewProductButton.click();
        log.info("Clicked on 'View Product' button for the product: " + productName);
        return new ProductDetailsPage();
    }

    public ConfirmationPopUp clickAddToCartButton(String productName) {
        String productNameLocator = String.format("//*[contains(text(),'%s')]/ancestor::div[@class='productinfo text-center']//a[contains(@class,'add-to-cart')]", productName);
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productNameLocator)));
        addToCartButton.click();
        log.info("Clicked on 'Add to Cart' button for the product: " + productName);
        return new ConfirmationPopUp();
    }

    public void clickExpandCategory(String category) {
        String productNameLocator = String.format("//a[@href='#%s']", category);
        WebElement expandCategoryButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productNameLocator)));
        expandCategoryButton.click();
        log.info("Expanded category: " + category);
    }

    public void clickCategoryButton(String category, String subCategory) {
        clickExpandCategory(category);
        String productNameLocator = String.format("//div[@id='%s']//a[contains(text(),'%s')]", category, subCategory);
        WebElement selectSubCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productNameLocator)));
        selectSubCategory.click();
        log.info("Clicked on category: " + category + " and subcategory: " + subCategory);
    }

    public List<WebElement> getElements() {
        return driver.findElements(By.className("col-sm-4"));
    }

    public String getTitleCategory() {
        return titleCategory.getText();
    }

}
