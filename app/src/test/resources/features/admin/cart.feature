@UI
Feature: Cart
  @RemoveProduct
  Scenario Outline: The user should be able to remove a product from the cart
    Given a logged in user on the home page
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product name>"
    And user continues viewing the products
    And user goes to cart page using the top menu
    And user removes the product added
    Then user should see the cart empty
    Examples:
      |product name  |
      |Blue Top      |


  @TryingCheckout
  Scenario Outline: the website should display a message when the user is not logged in and tries to continue with the checkout.
    Given a user not logged in
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product name>"
    And user accesses the cart from the confirmation popup
    And user continues to the checkout page
    Then user should see an informative message
    Examples:
      |product name  |
      |Blue Top      |

  @CorrectAmount
  Scenario Outline: The total amount of the order should be correct.
    Given a logged in user on the home page
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product name>"
    And user continues to add products to the cart
    And user adds the product with the name "Sleeveless Dress"
    And user accesses the cart from the confirmation popup
    And user accesses the checkout section
    Then user should see the correct amount of the order
    Examples:
      |product name  |
      |Blue Top      |

  @Payment
    Scenario Outline: Verify that no invalid data can be entered in the payment card fields
    Given a logged in user on the home page
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product>"
    And user accesses the cart from the confirmation popup
    And user continues to the checkout page
    And user continues to the payment page
    And user enters "<name>" in the Name on Card field
    And user enters "<card_number>" in the Card Number field
    And user enters "<cvc>" in the CVC field
    And user enters "<expiration_month>" in the Expiration Month field
    And user enters "<expiration_year>" in the Expiration Year field
    And user confirms the order and pays
    Then the page should show a error message

    Examples:
      | product    | name   | card_number | cvc | expiration_month | expiration_year |
      | Blue Top   | Visa   | Number      | CVC | July            | 0               |


  @RetainProducts
  Scenario Outline: The page should retain the added product after logging out.
    Given a logged in user on the home page
    When user goes to Products Page using the top menu
    And user adds the product with the name "<product name>"
    And user accesses the cart from the confirmation popup
    And a user not logged in
    And a logged in user on the home page
    And user goes to cart page using the top menu
    Then user should see the product added before logging out
    Examples:
      |product name  |
      |Blue Top      |


