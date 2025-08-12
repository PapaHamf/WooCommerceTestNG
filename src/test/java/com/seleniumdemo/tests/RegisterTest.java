package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.RegisterUserPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    //sprawdzić, jak zapisywać do pliku
    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void registerUserTest() {
        ExtentTest test = extentReports.createTest("Register User Test");
        HomePage homePage = new HomePage(driver);
        RegisterUserPage registerUserPage = homePage.clickMyAccount();
        String email = "mareczek@testowy.pl";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        registerUserPage.enterRegisterEmail(email);
        String password = "testowy123";
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        registerUserPage.enterRegisterPassword(password);
        MyAccountPage myAccountPage = registerUserPage.clickRegisterButton();
        // jeden sposób
        Assert.assertEquals(myAccountPage.getWelcomeText(), email.substring(0, email.indexOf("@")));
        // drugi sposób
        Assert.assertTrue(myAccountPage.getDashboardText().isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
