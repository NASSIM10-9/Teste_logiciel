package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.pages.CheckoutPage;
import com.testautomation.pages.LoginPage;
import com.testautomation.pages.ProductPage;
import com.testautomation.pages.CartPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class CheckoutStepDefinitions {
    private CheckoutPage checkoutPage = new CheckoutPage();
    private LoginPage loginPage = new LoginPage();
    private ProductPage productPage = new ProductPage();
    private CartPage cartPage = new CartPage();

    @Given("Je suis connecté avec des articles dans le panier")
    public void je_suis_connecte_avec_des_articles_dans_le_panier() {
        try {

            loginPage.navigateToLogin();
            loginPage.login("standard_user", "secret_sauce");

            productPage.addFirstProductToCart();
            productPage.clickCartIcon();

            Hooks._scenario.log(Status.PASS, "Conditions initiales établies : connecté avec article au panier");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de la mise en place des conditions initiales");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je suis sur la page de checkout")
    public void je_suis_sur_la_page_de_checkout() {
        try {
            cartPage.clickCheckout();

            Assertions.assertTrue(checkoutPage.isCheckoutStepOneDisplayed(), "Doit être sur la page checkout step one");
            Hooks._scenario.log(Status.PASS, "Page checkout step one affichée");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Echec navigation vers checkout step one");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @When("Je saisis le prénom {string}")
    public void je_saisis_le_prenom(String firstName) {
        try {
            checkoutPage.enterFirstName(firstName);
            Hooks._scenario.log(Status.PASS, "Prénom saisi: " + firstName);
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur saisie prénom");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je saisis le nom {string}")
    public void je_saisis_le_nom(String lastName) {
        try {
            checkoutPage.enterLastName(lastName);
            Hooks._scenario.log(Status.PASS, "Nom saisi: " + lastName);
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur saisie nom");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je saisis le code postal {string}")
    public void je_saisis_le_code_postal(String postalCode) {
        try {
            checkoutPage.enterPostalCode(postalCode);
            Hooks._scenario.log(Status.PASS, "Code postal saisi: " + postalCode);
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur saisie code postal");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je continue vers l'étape suivante")
    public void je_continue_vers_l_etape_suivante() {
        try {
            checkoutPage.clickContinue();
            Hooks._scenario.log(Status.PASS, "Cliqué sur Continue");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur clic Continue");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je finalise la commande")
    public void je_finalise_la_commande() {
        try {
            checkoutPage.clickFinish();
            Hooks._scenario.log(Status.PASS, "Cliqué sur Finish");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur clic Finish");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @Then("La commande doit être complétée avec succès")
    public void la_commande_doit_etre_completee_avec_succes() {
        try {
            Assertions.assertTrue(checkoutPage.isCheckoutComplete(), "La commande doit être complétée");
            Hooks._scenario.log(Status.PASS, "Commande complétée avec succès");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "La commande n'est pas complétée");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Un message de confirmation doit être affiché")
    public void un_message_de_confirmation_doit_etre_affiche() {
        try {
            Assertions.assertTrue(checkoutPage.isCheckoutComplete(), "Un message de confirmation doit être affiché");
            String message = checkoutPage.getCompleteMessage();
            Assertions.assertTrue(message.contains("Thank you"), "Le message doit contenir 'Thank you'");
            Hooks._scenario.log(Status.PASS, "Message de confirmation validé");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Echec validation message de confirmation");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @Then("Le checkout doit échouer")
    public void le_checkout_doit_echouer() {
        try {
            Assertions.assertTrue(checkoutPage.isErrorMessageDisplayed() || checkoutPage.isCheckoutStepOneDisplayed(), "Le checkout doit échouer");
            Hooks._scenario.log(Status.PASS, "Checkout échoué comme attendu");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Le checkout n'a pas échoué comme attendu");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }
}



