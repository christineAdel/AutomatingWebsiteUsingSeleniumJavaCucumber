package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyCartPage {
    WebDriverWait wait;

    public MyCartPage(){
        wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(20));
    }

    public String getMyCartPageTitle() {
        WebElement title = Hooks.getDriver().findElement(By.xpath("//div[@id='header_container']//span[@class='title']"));
        return title.getText();
    }

    public int getNumberOfProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item_label")));
        List<WebElement> elements = Hooks.getDriver().findElements(By.className("cart_item_label"));
        int elementCount = elements.size();
        System.out.println("Number of product elements found: " + elementCount);
        return  elementCount;
    }

    public void clickOnTheCheckoutButton() {
        JavascriptExecutor js = (JavascriptExecutor) Hooks.getDriver();
        js.executeScript("window.scrollBy(0,250)", "");
        WebElement checkoutBtn = Hooks.getDriver().findElement(By.xpath("//button[contains(@class,'checkout_button')]"));
        checkoutBtn.click();
    }

}
