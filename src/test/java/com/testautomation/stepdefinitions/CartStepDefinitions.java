package com.testautomation.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.testautomation.pages.CartPage;
import com.testautomation.pages.ProductPage;
import com.testautomation.utils.TestContext;
import io.cucumber.java.fr.*;
import org.junit.Assert;

public class CartStepDefinitions {
    private CartPage cartPage;
    private ProductPage productPage;

    @Quand("^J'ajoute un article au panier$")
    public void j_ajoute_un_article_au_panier() {
        try {
            if (productPage == null) {
                TestContext.loginAndNavigateToProducts();
                productPage = TestContext.getProductPage();
            }
            productPage.addFirstProductToCart();
            Hooks.scenario.log(Status.PASS, "Article ajouté au panier");
        } catch (Exception e) {
            Hooks.scenario.log(Status.FAIL, "Erreur lors de l'ajout de l'article: " + e.getMessage());
            throw e;
        }
    }

    @Alors("^L'article doit être ajouté au panier$")
    public void l_article_doit_etre_ajoute_au_panier() {
        try {
            int cartCount = productPage.getCartItemCount();
            Assert.assertTrue("L'article doit être dans le panier", cartCount > 0);
            Hooks.scenario.log(Status.PASS, "Article confirmé dans le panier. Nombre d'articles: " + cartCount);
        } catch (AssertionError e) {
            Hooks.scenario.log(Status.FAIL, "L'article n'est pas dans le panier: " + e.getMessage());
            throw e;
        }
    }

    @Et("^Le nombre d'articles dans le panier doit être \"([^\"]*)\"$")
    public void le_nombre_d_articles_dans_le_panier_doit_etre(String expectedCount) {
        int count = Integer.parseInt(expectedCount);
        int actualCount = productPage.getCartItemCount();
        Assert.assertEquals("Le nombre d'articles doit être " + count, count, actualCount);
    }

    @Soit("^Je suis sur la page du panier$")
    public void je_suis_sur_la_page_du_panier() {
        if (productPage == null) {
            TestContext.loginAndNavigateToProducts();
            productPage = TestContext.getProductPage();
        }
        productPage.clickCartIcon();
        cartPage = new CartPage();
        Assert.assertTrue("Doit être sur la page panier", cartPage.isCartPageDisplayed());
    }

    @Et("^Le panier est vide$")
    public void le_panier_est_vide() {
        Assert.assertTrue("Le panier doit être vide", cartPage.isCartEmpty());
    }

    @Quand("^J'essaie de supprimer un article$")
    public void j_essaie_de_supprimer_un_article() {
        cartPage.removeItem(0);
    }

    @Alors("^Le panier doit rester vide$")
    public void le_panier_doit_rester_vide() {
        Assert.assertTrue("Le panier doit rester vide", cartPage.isCartEmpty());
    }

    @Et("^Aucun article ne doit être supprimé$")
    public void aucun_article_ne_doit_etre_supprime() {
        Assert.assertEquals("Aucun article ne doit être supprimé", 0, cartPage.getCartItemCount());
    }

    @Et("^J'ajoute \"([^\"]*)\" articles au panier$")
    public void j_ajoute_articles_au_panier(String count) {
        if (productPage == null) {
            TestContext.loginAndNavigateToProducts();
            productPage = TestContext.getProductPage();
        }
        int itemCount = Integer.parseInt(count);
        for (int i = 0; i < itemCount && i < productPage.getProductCount(); i++) {
            productPage.addProductToCart(i);
        }
    }

    @Et("^Je vais sur la page du panier$")
    public void je_vais_sur_la_page_du_panier() {
        productPage.clickCartIcon();
        cartPage = new CartPage();
    }

    @Quand("^J'essaie de supprimer l'article à l'index \"([^\"]*)\"$")
    public void j_essaie_de_supprimer_l_article_a_l_index(String index) {
        int idx = Integer.parseInt(index);
        if (idx < cartPage.getCartItemCount()) {
            cartPage.removeItem(idx);
        }
    }

    @Alors("^Le nombre d'articles doit rester \"([^\"]*)\"$")
    public void le_nombre_d_articles_doit_rester(String expectedCount) {
        int count = Integer.parseInt(expectedCount);
        Assert.assertEquals("Le nombre d'articles doit rester " + count, 
            count, cartPage.getCartItemCount());
    }
}

