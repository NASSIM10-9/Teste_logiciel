package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test^='remove']")
    private List<WebElement> removeButtons;
    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;
    @FindBy(className = "cart_quantity")
    private List<WebElement> cartQuantities;
    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;

    public CartPage() {
        super();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void removeItem(int index) {
        if (index < removeButtons.size()) {
            click(removeButtons.get(index));
        }
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public void clickContinueShopping() {
        click(continueShoppingButton);
    }

    public String getFirstCartItemName() {
        if (!cartItemNames.isEmpty()) {
            return getText(cartItemNames.get(0));
        }
        return "";
    }

    public boolean isCartPageDisplayed() {
        return driver.getCurrentUrl().contains("/cart.html");
    }
}

