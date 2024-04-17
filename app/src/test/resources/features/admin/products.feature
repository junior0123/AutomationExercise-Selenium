@UI
Feature: Products
  @SearchProduct
  Scenario Outline: The user should can search a product by name
    Given a user not logged in
    When user goes to Products Page using the top menu
    And user performs the search with the "<product name>"
    Then user should see the results for the product
    Examples:
        |product name  |
        |Blue Top      |
        |Stylish Dress |
        |Little Girls Mr. Panda Shirt |
  @AddProduct
  Scenario Outline: The user should can add a product to the cart
    Given a logged in user on the home page
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product name>"
    And user continues viewing the products
    Then user should see the product in the cart
    Examples:
      |product name  |
      |Blue Top      |
      |Stylish Dress |
      |Little Girls Mr. Panda Shirt |

  @AccessCart
  Scenario Outline: The user should can access to cart from the confirmation pop up
    Given a logged in user on the home page
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product name>"
    And user accesses the cart from the confirmation popup
    Then user should see the product in the cart
    Examples:
      |product name  |
      |Blue Top      |
      |Stylish Dress |
      |Little Girls Mr. Panda Shirt |

  @AccessProductDetailPage
  Scenario Outline: The user should can access detail product page
    Given a user not logged in
    When user goes to Products Page using the top menu
    And user views the detail of the product with the "<product name>" name
    Then user should see the details of the product
    Examples:
      |product name  |
      |Blue Top      |
      |Stylish Dress |
      |Little Girls Mr. Panda Shirt |

  @ShowCategoryElements
  Scenario Outline: The page should show the products by category
    Given a user not logged in
    When user goes to Products Page using the top menu
    And user expands the "<category>" of the site and select a "<sub-category>"
    Then user should see the products of the category
    Examples:
      |category  |sub-category|
      |Women     |   Tops    |
      |Men       |   Jeans    |
      |Kids      |   Dress    |

  @QuantityOfProducts
  Scenario Outline: The user should be able to change the quantity of products.
    Given a user not logged in
    When user goes to Products Page using the top menu
    And user views the detail of the product with the "<product name>" name
    And user changes the data of the quantity of products "<quantity>"
    Then user should see the quantity of products in the cart section
    Examples:
    |product name|quantity  |
    |Blue Top    |5         |
    |Blue Top    |-5        |
    |Blue Top    |0         |
