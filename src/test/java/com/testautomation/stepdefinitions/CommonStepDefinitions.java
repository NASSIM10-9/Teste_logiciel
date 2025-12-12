package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.base.TestBase;
import com.testautomation.pages.CheckoutPage;
import com.testautomation.pages.LoginPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;

public class CommonStepDefinitions {
    private CheckoutPage checkoutPage = new CheckoutPage();
    private LoginPage loginPage = new LoginPage();
    
    @Given("Je suis connecté et sur la page des produits")
    public void je_suis_connecte_et_sur_la_page_des_produits() {
        try {
            TestContext.loginAndNavigateToProducts();
            Assertions.assertTrue(TestContext.getProductPage().isProductPageDisplayed(), "Doit être sur la page produits");
            Hooks._scenario.log(Status.PASS, "Navigation page produits réussie");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Echec navigation page produits");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Un message d'erreur doit être affiché")
    public void un_message_d_erreur_doit_etre_affiche() {
        try {

            String currentUrl = TestBase.getDriver().getCurrentUrl();
            
            if (currentUrl.contains("checkout")) {


                Assertions.assertTrue(checkoutPage.isErrorMessageDisplayed(), "Un message d'erreur doit être affiché sur la page checkout");
                Hooks._scenario.log(Status.PASS, "Message d'erreur affiché sur la page checkout");
            } else {


                Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Un message d'erreur doit être affiché sur la page login");
                Hooks._scenario.log(Status.PASS, "Message d'erreur affiché sur la page login");
            }
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Message d'erreur non affiché");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Le message doit contenir {string}")
    public void le_message_doit_contenir(String expectedMessage) {
        try {

            String currentUrl = TestBase.getDriver().getCurrentUrl();
            String actualMessage;
            
            if (currentUrl.contains("checkout")) {


                if (checkoutPage.isErrorMessageDisplayed()) {
                    actualMessage = checkoutPage.getErrorMessage();
                    Assertions.assertTrue(actualMessage.contains(expectedMessage), "Le message doit contenir: " + expectedMessage);
                    Hooks._scenario.log(Status.PASS, "Le message checkout contient: " + expectedMessage);
                } else {
                    Assertions.fail("Aucun message d'erreur affiché sur la page checkout");
                }
            } else {


                actualMessage = loginPage.getErrorMessage();
                Assertions.assertTrue(actualMessage.contains(expectedMessage), "Le message doit contenir: " + expectedMessage);
                Hooks._scenario.log(Status.PASS, "Le message login contient: " + expectedMessage);
            }
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Le message ne contient pas '" + expectedMessage + "'");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }
}

