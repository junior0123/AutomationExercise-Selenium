package linkedinautomationexercise.steps.hooks;

import io.cucumber.java.After;
import linkedinautomationexercise.ui.PageTransporter;
import linkedinautomationexercise.ui.controller.UIController;
import linkedinautomationexercise.ui.methods.CommonMethods;
import linkedinautomationexercise.ui.pages.CartPage;


public class ProductsFeatureHooks {
    private static final PageTransporter pageTransporter = PageTransporter.getInstance();
    private static final UIController controller = UIController.getInstance();

    @After("@SearchProduct")
    public void afterProduct() {
        pageTransporter.navigateToHomePage();
    }
    @After("@AddProduct or @AccessCart or @QuantityOfProducts or @RetainProducts or @TryingCheckout")
    public void deleteProducts() {
        CartPage cartPage = new CartPage();
        cartPage.deleteItemButton(controller.getProductName());
        CommonMethods.logout();
        controller.clearListProducts();
    }
}