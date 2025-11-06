package com.seleniumdemo.archive;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.UserDashboardPage;
import com.seleniumdemo.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void loginUserTest() {
        ExtentTest test = extentReports.createTest("Login user test");
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "mareczek@testowy.pl";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterLoginEmail(email);
        String password = "testowy123";
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterLoginPassword(password);
        UserDashboardPage userDashboardPage = myAccountPage.clickLoginButton();
        Assert.assertEquals(userDashboardPage.getWelcomeText(), email.substring(0, email.indexOf("@")));
    }

    @Test()
    public void loginUserInvalidEmailTest() {
        ExtentTest test = extentReports.createTest("Login user with invalid email test");
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "dua";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterLoginEmail(email);
        String password = "testowy123";
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterLoginPassword(password);
        myAccountPage.clickLoginButtonInvalid();
        Assert.assertTrue(myAccountPage.getErrorMessage().contains(MyAccountPage.INVALID_PASS_USER));
    }
}
