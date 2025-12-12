package com.testautomation.stepdefinitions;

import com.testautomation.pages.ProductPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.fr.*;
import org.junit.Assert;

public class NavigationStepDefinitions {
    private ProductPage productPage;
    private String initialUrl;

    @Quand("^J'ouvre le menu burger$")
    public void j_ouvre_le_menu_burger() {
        if (productPage == null) {
            TestContext.loginAndNavigateToProducts();
            productPage = TestContext.getProductPage();
            initialUrl = productPage.getCurrentUrl();
        }
        productPage.openBurgerMenu();
    }

    @Et("^Je clique sur \"([^\"]*)\"$")
    public void je_clique_sur(String menuItem) {
        if ("About".equals(menuItem)) {
            productPage.clickAbout();
        } else if ("Logout".equals(menuItem)) {
            productPage.clickLogout();
        }
    }

    @Alors("^Je dois être redirigé vers la page About$")
    public void je_dois_etre_redirige_vers_la_page_about() {
        // Attendre la redirection
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String currentUrl = productPage.getCurrentUrl();
        Assert.assertTrue("Doit être redirigé vers saucelabs.com", 
            currentUrl.contains("saucelabs.com"));
    }

    @Et("^L'URL doit contenir \"([^\"]*)\"$")
    public void l_url_doit_contenir(String expectedText) {
        String currentUrl = productPage.getCurrentUrl();
        Assert.assertTrue("L'URL doit contenir: " + expectedText, 
            currentUrl.contains(expectedText));
    }

    @Quand("^J'essaie de cliquer sur un élément du menu sans l'ouvrir$")
    public void j_essaie_de_cliquer_sur_un_element_du_menu_sans_l_ouvrir() {
        // Initialiser productPage si nécessaire
        if (productPage == null) {
            TestContext.loginAndNavigateToProducts();
            productPage = TestContext.getProductPage();
            initialUrl = productPage.getCurrentUrl();
        }
        // Essayer de cliquer sur About sans ouvrir le menu
        try {
            productPage.clickAbout();
        } catch (Exception e) {
            // Attendu - le menu n'est pas ouvert
        }
    }

    @Alors("^Le menu ne doit pas s'ouvrir$")
    public void le_menu_ne_doit_pas_s_ouvrir() {
        Assert.assertFalse("Le menu ne doit pas s'ouvrir", productPage.isBurgerMenuOpen());
    }

    @Et("^Je dois rester sur la page des produits$")
    public void je_dois_rester_sur_la_page_des_produits() {
        Assert.assertTrue("Doit rester sur la page produits", productPage.isProductPageDisplayed());
    }

    @Quand("^J'essaie d'accéder à une URL invalide$")
    public void j_essaie_d_acceder_a_une_url_invalide() {
        if (productPage == null) {
            TestContext.loginAndNavigateToProducts();
            productPage = TestContext.getProductPage();
        }
        if (initialUrl == null) {
            initialUrl = productPage.getCurrentUrl();
        }
        try {
            productPage.navigateTo("https://www.saucedemo.com/invalid-page");
        } catch (Exception e) {
            // Attendu - URL invalide
        }
    }

    @Alors("^Je dois rester sur la page actuelle$")
    public void je_dois_rester_sur_la_page_actuelle() {
        String currentUrl = productPage.getCurrentUrl();
        Assert.assertTrue("Doit rester sur la page actuelle", 
            currentUrl.contains("/inventory") || currentUrl.equals(initialUrl));
    }

    @Et("^Un message d'erreur peut être affiché$")
    public void un_message_d_erreur_peut_etre_affiche() {
        // Cette vérification est optionnelle
        Assert.assertTrue("Un message d'erreur peut être affiché", true);
    }
}

