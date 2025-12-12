package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.pages.CartPage;
import com.testautomation.pages.ProductPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class CartStepDefinitions {
    private CartPage cartPage = new CartPage();
    private ProductPage productPage;

    @When("J'ajoute un article au panier")
    public void j_ajoute_un_article_au_panier() {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
            }
            productPage.addFirstProductToCart();
            Hooks._scenario.log(Status.PASS, "Article ajouté au panier");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de l'ajout de l'article");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @Then("L'article doit être ajouté au panier")
    public void l_article_doit_etre_ajoute_au_panier() {
        try {
            int cartCount = productPage.getCartItemCount();
            Assertions.assertTrue(cartCount > 0, "L'article doit être dans le panier");
            Hooks._scenario.log(Status.PASS, "Article confirmé dans le panier. Nombre d'articles: " + cartCount);
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "L'article n'est pas dans le panier");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Le nombre d'articles dans le panier doit être {string}")
    public void le_nombre_d_articles_dans_le_panier_doit_etre(String expectedCount) {
        try {
            int count = Integer.parseInt(expectedCount);
            int actualCount = productPage.getCartItemCount();
            Assertions.assertEquals(count, actualCount, "Le nombre d'articles doit être " + count);
            Hooks._scenario.log(Status.PASS, "Nombre d'articles correct: " + actualCount);
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Nombre d'articles incorrect");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @Given("Je suis sur la page du panier")
    public void je_suis_sur_la_page_du_panier() {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
            }
            productPage.clickCartIcon();

            Assertions.assertTrue(cartPage.isCartPageDisplayed(), "Doit être sur la page panier");
            Hooks._scenario.log(Status.PASS, "Sur la page panier");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Pas sur la page panier");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
             throw t; 
        }
    }

    @And("Le panier est vide")
    public void le_panier_est_vide() {
        try {
            Assertions.assertTrue(cartPage.isCartEmpty(), "Le panier doit être vide");
            Hooks._scenario.log(Status.PASS, "Panier vide comme attendu");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Le panier n'est pas vide");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @When("J'essaie de supprimer un article")
    public void j_essaie_de_supprimer_un_article() {
        try {
            cartPage.removeItem(0);
            Hooks._scenario.log(Status.PASS, "Tentative de suppression d'article");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de la suppression");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @Then("Le panier doit rester vide")
    public void le_panier_doit_rester_vide() {
        try {
            Assertions.assertTrue(cartPage.isCartEmpty(), "Le panier doit rester vide");
            Hooks._scenario.log(Status.PASS, "Panier resté vide");
        } catch (Throwable t) {
            Hooks._scenario.log(Status.FAIL, "Le panier n'est pas resté vide");
            Hooks._scenario.log(Status.FAIL, t.getMessage());
            throw t;
        }
    }

    @And("Aucun article ne doit être supprimé")
    public void aucun_article_ne_doit_etre_supprime() {
        try {
            Assertions.assertEquals(0, cartPage.getCartItemCount(), "Aucun article ne doit être supprimé");
            Hooks._scenario.log(Status.PASS, "Aucun article supprimé");
        } catch (Throwable t) {
             Hooks._scenario.log(Status.FAIL, "Des articles ont été supprimés (ou ajoutés ?)");
             Hooks._scenario.log(Status.FAIL, t.getMessage());
             throw t;
        }
    }

    @And("J'ajoute {string} articles au panier")
    public void j_ajoute_articles_au_panier(String count) {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
            }
            int itemCount = Integer.parseInt(count);
            for (int i = 0; i < itemCount && i < productPage.getProductCount(); i++) {
                productPage.addProductToCart(i);
            }
            Hooks._scenario.log(Status.PASS, count + " articles ajoutés au panier");
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur lors de l'ajout multiple");
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @And("Je vais sur la page du panier")
    public void je_vais_sur_la_page_du_panier() {
        try {
            productPage.clickCartIcon();

            Hooks._scenario.log(Status.PASS, "Navigation vers panier");
        } catch (Exception e) {
             Hooks._scenario.log(Status.FAIL, "Erreur navigation panier");
             Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @When("J'essaie de supprimer l'article à l'index {string}")
    public void j_essaie_de_supprimer_l_article_a_l_index(String index) {
        try {
            int idx = Integer.parseInt(index);
            if (idx < cartPage.getCartItemCount()) {
                cartPage.removeItem(idx);
            }
            Hooks._scenario.log(Status.PASS, "Suppression article index " + index);
        } catch (Exception e) {
            Hooks._scenario.log(Status.FAIL, "Erreur suppression index " + index);
            Hooks._scenario.log(Status.FAIL, e.getMessage());
        }
    }

    @Then("Le nombre d'articles doit rester {string}")
    public void le_nombre_d_articles_doit_rester(String expectedCount) {
        try {
            int count = Integer.parseInt(expectedCount);
            Assertions.assertEquals(count, cartPage.getCartItemCount(), "Le nombre d'articles doit rester " + count);
            Hooks._scenario.log(Status.PASS, "Compte d'articles correct: " + count);
        } catch (Throwable t) {
             Hooks._scenario.log(Status.FAIL, "Compte d'articles incorrect");
             Hooks._scenario.log(Status.FAIL, t.getMessage());
             throw t;
        }
    }
}

