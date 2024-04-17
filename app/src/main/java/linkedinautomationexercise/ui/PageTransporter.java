package linkedinautomationexercise.ui;

import linkedinautomationexercise.framework.CredentialsManager;
import linkedinautomationexercise.framework.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import linkedinautomationexercise.ui.pages.CartPage;
import linkedinautomationexercise.ui.pages.HomePage;
import linkedinautomationexercise.ui.pages.LoginPage;
import linkedinautomationexercise.utils.LoggerManager;

public class PageTransporter {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final CredentialsManager credentialsManager = CredentialsManager.getInstance();

    private WebDriver driver;
    private String loginUserURL = "https://automationexercise.com/login";
    private String adminURL;
    private String homePageURL = "https://automationexercise.com";
    private static PageTransporter instance;

    protected PageTransporter() {
        initialize();
    }

    public static PageTransporter getInstance() {
        if (instance == null) {
            instance = new PageTransporter();
        }
        return instance;
    }

    private void initialize() {
        log.info("Initializing Page Transporter");
        loginUserURL = credentialsManager.getUserLoginURL();
        adminURL = credentialsManager.getUserURL();
        driver = DriverManager.getInstance().getWebDriver();
        homePageURL = credentialsManager.getBaseURL();
    }

    private void goToURL(String url) {
        driver.navigate().to(url);
    }

    public boolean isOnLoginAdminPage() {
        return driver.getCurrentUrl().contains(loginUserURL);
    }

    public LoginPage navigateToAdminLoginPage() {
        if (!isOnLoginAdminPage()) {
            goToURL(loginUserURL);
        }
        return new LoginPage();
    }

    public HomePage navigateToHomePage() {
        goToURL(homePageURL);
        return new HomePage();
    }
    public CartPage navigateToCartPage() {
        goToURL("https://automationexercise.com/view_cart");
        return new CartPage();
    }


}
