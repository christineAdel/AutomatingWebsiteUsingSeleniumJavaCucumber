package org.example.stepDefs;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.*;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class D01_LoginWithInvalidDataStepDef {
    LoginPage loginPage;
    HomePage homePage;
    private List<Map<String, String>> loginData;
    String username;
    String password;
    String errorMsg;

    public D01_LoginWithInvalidDataStepDef() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Given("Getting the invalid login data from JSON {string}")
    public void getLoginDataFromJSON(String filePath) {

        loginData = JSONUtils.readJSON(filePath);
    }

    @When("Login with wrong credentials using {string} JSON data")
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

    @Then("Make sure that The Product Page should {string}")
    public void checkingIfHomePageDisplayed(String displayedStatus) {
            boolean isValid = displayedStatus.equalsIgnoreCase("displayed");
            if (isValid) {
                Assert.assertTrue(homePage.isProductsDisplayed(), "Products should be displayed after login with Valid credentials ");
            } else {
                Assert.assertFalse(homePage.isProductsDisplayed(), "Products should not be displayed after login with inValid credentials ");
            }
    }
}