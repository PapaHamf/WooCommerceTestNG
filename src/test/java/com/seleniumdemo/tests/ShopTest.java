package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.ShopPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Random;

public class ShopTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void addProductToCartTest() {
        ExtentTest test = extentReports.createTest("Add product to cart test");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the shop page");
        test.log(Status.PASS, "Entering the shop page");
        ShopPage shopPage = homePage.clickShop();
        // losowy wybór produktu
        Random random = new Random();
        int randomNum = random.nextInt(3);
        shopPage.clickProductButtonNumber(randomNum);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
