@test
Feature: Login with valid credentials and buy products Feature

  Scenario: Login with correct credentials and buy two products
    Given Getting the valid login data from JSON "src\main\resources\data\valid_login_data.json"
    When Login using "valid" JSON data
    Then The Product Page should "displayed"
    And Add the most expensive two products to the cart
    And Click on the cart button
    Then Verify that you’ve been navigated to “Cart” page and the previously selected products are displayed at the page
    And Click on the Checkout button
    And Verify that you’ve been navigated to the Checkout page
    And Fill all the displayed form
    And Click on the Continue button
    Then Verify that you’ve been navigated to the Overview page
    And Verify the Items total amount before taxes
    And Verify that the URL matches with "https://www.saucedemo.com/checkout-step-two.html"
    And Click on the Finish button
    And Verify both the Thank You and the order has been dispatched messages

