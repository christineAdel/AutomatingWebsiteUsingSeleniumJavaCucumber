package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage {

    public String getThankYouText() {
        WebElement text = Hooks.getDriver().findElement(By.className("complete-header"));
        return text.getText();
    }

    public String getOrderHasBeenDispatchedText() {
        WebElement text = Hooks.getDriver().findElement(By.className("complete-text"));
        return text.getText();
    }
}
