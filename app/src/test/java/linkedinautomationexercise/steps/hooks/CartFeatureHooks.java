package linkedinautomationexercise.steps.hooks;


import io.cucumber.java.After;
import linkedinautomationexercise.ui.PageTransporter;
import linkedinautomationexercise.ui.controller.UIController;
import linkedinautomationexercise.ui.methods.CommonMethods;
import linkedinautomationexercise.ui.pages.CartPage;


import java.util.List;

public class CartFeatureHooks {
    private static final PageTransporter pageTransporter = PageTransporter.getInstance();
    private static final UIController controller = UIController.getInstance();

    @After("@CorrectAmount")
    public void removeAllElements() {
        CartPage cartPage = pageTransporter.navigateToCartPage();
        List<String> products = controller.getListProducts();
        for (String product : products) {
            cartPage.deleteItemButton(product);
        }
        CommonMethods.logout();
        controller.clearListProducts();
    }
    @After("@EmptyCart or @RemoveProduct or @RedirectUser or @Payment")
    public void logout() {
        CommonMethods.logout();
    }
}
