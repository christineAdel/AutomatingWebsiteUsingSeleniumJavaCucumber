package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class OverviewPge {

    public float getTotalAmountWithoutTaxes() {
        WebElement totalPriceWithoutTaxesTxt = Hooks.getDriver().findElement(By.className("summary_subtotal_label"));
        String priceText = totalPriceWithoutTaxesTxt.getText();
        String numericPart = priceText.replace("Item total: $", "");
        // Convert the numeric part to a float
        return Float.parseFloat(numericPart);
    }

    public void clickOnTheFinishButton(){
        JavascriptExecutor js = (JavascriptExecutor) Hooks.getDriver();
        js.executeScript("window.scrollBy(0,250)", "");
        WebElement finishBtn = Hooks.getDriver().findElement(By.id("finish"));
        finishBtn.click();
    }
}
