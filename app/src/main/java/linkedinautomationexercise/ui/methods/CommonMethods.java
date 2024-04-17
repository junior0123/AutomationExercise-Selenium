package linkedinautomationexercise.ui.methods;

import linkedinautomationexercise.ui.PageTransporter;
import linkedinautomationexercise.ui.components.TopBarMenu;
import linkedinautomationexercise.ui.pages.LoginPage;
import linkedinautomationexercise.utils.LoggerManager;

public class CommonMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static TopBarMenu topBarMenu;
    private static LoginPage loginPage;
    private static final PageTransporter pageTransporter = PageTransporter.getInstance();

    public static void logout() {
        log.info("Logging out...");
        topBarMenu = new TopBarMenu();
        topBarMenu.clickLogoutLink();
    }

    public static void login() {
        log.info("Logging in...");
        loginPage = pageTransporter.navigateToAdminLoginPage();
        loginPage.loginWithUser();
    }
}
