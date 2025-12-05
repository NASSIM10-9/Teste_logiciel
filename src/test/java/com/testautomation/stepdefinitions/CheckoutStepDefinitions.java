package com.testautomation.stepdefinitions;

import com.testautomation.pages.CheckoutPage;
import com.testautomation.pages.LoginPage;
import com.testautomation.pages.ProductPage;
import com.testautomation.pages.CartPage;
import io.cucumber.java.fr.*;
import org.junit.Assert;

public class CheckoutStepDefinitions {
    private CheckoutPage checkoutPage;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Soit("^Je suis connecté avec des articles dans le panier$")
    public void je_suis_connecte_avec_des_articles_dans_le_panier() {
        loginPage = new LoginPage();
        loginPage.navigateToLogin();
        loginPage.login("standard_user", "secret_sauce");
        productPage = new ProductPage();
        productPage.addFirstProductToCart();
        productPage.clickCartIcon();
        cartPage = new CartPage();
    }

    @Et("^Je suis sur la page de checkout$")
    public void je_suis_sur_la_page_de_checkout() {
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage();
        Assert.assertTrue("Doit être sur la page checkout step one", 
            checkoutPage.isCheckoutStepOneDisplayed());
    }

    @Quand("^Je saisis le prénom \"([^\"]*)\"$")
    public void je_saisis_le_prenom(String firstName) {
        checkoutPage.enterFirstName(firstName);
    }

    @Et("^Je saisis le nom \"([^\"]*)\"$")
    public void je_saisis_le_nom(String lastName) {
        checkoutPage.enterLastName(lastName);
    }

    @Et("^Je saisis le code postal \"([^\"]*)\"$")
    public void je_saisis_le_code_postal(String postalCode) {
        checkoutPage.enterPostalCode(postalCode);
    }

    @Et("^Je continue vers l'étape suivante$")
    public void je_continue_vers_l_etape_suivante() {
        checkoutPage.clickContinue();
    }

    @Et("^Je finalise la commande$")
    public void je_finalise_la_commande() {
        checkoutPage.clickFinish();
    }

    @Alors("^La commande doit être complétée avec succès$")
    public void la_commande_doit_etre_completee_avec_succes() {
        Assert.assertTrue("La commande doit être complétée", 
            checkoutPage.isCheckoutComplete());
    }

    @Et("^Un message de confirmation doit être affiché$")
    public void un_message_de_confirmation_doit_etre_affiche() {
        Assert.assertTrue("Un message de confirmation doit être affiché", 
            checkoutPage.isCheckoutComplete());
        String message = checkoutPage.getCompleteMessage();
        Assert.assertTrue("Le message doit contenir 'Thank you'", 
            message.contains("Thank you"));
    }

    @Alors("^Le checkout doit échouer$")
    public void le_checkout_doit_echouer() {
        Assert.assertTrue("Le checkout doit échouer",
            checkoutPage.isErrorMessageDisplayed() ||
            checkoutPage.isCheckoutStepOneDisplayed());
    }


}

