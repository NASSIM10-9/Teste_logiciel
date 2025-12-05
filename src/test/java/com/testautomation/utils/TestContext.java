package com.testautomation.utils;

import com.testautomation.pages.LoginPage;
import com.testautomation.pages.ProductPage;
import org.junit.Assert;

public class TestContext {
    private static ProductPage productPage;
    private static LoginPage loginPage;

    public static void loginAndNavigateToProducts() {
        loginPage = new LoginPage();
        loginPage.navigateToLogin();
        loginPage.login("standard_user", "secret_sauce");
        productPage = new ProductPage();
        Assert.assertTrue("Doit être sur la page produits", productPage.isProductPageDisplayed());
    }

    public static ProductPage getProductPage() {
        if (productPage == null) {
            loginAndNavigateToProducts();
        }
        return productPage;
    }

    public static LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static void reset() {
        productPage = null;
        loginPage = null;
    }
}

