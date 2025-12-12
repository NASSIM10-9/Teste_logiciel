package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {
    
    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;
    
    @FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;
    
    @FindBy(css = "[data-test='product_sort_container']")
    private WebElement sortContainer;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;
    
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;
    
    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;
    
    @FindBy(id = "react-burger-cross-btn")
    private WebElement burgerMenuCloseButton;
    
    @FindBy(id = "about_sidebar_link")
    private WebElement aboutLink;
    
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;
    
    @FindBy(className = "bm-menu")
    private WebElement burgerMenu;

    public ProductPage() {
        super();
    }

    public int getProductCount() {
        return productItems.size();
    }

    public void addProductToCart(int index) {
        if (index < addToCartButtons.size()) {
            click(addToCartButtons.get(index));
        }
    }

    public void addFirstProductToCart() {
        if (!addToCartButtons.isEmpty()) {
            click(addToCartButtons.get(0));
        }
    }

    public void clickCartIcon() {
        click(cartIcon);
    }

    public int getCartItemCount() {
        try {
            if (isElementDisplayed(cartBadge)) {
                return Integer.parseInt(getText(cartBadge));
            }
        } catch (Exception e) {
            // Badge not displayed means cart is empty
        }
        return 0;
    }

    public void selectSortOption(String option) {
        // Options: "az", "za", "lohi", "hilo"
        click(sortDropdown);
        // Utiliser Select pour le dropdown
        org.openqa.selenium.support.ui.Select select = 
            new org.openqa.selenium.support.ui.Select(sortDropdown);
        select.selectByValue(option);
    }

    public String getFirstProductName() {
        if (!productNames.isEmpty()) {
            return getText(productNames.get(0));
        }
        return "";
    }

    public List<String> getAllProductNames() {
        return productNames.stream()
            .map(this::getText)
            .collect(java.util.stream.Collectors.toList());
    }

    public boolean isProductPageDisplayed() {
        return driver.getCurrentUrl().contains("/inventory.html") || 
               driver.getCurrentUrl().contains("/inventory");
    }

    public void openBurgerMenu() {
        click(burgerMenuButton);
        // Attendre que le menu s'ouvre
        // Attendre que le menu s'ouvre
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(burgerMenu));
    }

    public void closeBurgerMenu() {
        if (isElementDisplayed(burgerMenuCloseButton)) {
            click(burgerMenuCloseButton);
        }
    }

    public void clickAbout() {
        click(aboutLink);
    }

    public void clickLogout() {
        click(logoutLink);
    }

    public boolean isBurgerMenuOpen() {
        return isElementDisplayed(burgerMenu);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public void navigateTo(String url) {
        driver.get(url);
    }
}

