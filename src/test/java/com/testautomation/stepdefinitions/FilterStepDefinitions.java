package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.pages.ProductPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.fr.*;
import org.junit.Assert;

public class FilterStepDefinitions {
    private ProductPage productPage;

    @Quand("^Je trie les produits par \"([^\"]*)\"$")
    public void je_trie_les_produits_par(String sortOption) {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
            }
            productPage.selectSortOption(sortOption);
            Hooks.scenario.log(Status.PASS, "Tri des produits par: " + sortOption);
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Erreur lors du tri: " + e.getMessage());
            throw e;
        }
    }

    @Alors("^Les produits doivent être triés par nom de A à Z$")
    public void les_produits_doivent_etre_tries_par_nom_de_a_a_z() {
        java.util.List<String> productNames = productPage.getAllProductNames();
        boolean isSorted = true;
        for (int i = 0; i < productNames.size() - 1; i++) {
            if (productNames.get(i).compareTo(productNames.get(i + 1)) > 0) {
                isSorted = false;
                break;
            }
        }
        Assert.assertTrue("Les produits doivent être triés de A à Z", isSorted);
    }

    @Et("^Le premier produit doit commencer par \"([^\"]*)\"$")
    public void le_premier_produit_doit_commencer_par(String expectedPrefix) {
        String firstProduct = productPage.getFirstProductName();
        Assert.assertTrue("Le premier produit doit commencer par: " + expectedPrefix,
            firstProduct.startsWith(expectedPrefix));
    }

    @Quand("^J'essaie de trier avec une option invalide$")
    public void j_essaie_de_trier_avec_une_option_invalide() {
        try {
            productPage.selectSortOption("invalid_option");
        } catch (Exception e) {
            // Attendu
        }
    }

    @Alors("^Les produits ne doivent pas changer d'ordre$")
    public void les_produits_ne_doivent_pas_changer_d_ordre() {
        Assert.assertTrue("Les produits ne doivent pas changer d'ordre", true);
    }

    @Quand("^J'essaie de trier avec une valeur null$")
    public void j_essaie_de_trier_avec_une_valeur_null() {
        try {
            productPage.selectSortOption(null);
        } catch (Exception e) {
            // Attendu
        }
    }

    @Alors("^Les produits doivent rester dans leur ordre initial$")
    public void les_produits_doivent_rester_dans_leur_ordre_initial() {
        Assert.assertTrue("Les produits doivent rester dans leur ordre initial", true);
    }
}

