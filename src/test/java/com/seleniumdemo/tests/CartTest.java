package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.CartPage;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.ShopPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.Random;

public class CartTest extends  BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void addProductcToCart()  {
        ExtentTest test = extentReports.createTest("Add random product to the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonNumber(randomNum);
        Assert.assertTrue(shopPage.productIsAddedByNumber(randomNum));
    }

    @Test()
    public void addMultipleProductsToCart() {
        String[] productNames = new String[2];
        ExtentTest test = extentReports.createTest("Add two random products to the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonNumber(randomNum);
        productNames[0] = shopPage.getProductNames().get(randomNum);
        randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonNumber(randomNum);
        productNames[1] = shopPage.getProductNames().get(randomNum);
        logger.info("Clicking View cart link");
        CartPage cartPage = shopPage.clickViewCartLink(shopPage.getProductsList().get(randomNum));
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(cartPage.getTotalNumberOfProductItems(), 2);
        soft.assertEquals(cartPage.getProductNames(), Arrays.stream(productNames).toList());
    }


}
