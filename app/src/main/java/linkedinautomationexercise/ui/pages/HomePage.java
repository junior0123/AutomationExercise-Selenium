package linkedinautomationexercise.ui.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import linkedinautomationexercise.ui.BasePageObject;
import linkedinautomationexercise.utils.LoggerManager;

public class HomePage extends BasePageObject {
    private static final LoggerManager log = LoggerManager.getInstance();
    @FindBy(id = "slider")
    WebElement slider;

    @FindBy(css = "div[class^='features'] h2[class$='text-center']")
    WebElement featuresItemsTitle;

    //@FindBy(css = "ul[class='nav navbar-nav']")
    //WebElement navBar;
    public HomePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        //featuresItemsTitle = wait.until(ExpectedConditions.visibilityOf(featuresItemsTitle));
    }

}
