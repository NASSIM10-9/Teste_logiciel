package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    
    @FindBy(id = "user-name")
    private WebElement usernameInput;
    
    @FindBy(id = "password")
    private WebElement passwordInput;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;
    
    @FindBy(className = "app_logo")
    private WebElement appLogo;

    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLoginSuccessful() {
        // Après login réussi, on est redirigé vers la page produits
        // Vérifier qu'on n'est plus sur la page de login
        try {
            return !isElementDisplayed(loginButton) || driver.getCurrentUrl().contains("/inventory.html");
        } catch (Exception e) {
            return false;
        }
    }
    
    public void navigateToLogin() {
        driver.get("https://www.saucedemo.com");
    }
}

