package org.example.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.*;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.lang.Float.sum;

public class D02_BuyProductsStepDef {
    LoginPage loginPage;
    HomePage homePage;
    private List<Map<String, String>> loginData;
    String username;
    String password;
    String errorMsg;
    String productName1;
    String productDesc1;
    float productPrice1;
    String productName2;
    String productDesc2;
    float productPrice2;
    MyCartPage myCartPage;
    CheckoutPage checkoutPage;
    OverviewPge overviewPge;
    CheckoutCompletePage checkoutCompletePage;

    public D02_BuyProductsStepDef() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        myCartPage = new MyCartPage();
        checkoutPage = new CheckoutPage();
        overviewPge = new OverviewPge();
        checkoutCompletePage = new CheckoutCompletePage();
    }

    @Given("Getting the valid login data from JSON {string}")
    public void getLoginDataFromJSON(String filePath) {
        loginData = JSONUtils.readJSON(filePath);
    }

    @When("Login using {string} JSON data")
    public void LogInUsingJSONData(String scenarioType) {
        for (Map<String, String> credentials : loginData) {
            //getting the data
            username = credentials.get("username");
            password = credentials.get("password");
            errorMsg = credentials.get("errorMsg");
            boolean isValid = scenarioType.equalsIgnoreCase("valid");
            //login steps
            loginPage.enterUserName(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            // Assert based on the expected result
            if (isValid) {
                Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "Error message should not be displayed");
            }else{
                Assert.assertTrue(loginPage.getErrorMessage().contains(errorMsg), "Error message mismatch");
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
            }
            Hooks.getDriver().navigate().refresh();
        }
    }

    @Then("The Product Page should {string}")
    public void checkingIfHomePageDisplayed(String displayedStatus) {
            boolean isValid = displayedStatus.equalsIgnoreCase("displayed");
            if (isValid) {
                Assert.assertTrue(homePage.isProductsDisplayed(), "Products should be displayed after login with Valid credentials ");
            } else {
                Assert.assertFalse(homePage.isProductsDisplayed(), "Products should not be displayed after login with inValid credentials ");
            }
    }

    @And("Add the most expensive two products to the cart")
    public void addMostExpensiveTwoProductsToTheCart() throws InterruptedException {
        homePage.openTheFilterMenu();
        homePage.productsFilter("Price high to low");
        Thread.sleep(Duration.ofSeconds(15));
        productName1 = homePage.getProductName(1);
        productDesc1 = homePage.getProductsDescription(1);
        productPrice1 = homePage.getProductsPrice(1);
        productName2 = homePage.getProductName(2);
        productDesc2 = homePage.getProductsDescription(2);
        productPrice2 = homePage.getProductsPrice(2);
        homePage.addMostExpensiveTwoProductsToCart();
        Assert.assertTrue(homePage.getNumberOfAddedItemsAtTheShoppingCart().contains("2"));
        System.out.println(productPrice2);
    }

    @And("Click on the cart button")
    public void clickOnTheCartButton() throws InterruptedException {
        homePage.clickOnTheCartButton();
        Thread.sleep(Duration.ofSeconds(15));
    }

    @Then("Verify that you’ve been navigated to “Cart” page and the previously selected products are displayed at the page")
    public void verifyNavigationToCartAndTwoProductsAreFound() {
        Assert.assertTrue(myCartPage.getMyCartPageTitle().contains("Your Cart"));
        Assert.assertTrue(Objects.requireNonNull(Hooks.getDriver().getCurrentUrl()).contains("https://www.saucedemo.com/cart.html"));
        Assert.assertTrue(homePage.getNumberOfAddedItemsAtTheShoppingCart().contains("2"));
        Assert.assertEquals( myCartPage.getNumberOfProduct(),2,"Product count does not match!");
        Assert.assertTrue( homePage.getProductName(1).contains(productName1));
        System.out.println(homePage.getProductName(1));
        System.out.println(productName2);
        Assert.assertTrue( homePage.getProductsDescription(1).contains(productDesc1));
        Assert.assertEquals( homePage.getProductsPrice(1),productPrice1," of the first product does not match!");
        Assert.assertTrue(homePage.getProductName(2).contains(productName2));
        Assert.assertTrue(homePage.getProductsDescription(2).contains(productDesc2));
        Assert.assertEquals(homePage.getProductsPrice(2),productPrice2,"price of the second product does not match!");
    }

    @And("Click on the Checkout button")
    public void clickOnTheCheckoutButton() {
        myCartPage.clickOnTheCheckoutButton();
    }

    @And("Verify that you’ve been navigated to the Checkout page")
    public void verifyNavigatedToCheckoutPage() {
        Assert.assertTrue(Objects.requireNonNull(Hooks.getDriver().getCurrentUrl()).contains("https://www.saucedemo.com/checkout-step-one.html"));
        Assert.assertTrue(checkoutPage.getCheckoutPageTitle().contains("Checkout: Your Information"));
    }

    @And("Fill all the displayed form")
    public void fillTheForm() {
        checkoutPage.enterFirstName("Automation");
        checkoutPage.enterLastName("Testing");
        checkoutPage.enterPostalCode("123456");
    }

    @And("Click on the Continue button")
    public void clickOnTheContinueButton() {
        checkoutPage.clickOnContinueBtn();
    }

    @Then("Verify that you’ve been navigated to the Overview page")
    public void verifyNavigatedToTheOverviewPage() {
        Assert.assertTrue(checkoutPage.getCheckoutPageTitle().contains("Checkout: Overview"));
        Assert.assertTrue(homePage.getNumberOfAddedItemsAtTheShoppingCart().contains("2"));
        Assert.assertEquals( myCartPage.getNumberOfProduct(), 2,"Product count does not match!");
        Assert.assertTrue( homePage.getProductName(1).contains(productName1));
        Assert.assertTrue( homePage.getProductsDescription(1).contains(productDesc1));
        Assert.assertEquals( homePage.getProductsPrice(1),productPrice1," of the first product does not match!");
        Assert.assertTrue(homePage.getProductName(2).contains(productName2));
        Assert.assertTrue(homePage.getProductsDescription(2).contains(productDesc2));
        Assert.assertEquals(homePage.getProductsPrice(2),productPrice2,"price of the second product does not match!");
    }
    @And("Verify the Items total amount before taxes")
    public void verifyTheItemsTotalAmountBeforeTaxes() {
        Assert.assertEquals(overviewPge.getTotalAmountWithoutTaxes(),sum(productPrice1,productPrice2),"total price without taxes does not match!");
    }

    @And("Verify that the URL matches with {string}")
    public void verifyTheURL(String url) {
        Assert.assertTrue(Objects.requireNonNull(Hooks.getDriver().getCurrentUrl()).contains(url));
    }

    @And("Click on the Finish button")
    public void clickOnTheFinishButton() {
        overviewPge.clickOnTheFinishButton();
    }

    @And("Verify both the Thank You and the order has been dispatched messages")
    public void verifyMessages() {
        Assert.assertTrue(checkoutCompletePage.getThankYouText().contains("Thank you for your order!"));
        Assert.assertTrue(checkoutCompletePage.getOrderHasBeenDispatchedText().contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!"));
    }

}