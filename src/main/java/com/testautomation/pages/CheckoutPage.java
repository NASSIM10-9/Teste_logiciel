package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class CheckoutPage extends BasePage {


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


    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;

    @FindBy(id = "finish")
    private WebElement finishButtonById;

    @FindBy(xpath = "//button[contains(text(), 'Finish')]")
    private WebElement finishButtonByText;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;


    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "error-message-container")
    private WebElement errorMessageContainer;

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
        // Attendre que la page de checkout step two se charge
        // Attendre que la page de checkout step two se charge
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void clickFinish() {

        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        try {
           wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("checkout-step-two.html"));
        } catch (Exception e) {
        }

        try {
            if (finishButton != null && finishButton.isDisplayed()) {
                click(finishButton);
                return;
            }
        } catch (Exception e) {
            System.out.println("Impossible de cliquer avec [data-test='finish']: " + e.getMessage());
        }

        try {
            if (finishButtonById != null && finishButtonById.isDisplayed()) {
                click(finishButtonById);
                return;
            }
        } catch (Exception e) {
            System.out.println("Impossible de cliquer avec id='finish': " + e.getMessage());
        }

        // Essayer avec le texte du bouton
        try {
            if (finishButtonByText != null && finishButtonByText.isDisplayed()) {
                click(finishButtonByText);
                return;
            }
        } catch (Exception e) {
            System.out.println("Impossible de cliquer avec xpath text: " + e.getMessage());
        }

        // Essayer en trouvant directement l'élément et utiliser JavaScript
        try {
            WebElement button = driver.findElement(By.cssSelector("[data-test='finish'], #finish, button[type='submit']"));
            if (button != null) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", button);
                return;
            }
        } catch (Exception e) {
            System.out.println("Impossible de cliquer le bouton Finish: " + e.getMessage());
            throw new RuntimeException("Le bouton Finish n'a pas pu être cliqué", e);
        }
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
        try {
            // Essayer d'abord avec data-test='error'
            if (isElementDisplayed(errorMessage)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("data-test='error' non trouvé, essai d'autres sélecteurs");
        }

        try {
            // Essayer avec la classe error-message-container
            if (isElementDisplayed(errorMessageContainer)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error-message-container non trouvé");
        }

        try {
            // Essayer avec une classe commune pour les messages d'erreur
            WebElement errorByClass = driver.findElement(By.cssSelector(".error-message-container, h3[data-test]"));
            return errorByClass != null && errorByClass.isDisplayed();
        } catch (Exception e) {
            System.out.println("Aucun message d'erreur trouvé avec les sélecteurs courants");
        }

        return false;
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
