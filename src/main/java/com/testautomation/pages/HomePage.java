package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    

    
    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    public HomePage() {
        super();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isHomePageDisplayed() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/") ||
               driver.getCurrentUrl().equals("https://www.saucedemo.com");
    }
}

