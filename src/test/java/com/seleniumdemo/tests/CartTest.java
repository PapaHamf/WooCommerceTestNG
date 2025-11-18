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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CartTest extends  BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
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

    @Test()
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
        soft.assertAll();
    }

    @Test()
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
        logger.info("Adding the product to cart");
        test.log(Status.PASS, "Adding the product to cart");
        productPage.clickAddToCart();
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(productPage.getAlertMessage().contains(ProductPage.ADDED_TO_CART));
        logger.info("Clicking the View cart button");
        test.log(Status.PASS, "Clicking the View cart button");
        CartPage cartPage = productPage.clickViewCartAlert();
        soft.assertEquals(cartPage.getProductQuantityByName(productName), numberOfItems);
        soft.assertAll();
    }

    @Test()
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
        logger.info("Clicking the Remove button on the cart page");
        test.log(Status.PASS, "Clicking the Remove button on the cart page");
        // only one product added, it's safe to use 0
        cartPage.removeProductByNumber(0);
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(cartPage.getAlertMessage().contains(CartPage.PRODUCT_REMOVED));
        soft.assertNotEquals(cartPage.getProductNames(), productNamesBeforeRemove);
        soft.assertAll();
    }

    @Test()
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
        logger.info("Clicking the Remove button on the cart page");
        test.log(Status.PASS, "Clicking the Remove button on the cart page");
        // only one product added, it's safe to use 0
        cartPage.removeProductByNumber(0);
        logger.info("Clicking the Undo link");
        test.log(Status.PASS, "Clicking the Undo link");
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
        logger.info("Displaying the widget");
        test.log(Status.PASS,"Displaying the widget");
        homePage.displayCartWidget();
        List<String> productNamesBeforeRemove = homePage.getProductItemsFromWidget();
        logger.info("Clicking remove button (x) in the widget");
        test.log(Status.PASS, "Clicking remove button (x) in the widget");
        // only one product added, it's safe to use 0
        homePage.removeWidgetProductByNumber(0);
        SeleniumHelper.waitForElementToBeClickable(driver, homePage.getInteractiveCartIcon());
        logger.info("Displaying the widget");
        test.log(Status.PASS,"Displaying the widget");
        homePage.displayCartWidget();
        Assert.assertNotEquals(homePage.getProductItemsFromWidget(), productNamesBeforeRemove);
    }

    @Test()
    public void checkProductsQuantityPriceInCartTest() {
        ExtentTest test = extentReports.createTest("Verify the products quantity and price in the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        String productName = shopPage.getProductNames().get(randomNum);
        logger.info("Clicking View cart link");
        test.log(Status.PASS, "Clicking View cart link");
        CartPage cartPage = shopPage.clickViewCartLink(shopPage.getProductsList().get(randomNum));
        // fetching the data
        String productPrice = cartPage.getProductPriceByName(productName);
        String productOldTotal = cartPage.getProductTotalByName(productName);
        String newQty = "10";
        logger.info("Changing the product (" + productName + ") quantity to " + newQty);
        test.log(Status.PASS, "Changing the product (" + productName + ") quantity to " + newQty);
        cartPage.setProductQuantityByName(productName, newQty);
        logger.info("Clicking the Update Cart button");
        test.log(Status.PASS, "Clicking the Update Cart button");
        SeleniumHelper.waitForElementToBeClickable(driver, cartPage.getUpdateCartButton());
        cartPage.clickUpdateCart();
        // fetching new data
        String productNewQty = cartPage.getProductQuantityByName(productName);
        String productNewTotal = cartPage.getProductTotalByName(productName);
        SoftAssert soft = new SoftAssert();
        soft.assertNotEquals(productNewTotal, productOldTotal);
        soft.assertEquals(productNewQty, newQty);
        Double prodPrice = Double.parseDouble(productPrice
                .substring(0, productPrice.indexOf(" zł"))
                .replace(",", ".")
                .replace(" ", ""));
        Double prodTotal = Double.parseDouble(productNewTotal
                .substring(0, productNewTotal.indexOf(" zł"))
                .replace(",", ".")
                .replace(" ", ""));
        int prodQty = Integer.parseInt(newQty);
        soft.assertEquals(prodTotal, (prodPrice * prodQty));
        soft.assertAll();
    }

    @Test()
    public void checkProductsTotalInCartTest() {
        ExtentTest test = extentReports.createTest("Verify the products total in the cart");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        int productQty = 10;
        logger.info("Adding random " + productQty + " products to cart");
        test.log(Status.PASS, "Adding random " + productQty + " products to cart");
        shopPage.addMultipleProducts(productQty);
        logger.info("Displaying the widget");
        test.log(Status.PASS,"Displaying the widget");
        homePage.displayCartWidget();
        logger.info("Clicking View cart link");
        test.log(Status.PASS, "Clicking View cart link");
        CartPage cartPage = homePage.clickWidgetViewCartButton();
        // fetching data
        List<Double> productPrices = cartPage.getProductPrices();
        List<Integer> productQuantities = cartPage.getProductQuantities();
        Double productTotal = cartPage.getOrderTotal();
        double productsSum = 0.0;
        if ( productPrices.size() == productQuantities.size() ) {
            for (int i = 0; i < productPrices.size(); i++) {
                productsSum += productPrices.get(i) * productQuantities.get(i);
            }
        }
        Assert.assertEquals(productTotal, productsSum);
    }

    @Test()
    public void addProductFromSearchResultsTest() {
        ExtentTest test = extentReports.createTest("Add product from search results on Shop page");
        HomePage homePage = new HomePage(driver);
        logger.info("Clicking the search icon (magnifying glass)");
        test.log(Status.PASS, "Clicking the search icon (magnifying glass)");
        homePage.clickSearchButton();
        String productName = "GIT basics";
        logger.info("Entering the product name: " + productName);
        test.log(Status.PASS, "Entering the product name: " + productName);
        homePage.setSearchTerm(productName);
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(homePage.searchProductFound(productName));
        logger.info("Clicking the found product (" +productName + ") tile");
        test.log(Status.PASS, "Clicking the found product (" +productName + ") tile");
        ProductPage productPage = homePage.clickFoundProductTitle(productName);
        logger.info("Clicking the Add to cart button");
        test.log(Status.PASS, "Clicking the Add to cart button");
        productPage.clickAddToCart();
        soft.assertTrue(productPage.getAlertMessage().contains(ProductPage.ADDED_TO_CART));
        soft.assertAll();
    }
}
