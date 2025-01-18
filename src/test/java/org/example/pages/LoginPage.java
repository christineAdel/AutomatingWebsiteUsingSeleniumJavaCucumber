package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriverWait wait;

    public LoginPage(){
        wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(20));
    }
    public void enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement usernameField= Hooks.getDriver().findElement(By.id("user-name"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement passwordField= Hooks.getDriver().findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton= Hooks.getDriver().findElement(By.id("login-button"));
        loginButton.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        WebElement errorMessage= Hooks.getDriver().findElement(By.xpath("//h3[@data-test='error']"));
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        boolean status;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        WebElement errorMessage= Hooks.getDriver().findElement(By.xpath("//h3[@data-test='error']"));
            status = errorMessage.isDisplayed();
        }catch(Exception e) {
            status = false;
        }
        return status;
    }
}
