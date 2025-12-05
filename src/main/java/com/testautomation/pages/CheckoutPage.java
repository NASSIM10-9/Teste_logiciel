package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    
    // Checkout Step One - Information
    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    
    @FindBy(css = "[data-test='continue']")
    private WebElement continueButton;
    
    @FindBy(css = "[data-test='cancel']")
    private WebElement cancelButton;
    
    // Checkout Step Two - Overview
    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;
    
    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;
    
    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;
    
    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;
    
    // Checkout Complete
    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    
    @FindBy(className = "complete-text")
    private WebElement completeText;
    
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckoutPage() {
        super();
    }

    public void enterFirstName(String firstName) {
        sendKeys(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        sendKeys(postalCodeInput, postalCode);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public boolean isCheckoutComplete() {
        return isElementDisplayed(completeHeader);
    }

    public String getCompleteMessage() {
        return getText(completeHeader);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public boolean isCheckoutStepOneDisplayed() {
        return driver.getCurrentUrl().contains("/checkout-step-one.html");
    }

    public boolean isCheckoutStepTwoDisplayed() {
        return driver.getCurrentUrl().contains("/checkout-step-two.html");
    }

    public boolean isCheckoutCompleteDisplayed() {
        return driver.getCurrentUrl().contains("/checkout-complete.html");
    }
}

