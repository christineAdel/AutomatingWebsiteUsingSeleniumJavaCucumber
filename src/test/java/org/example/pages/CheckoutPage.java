package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriverWait wait;

    public CheckoutPage(){
        wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(20));
    }

    public String getCheckoutPageTitle() {
        WebElement title = Hooks.getDriver().findElement(By.xpath("//div[@id='header_container']//span[@class='title']"));
        return title.getText();
    }
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        WebElement firstNameField= Hooks.getDriver().findElement(By.id("first-name"));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        WebElement lastNameField= Hooks.getDriver().findElement(By.id("last-name"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterPostalCode(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        WebElement postalCodeField= Hooks.getDriver().findElement(By.id("postal-code"));
        postalCodeField.clear();
        postalCodeField.sendKeys(firstName);
    }

    public void clickOnContinueBtn() {
        WebElement continueBtn = Hooks.getDriver().findElement(By.id("continue"));
        continueBtn.click();
    }
}
