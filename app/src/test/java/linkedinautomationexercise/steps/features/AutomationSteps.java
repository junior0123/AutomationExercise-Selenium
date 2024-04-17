package linkedinautomationexercise.steps.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import linkedinautomationexercise.ui.PageTransporter;
import linkedinautomationexercise.ui.components.ConfirmationPopUp;
import linkedinautomationexercise.ui.components.TopBarMenu;
import linkedinautomationexercise.ui.controller.UIController;
import linkedinautomationexercise.ui.methods.CommonMethods;
import linkedinautomationexercise.ui.pages.*;

import java.util.List;

public class AutomationSteps {
    private static final UIController controller = UIController.getInstance();
    private LoginPage loginPage;
    private HomePage homePage;
    private TopBarMenu topBarMenu;
    private ProductPage productPage;
    private ConfirmationPopUp confirmationPopUp;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private PaymentDonePage paymentDonePage;

    private final PageTransporter pageTransporter;
    public AutomationSteps() {
        this.pageTransporter = PageTransporter.getInstance();
        topBarMenu = new TopBarMenu();
    }

    @When("user goes to Products Page using the top menu")
    public void goToTheProductsPage() {
        productPage = topBarMenu.clickProductsLink();
    }

    @Given("a logged in user on the home page")
    public void login() {
        CommonMethods.login();
    }

