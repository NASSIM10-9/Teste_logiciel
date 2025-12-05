package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    
    @FindBy(id = "email")
    private WebElement emailInput;
    
    @FindBy(id = "phone")
    private WebElement phoneInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    @FindBy(id = "avatar-upload")
    private WebElement avatarUploadInput;
    
    @FindBy(className = "avatar-preview")
    private WebElement avatarPreview;

    public ProfilePage() {
        super();
    }

    public void updateFirstName(String firstName) {
        sendKeys(firstNameInput, firstName);
    }

    public void updateLastName(String lastName) {
        sendKeys(lastNameInput, lastName);
    }

    public void updateEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void updatePhone(String phone) {
        sendKeys(phoneInput, phone);
    }

    public void clickSave() {
        click(saveButton);
    }

    public void updateProfile(String firstName, String lastName, String email, String phone) {
        updateFirstName(firstName);
        updateLastName(lastName);
        updateEmail(email);
        updatePhone(phone);
        clickSave();
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public void uploadAvatar(String filePath) {
        avatarUploadInput.sendKeys(filePath);
    }

    public boolean isAvatarDisplayed() {
        return isElementDisplayed(avatarPreview);
    }
}

