package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.base.TestBase;
import com.testautomation.pages.CheckoutPage;
import com.testautomation.pages.LoginPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Soit;
import org.junit.Assert;

public class CommonStepDefinitions {
    
    @Soit("^Je suis connecté et sur la page des produits$")
    public void je_suis_connecte_et_sur_la_page_des_produits() {
        TestContext.loginAndNavigateToProducts();
        Assert.assertTrue("Doit être sur la page produits", 
            TestContext.getProductPage().isProductPageDisplayed());
    }

    @Et("^Un message d'erreur doit être affiché$")
    public void un_message_d_erreur_doit_etre_affiche() {
        try {
            // Vérifier le contexte : login ou checkout
            String currentUrl = TestBase.getDriver().getCurrentUrl();
            
            if (currentUrl.contains("checkout")) {
                // Contexte checkout
                CheckoutPage checkoutPage = new CheckoutPage();
                Assert.assertTrue("Un message d'erreur doit être affiché sur la page checkout",
                    checkoutPage.isErrorMessageDisplayed());
                Hooks.scenario.log(Status.PASS, "Message d'erreur affiché sur la page checkout");
            } else {
                // Contexte login (par défaut)
                LoginPage loginPage = new LoginPage();
                Assert.assertTrue("Un message d'erreur doit être affiché sur la page login",
                    loginPage.isErrorMessageDisplayed());
                Hooks.scenario.log(Status.PASS, "Message d'erreur affiché sur la page login");
            }
        } catch (AssertionError e) {
            Hooks.scenario.log(Status.FAIL, "Message d'erreur non affiché: " + e.getMessage());
            throw e;
        }
    }

    @Et("^Le message doit contenir \"([^\"]*)\"$")
    public void le_message_doit_contenir(String expectedMessage) {
        try {
            // Vérifier le contexte : login ou checkout
            String currentUrl = TestBase.getDriver().getCurrentUrl();
            String actualMessage;
            
            if (currentUrl.contains("checkout")) {
                // Contexte checkout
                CheckoutPage checkoutPage = new CheckoutPage();
                if (checkoutPage.isErrorMessageDisplayed()) {
                    actualMessage = checkoutPage.getErrorMessage();
                    Assert.assertTrue("Le message doit contenir: " + expectedMessage,
                        actualMessage.contains(expectedMessage));
                    Hooks.scenario.log(Status.PASS, "Le message checkout contient: " + expectedMessage);
                } else {
                    Assert.fail("Aucun message d'erreur affiché sur la page checkout");
                }
            } else {
                // Contexte login (par défaut)
                LoginPage loginPage = new LoginPage();
                actualMessage = loginPage.getErrorMessage();
                Assert.assertTrue("Le message doit contenir: " + expectedMessage,
                    actualMessage.contains(expectedMessage));
                Hooks.scenario.log(Status.PASS, "Le message login contient: " + expectedMessage);
            }
        } catch (AssertionError e) {
            Hooks.scenario.log(Status.FAIL, "Le message ne contient pas '" + expectedMessage + "': " + e.getMessage());
            throw e;
        }
    }
}

