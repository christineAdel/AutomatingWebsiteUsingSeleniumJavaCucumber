package org.example.pages;

import org.example.stepDefs.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriverWait wait;

    public HomePage(){
        wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(20));
    }

    public boolean isProductsDisplayed() {
        boolean status;
        try {
            WebElement products = Hooks.getDriver().findElement(By.id("inventory_container"));
            status = products.isDisplayed();
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public void openTheFilterMenu() {
        WebElement productSort = Hooks.getDriver().findElement(By.className("product_sort_container"));
        productSort.click();
    }

    public void productsFilter(String sortBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
        Select dropdown = new Select(Hooks.getDriver().findElement(By.className("product_sort_container")));
        switch (sortBy) {
            case "Name from A to Z" -> dropdown.selectByValue("az");
            case "Name from Z to A" -> dropdown.selectByValue("za");
            case "Price low to high" -> dropdown.selectByValue("lohi");
            case "Price high to low" -> dropdown.selectByValue("hilo");
        }
        wait.until(ExpectedConditions.textToBePresentInElement(
                Hooks.getDriver().findElement(By.xpath("//span[@class='active_option' and @data-test='active-option']")),
                "Price (high to low)"
        ));
    }

    public void addMostExpensiveTwoProductsToCart() {
       for (int i = 1; i <= 2; i++) {
           WebElement addProduct = Hooks.getDriver().findElement(By.xpath("(//button[contains(@id,'add-to-cart')])[1]"));
           addProduct.click();
       }
    }

    public String getProductName(int index) {
        String element= "(//div[contains(@class, 'inventory_item_name')])[" + index + "]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        WebElement productName = Hooks.getDriver().findElement(By.xpath(element));
        return productName.getText();
    }

    public String getProductsDescription(int index) {
        WebElement productDesc = Hooks.getDriver().findElement(By.xpath("(//div[@class='inventory_item_desc'])[" + index + "]"));
        return productDesc.getText();
    }

    public float getProductsPrice(int index) {
        WebElement productPrice = Hooks.getDriver().findElement(By.xpath("(//div[@class='inventory_item_price'])[" + index + "]"));
        String priceText = productPrice.getText();
        String numericPart = priceText.replace("$", "");
        // Convert the numeric part to a float
        return Float.parseFloat(numericPart);
    }

    public String getNumberOfAddedItemsAtTheShoppingCart() {
        WebElement productCounter = Hooks.getDriver().findElement(By.className("shopping_cart_badge"));
        return productCounter.getText();
    }


    public void clickOnTheCartButton(){
        WebElement shoppingCartBtn = Hooks.getDriver().findElement(By.className("shopping_cart_link"));
        shoppingCartBtn.click();
    }
}
