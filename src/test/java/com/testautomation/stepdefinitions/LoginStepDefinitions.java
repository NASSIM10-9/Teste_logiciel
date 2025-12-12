package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefinitions {
    private LoginPage loginPage = new LoginPage();

    @Given("Je suis sur la page de connexion")
    public void je_suis_sur_la_page_de_connexion() {
        try {
            loginPage.navigateToLogin();
            Hooks._scenario.log(Status.PASS, "Navigation vers la page de connexion réussie");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de la navigation");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @When("Je saisis le nom d'utilisateur {string}")
    public void je_saisis_le_nom_d_utilisateur(String username) {
        try {
            loginPage.enterUsername(username);
            Hooks._scenario.log(Status.PASS, "Nom d'utilisateur saisi: " + username);
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de la saisie du nom d'utilisateur");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je saisis le mot de passe {string}")
    public void je_saisis_le_mot_de_passe(String password) {
        try {
            loginPage.enterPassword(password);
            Hooks._scenario.log(Status.PASS, "Mot de passe saisi avec succès");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de la saisie du mot de passe");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je clique sur le bouton de connexion")
    public void je_clique_sur_le_bouton_de_connexion() {
        try {
            loginPage.clickLoginButton();
            Hooks._scenario.log(Status.PASS, "Bouton de connexion cliqué");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors du clic sur le bouton de connexion");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @Then("La connexion doit être réussie")
    public void la_connexion_doit_etre_reussie() {
        try {
            Assertions.assertTrue(loginPage.isLoginSuccessful(), "La connexion doit être réussie");
            Hooks._scenario.log(Status.PASS, "Connexion réussie");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "La connexion a échoué");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Un message de succès doit être affiché")
    public void un_message_de_succes_doit_etre_affiche() {
        try {
            Assertions.assertTrue(loginPage.isLoginSuccessful(), "Un message de succès doit être affiché");
            Hooks._scenario.log(Status.PASS, "Message de succès affiché");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Message de succès non affiché");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @Then("La connexion doit échouer")
    public void la_connexion_doit_echouer() {
        try {
            Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "La connexion doit échouer");
            Hooks._scenario.log(Status.PASS, "Connexion échouée comme attendu");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "La connexion n'a pas échoué comme attendu");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }
}

