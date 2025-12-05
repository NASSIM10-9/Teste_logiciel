package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.pages.LoginPage;
import io.cucumber.java.fr.*;
import org.junit.Assert;

public class LoginStepDefinitions {
    private LoginPage loginPage;

    public LoginStepDefinitions() {
        this.loginPage = new LoginPage();
    }

    @Soit("^Je suis sur la page de connexion$")
    public void je_suis_sur_la_page_de_connexion() {
        try {
            loginPage.navigateToLogin();
            Hooks.scenario.log(Status.PASS, "Navigation vers la page de connexion réussie");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Erreur lors de la navigation: " + e.getMessage());
            throw e;
        }
    }

    @Quand("^Je saisis le nom d'utilisateur \"([^\"]*)\"$")
    public void je_saisis_le_nom_d_utilisateur(String username) {
        try {
            loginPage.enterUsername(username);
            Hooks.scenario.log(Status.PASS, "Nom d'utilisateur saisi: " + username);
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Erreur lors de la saisie du nom d'utilisateur: " + e.getMessage());
            throw e;
        }
    }

    @Et("^Je saisis le mot de passe \"([^\"]*)\"$")
    public void je_saisis_le_mot_de_passe(String password) {
        try {
            loginPage.enterPassword(password);
            Hooks.scenario.log(Status.PASS, "Mot de passe saisi avec succès");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Erreur lors de la saisie du mot de passe: " + e.getMessage());
            throw e;
        }
    }

    @Et("^Je clique sur le bouton de connexion$")
    public void je_clique_sur_le_bouton_de_connexion() {
        try {
            loginPage.clickLoginButton();
            Hooks.scenario.log(Status.PASS, "Bouton de connexion cliqué");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Erreur lors du clic sur le bouton de connexion: " + e.getMessage());
            throw e;
        }
    }

    @Alors("^La connexion doit être réussie$")
    public void la_connexion_doit_etre_reussie() {
        try {
            Assert.assertTrue("La connexion doit être réussie", loginPage.isLoginSuccessful());
            Hooks.scenario.log(Status.PASS, "Connexion réussie");
        } catch (AssertionError e) {
            Hooks.scenario.log(Status.FAIL, "La connexion a échoué: " + e.getMessage());
            throw e;
        }
    }

    @Et("^Un message de succès doit être affiché$")
    public void un_message_de_succes_doit_etre_affiche() {
        try {
            Assert.assertTrue("Un message de succès doit être affiché", loginPage.isLoginSuccessful());
            Hooks.scenario.log(Status.PASS, "Message de succès affiché");
        } catch (AssertionError e) {
            Hooks.scenario.log(Status.FAIL, "Message de succès non affiché: " + e.getMessage());
            throw e;
        }
    }

    @Alors("^La connexion doit échouer$")
    public void la_connexion_doit_echouer() {
        try {
            Assert.assertTrue("La connexion doit échouer", loginPage.isErrorMessageDisplayed());
            Hooks.scenario.log(Status.PASS, "Connexion échouée comme attendu");
        } catch (AssertionError e) {
            Hooks.scenario.log(Status.FAIL, "La connexion n'a pas échoué comme attendu: " + e.getMessage());
            throw e;
        }
    }


}