    @Then("user should see all the products")
    public void verifyQuantityOfProducts() {
        int actualResult = productPage.getElements().size();
        int expectedResult = 35;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Given("user is on the homepage of the website")
    public void switchHomePage() {
        homePage = pageTransporter.navigateToHomePage();
    }

    @And("a user not logged in")
    public void logout() {
        CommonMethods.logout();

    }

    @And("user performs the search with the {string}")
    public void performSearchByName(String productName) {
        productPage.performASearch(productName);
        controller.setProductName(productName);
    }
    @Then("user should see the results for the product")
    public void verifyIfTheProductsIsShowed() {
        List<WebElement> products = productPage.getElements();
        boolean expectedResult = false;
        for (WebElement product : products) {
            String productName = product.getText();
            if (productName.contains(controller.getProductName())) {
                expectedResult = true;
            }
        }
        Assert.assertTrue(expectedResult);
    }

    @And("user adds the product with the name {string}")
    public void addProductToCart(String productName) {
        controller.setProductName(productName);
        controller.setListProducts(productName);
        confirmationPopUp = productPage.clickAddToCartButton(productName);
    }

    @Then("user should see the product in the cart")
    public void verifyIfTheProductIsInTheCart() {
        boolean actualResult = cartPage.isTheProductAdded(controller.getProductName());
        Assert.assertNotNull(cartPage);
        Assert.assertTrue(actualResult);
    }

    @And("user continues viewing the products")
    public void continueViewing() {
        confirmationPopUp.clickOnTheContinueShoppingButton();
        cartPage = topBarMenu.clickCartLink();
    }

    @And("user accesses the cart from the confirmation popup")
    public void switchCart() {
        cartPage = confirmationPopUp.clickOnTHheViewCartButton();
    }

    @And("user views the detail of the product with the {string} name")
    public void switchDetailsPage(String productName) {
        controller.setProductName(productName);
        productDetailsPage = productPage.goToProductDetailsPage(productName);
    }

    @Then("user should see the details of the product")
    public void verifyTheDetailsOfTheProduct() {
        Assert.assertNotNull(productDetailsPage);
        Assert.assertEquals(productDetailsPage.getTheProductName(), controller.getProductName());
    }
    @And("user expands the {string} of the site and select a {string}")
    public void expandCategoryAndSubCategory(String category, String subCategory) {
        controller.setCategory(category);
        controller.setSubCategory(subCategory);
        productPage.clickCategoryButton(category, subCategory);
    }
    @Then("user should see the products of the category")
    public void verifyTheCategory() {
        String expectedResult = controller.getCategory() + " - " + controller.getSubCategory() + " products";
        Assert.assertEquals(productPage.getTitleCategory().toLowerCase(), expectedResult.toLowerCase());
        Assert.assertNotNull(productPage.getElements());
    }

    @And("user changes the data of the quantity of products {string}")
    public void changeQuantityProducts(String quantity) {
        controller.setQuantityProducts(quantity);
        productDetailsPage.setQuantityField(quantity);
        confirmationPopUp = productDetailsPage.clickOnButtonAddCart();
        cartPage = confirmationPopUp.clickOnTHheViewCartButton();
    }

    @Then("user should see the quantity of products in the cart section")
    public void verifyTheQuantity() {
        int quantity = Integer.parseInt(cartPage.getQuantityOfTheProduct(controller.getProductName()));
        Assert.assertTrue(quantity > 0);
        Assert.assertTrue(cartPage.isTheProductAdded(controller.getProductName()));
        Assert.assertEquals(cartPage.getQuantityOfTheProduct(controller.getProductName()), controller.getQuantityProducts());
    }
    @And("user removes the product added")
    public void removeProductByName() {
        cartPage.deleteItemButton(controller.getProductName());
    }

    @Then("user should see the cart empty")
    public void verifyCartEmpty() {
        String expectedResult = "Cart is empty! Click here to buy products.";
        Assert.assertEquals(expectedResult, cartPage.getMessageText());
    }

    @When("user goes to cart page using the top menu")
    public void switchCartPage() {
        cartPage = topBarMenu.clickCartLink();
    }

    @And("user continues to the checkout page")
    public void switchCheckoutPage() {
        checkoutPage = cartPage.clickOnProceedToCheckoutButton();
    }

    @Then("user should see an informative message")
    public void verifyInformativeMessageIsShowed() {
        Assert.assertTrue(cartPage.isDisplayInformativePopUp());
    }

    @And("user goes to the product page from empty cart")
    public void switchProductPageFromCart() {
        productPage = cartPage.clickOnTheHereLink();
    }

    @Then("user should see the product page")
    public void verifyQuantityProductsLoaded() {
        Assert.assertNotNull(productPage);
        int actualResult = productPage.getElements().size();
        int expectedResult = 35;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Then("user should see the product added before logging out")
    public void verifyProductKept() {
        Assert.assertTrue(cartPage.isTheProductAdded(controller.getProductName()));
    }

    @And("user continues to add products to the cart")
    public void continueOnTheProductPage() {
        confirmationPopUp.clickOnTheContinueShoppingButton();
    }

    @And("user accesses the checkout section")
    public void switchCheckout() {
        checkoutPage = cartPage.clickOnProceedToCheckoutButton();
    }

    @Then("user should see the correct amount of the order")
    public void verifyTotalAmount() {
        int expectedTotalAmount = 0;
        List<String> products = controller.getListProducts();

        for (String product : products) {
            String priceString = checkoutPage.getAmountOfProduct(product);
            String numericString = priceString.replaceAll("[^\\d]", ""); // Remove non-numeric characters
            expectedTotalAmount += Integer.parseInt(numericString);
        }
        String totalAmountString = checkoutPage.getTotalAmount();
        String totalNumericString = totalAmountString.replaceAll("[^\\d]", ""); // Remove non-numeric characters
        Assert.assertEquals(expectedTotalAmount, Integer.parseInt(totalNumericString));
    }

    @And("user continues to the payment page")
    public void switchPaymentPAge() {
        paymentPage = checkoutPage.clickOnPlaceOrder();

    }

    @And("user enters {string} in the Name on Card field")
    public void enterNameCard(String nameCard) {
        paymentPage.setInputNameCard(nameCard);
    }

    @And("user enters {string} in the Card Number field")
    public void enterCardNumber(String cardNumber) {
        paymentPage.setInputCardNumber(cardNumber);
    }

    @And("user enters {string} in the CVC field")
    public void enterCVC(String cvc) {
        paymentPage.setInputCVC(cvc);
    }

    @And("user enters {string} in the Expiration Month field")
    public void enterExpirationMonth(String expirationMonth) {
        paymentPage.setInputExpiryMonth(expirationMonth);
    }

    @And("user enters {string} in the Expiration Year field")
    public void enterExpirationYear(String expirationYear) {
        paymentPage.setInputExpiryYear(expirationYear);
    }

    @And("user confirms the order and pays")
    public void confirmPay() {
        paymentDonePage = paymentPage.clickOnPayAndConfirmOrderButton();
    }

    @Then("the page should show a error message")
    public void verifyErrorMessage() {
        Assert.assertFalse(paymentDonePage.isShowedTheOrderPlaceMessage());
    }
}
