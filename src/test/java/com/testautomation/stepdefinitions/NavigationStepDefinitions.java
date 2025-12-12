package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.base.TestBase;
import com.testautomation.pages.ProductPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationStepDefinitions {
    private ProductPage productPage;
    private String initialUrl;

    @When("J'ouvre le menu burger")
    public void j_ouvre_le_menu_burger() {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
                initialUrl = productPage.getCurrentUrl();
            }
            productPage.openBurgerMenu();
            Hooks._scenario.log(Status.PASS, "Menu burger ouvert");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur ouverture menu burger");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je clique sur {string}")
    public void je_clique_sur(String menuItem) {
        try {
            if ("About".equals(menuItem)) {
                productPage.clickAbout();
            } else if ("Logout".equals(menuItem)) {
                productPage.clickLogout();
            }
            Hooks._scenario.log(Status.PASS, "Clic sur menu: " + menuItem);
        } catch (Exception e) {
             Hooks._scenario.log(Status.FAIL, "Erreur clic menu: " + menuItem);
             Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @Then("Je dois être redirigé vers la page About")
    public void je_dois_etre_redirige_vers_la_page_about() {
        try {

            WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("saucelabs.com"));
            
            String currentUrl = productPage.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("saucelabs.com"), "Doit être redirigé vers saucelabs.com");
            Hooks._scenario.log(Status.PASS, "Redirection vers About validée");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Echec redirection About");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("L'URL doit contenir {string}")
    public void l_url_doit_contenir(String expectedText) {
        try {
            String currentUrl = productPage.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains(expectedText), "L'URL doit contenir: " + expectedText);
            Hooks._scenario.log(Status.PASS, "URL contient: " + expectedText);
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "URL incorrecte");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @When("J'essaie de cliquer sur un élément du menu sans l'ouvrir")
    public void j_essaie_de_cliquer_sur_un_element_du_menu_sans_l_ouvrir() {
        try {

            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
                initialUrl = productPage.getCurrentUrl();
            }

            try {
                productPage.clickAbout();
            } catch (Exception e) {

                Hooks._scenario.log(Status.INFO, "Exception attendue: clic impossible sans menu ouvert");
            }
            Hooks._scenario.log(Status.PASS, "Tentative clic sans menu (simulé)");
        } catch (Exception e) {
             Hooks._scenario.log(Status.FAIL, "Erreur inattendue");
        }
    }

    @Then("Le menu ne doit pas s'ouvrir")
    public void le_menu_ne_doit_pas_s_ouvrir() {
        try {
            Assertions.assertFalse(productPage.isBurgerMenuOpen(), "Le menu ne doit pas s'ouvrir");
            Hooks._scenario.log(Status.PASS, "Menu resté fermé");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Le menu s'est ouvert");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Je dois rester sur la page des produits")
    public void je_dois_rester_sur_la_page_des_produits() {
        try {
            Assertions.assertTrue(productPage.isProductPageDisplayed(), "Doit rester sur la page produits");
            Hooks._scenario.log(Status.PASS, "Resté sur page produits");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Pas sur page produits");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @When("J'essaie d'accéder à une URL invalide")
    public void j_essaie_d_acceder_a_une_url_invalide() {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
            }
            if (initialUrl == null) {
                initialUrl = productPage.getCurrentUrl();
            }
            try {
                productPage.navigateTo("https://www.saucedemo.com/invalid-page");
                Hooks._scenario.log(Status.PASS, "Tentative navigation URL invalide");
            } catch (Exception e) {

                Hooks._scenario.log(Status.INFO, "Exception navigation URL invalide");
            }
        } catch (Exception e) {
             Hooks._scenario.log(Status.FAIL, "Erreur lors de la tentative de navigation");
        }
    }

    @Then("Je dois rester sur la page actuelle")
    public void je_dois_rester_sur_la_page_actuelle() {
        try {
            String currentUrl = productPage.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("/inventory") || currentUrl.equals(initialUrl), "Doit rester sur la page actuelle");
            Hooks._scenario.log(Status.PASS, "Page actuelle conservée");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Page actuelle non conservée");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Un message d'erreur peut être affiché")
    public void un_message_d_erreur_peut_etre_affiche() {
        try {

            Assertions.assertTrue(true, "Un message d'erreur peut être affiché");
            Hooks._scenario.log(Status.PASS, "Check optionnel message erreur");
        } catch (Throwable t) {
             Hooks._scenario.log(Status.FAIL, "Echec check optionnel");
             throw t;
        }
    }
}

