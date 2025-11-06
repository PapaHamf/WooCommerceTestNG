package com.seleniumdemo.archive;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.UserDashboardPage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    //sprawdzić, jak zapisywać do pliku
    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void registerUserTest() {
        ExtentTest test = extentReports.createTest("Register user test");
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        int random = (int) (Math.random() * 1000);
        String email = "mareczek" + random + "@testowy.pl";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = "testowy123";
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
        // jeden sposób
        Assert.assertEquals(userDashboardPage.getWelcomeText(), email.substring(0, email.indexOf("@")));
        // drugi sposób
        Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    @Test()
    public void registerExistingEmailTest() {
        ExtentTest test = extentReports.createTest("Register using existing email address test");
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "mareczek@testowy.pl";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = "testowy123";
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        myAccountPage.clickRegisterButtonInvalid();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.ACCOUNT_EXISTS);
    }
}
