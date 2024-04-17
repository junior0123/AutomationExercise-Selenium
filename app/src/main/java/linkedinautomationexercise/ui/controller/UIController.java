package linkedinautomationexercise.ui.controller;

import java.util.ArrayList;
import java.util.List;

import linkedinautomationexercise.utils.LoggerManager;

public class UIController {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static UIController instance = null;

    private String category;
    private String subCategory;
    private String productName;
    private String quantity;
    private List<String> listProducts;

    private UIController() {
        listProducts = new ArrayList<String>();
    }

    public static UIController getInstance() {
        if (instance == null) {
            instance = new UIController();
        }
        return instance;
    }

    public void setCategory(String category) {
        this.category = category;
        log.info("Category set to " + category);
    }

    public String getCategory() {
        return category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        log.info("Sub-category set to " + subCategory);
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        log.info("Product name set to " + productName);
    }

    public void setQuantityProducts(String quantity) {
        this.quantity = quantity;
        log.info("Quantity of products set to " + quantity);
    }

    public String getQuantityProducts() {
        return quantity;
    }

    public void setListProducts(String productName) {
        listProducts.add(productName);
        log.info("Added product " + productName + " to the list of products");
    }

    public List<String> getListProducts() {
        return listProducts;
    }

    public void clearListProducts() {
        listProducts.clear();
        log.info("Cleared the list of products");
    }
}
