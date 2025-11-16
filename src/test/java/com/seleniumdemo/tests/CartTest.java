package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.CartPage;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.ProductPage;
import com.seleniumdemo.pages.ShopPage;
import com.seleniumdemo.utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CartTest extends  BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test() @Ignore
    public void addProductToCartTest()  {
        ExtentTest test = extentReports.createTest("Add random product to the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        Assert.assertTrue(shopPage.productIsAddedByNumber(randomNum));
    }

    @Test() @Ignore
    public void addMultipleProductsToCartTest() {
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
        shopPage.clickProductButtonByNumber(randomNum);
        productNames[0] = shopPage.getProductNames().get(randomNum);
        randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        productNames[1] = shopPage.getProductNames().get(randomNum);
        logger.info("Clicking View cart link");
        test.log(Status.PASS, "Clicking View cart link");
        CartPage cartPage = shopPage.clickViewCartLink(shopPage.getProductsList().get(randomNum));
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(cartPage.getTotalNumberOfProductItems(), 2);
        soft.assertEquals(cartPage.getProductNames(), Arrays.stream(productNames).toList());
    }

    @Test() @Ignore
    public void addProductFromProductPageTest() {
        ExtentTest test = extentReports.createTest("Add product from the product page");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Clicking the product tile");
        test.log(Status.PASS, "Clicking the product tile");
        // required to check the product quantity on the cart page
        String productName = shopPage.getProductNames().get(randomNum);
        ProductPage productPage = shopPage.clickProductTileByNumber(randomNum);
        String numberOfItems = String.valueOf(random.nextInt(0, 100));
        logger.info("Changing the quantity of the product to: " + numberOfItems);
        test.log(Status.PASS,"Changing the quantity of the product to: " + numberOfItems);
        productPage.setProductQuantity(numberOfItems);
        productPage.clickAddToCart();
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(productPage.getAlertMessage().contains(ProductPage.ADDED_TO_CART));
        CartPage cartPage = productPage.clickViewCartAlert();
        soft.assertEquals(cartPage.getProductQuantityByName(productName), numberOfItems);
    }

    @Test() @Ignore
    public void removeProductFromCartTest() {
        ExtentTest test = extentReports.createTest("Remove product from the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        logger.info("Clicking View cart link");
        test.log(Status.PASS, "Clicking View cart link");
        CartPage cartPage = shopPage.clickViewCartLink(shopPage.getProductsList().get(randomNum));
        List<String> productNamesBeforeRemove = cartPage.getProductNames();
        // only one product added, it's safe to use 0
        cartPage.removeProductByNumber(0);
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(cartPage.getAlertMessage().contains(CartPage.PRODUCT_REMOVED));
        soft.assertNotEquals(cartPage.getProductNames(), productNamesBeforeRemove);
    }

    @Test() @Ignore
    public void undoRemovalOfProductFromCartTest() {
        ExtentTest test = extentReports.createTest("Undo removal of product from the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        String productName = shopPage.getProductNames().get(randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        logger.info("Clicking View cart link");
        test.log(Status.PASS, "Clicking View cart link");
        CartPage cartPage = shopPage.clickViewCartLink(shopPage.getProductsList().get(randomNum));
        // only one product added, it's safe to use 0
        cartPage.removeProductByNumber(0);
        cartPage.clickUndoLink();
        Assert.assertTrue(cartPage.getProductNames().contains(productName));
    }

    @Test()
    public void removeProductFromWidgetTest() {
        ExtentTest test = extentReports.createTest("Remove product from the cart widget");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        homePage.displayCartWidget();
        List<String> productNamesBeforeRemove = homePage.getProductItemsFromWidget();
        // only one product added, it's safe to use 0
        homePage.removeWidgetProductByNumber(0);
        SeleniumHelper.waitForElementToBeClickable(driver, homePage.getInteractiveCartIcon());
        homePage.displayCartWidget();
        Assert.assertNotEquals(homePage.getProductItemsFromWidget(), productNamesBeforeRemove);
    }



}
