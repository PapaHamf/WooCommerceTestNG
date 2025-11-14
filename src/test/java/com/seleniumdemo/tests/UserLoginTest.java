package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.UserDashboardPage;
import com.seleniumdemo.utils.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value = {TestListener.class})
public class UserLoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @BeforeTest
    public void beforeTest() {

    }

    @Test(dataProvider = "userLogins")
    public void loginUsingProperData(String email, String password) {
        ExtentTest test = extentReports.createTest("Login user with proper email address and password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        logger.info("Entering the email address: " + email);
        test.log(Status.PASS, "Entering the email address: " + email);
        myAccountPage.enterLoginEmail(email);
        logger.info("Entering the password: " + password);
        test.log(Status.PASS, "Entering the password: " + password);
        myAccountPage.enterLoginPassword(password);
        logger.info("Clicking the Log in button");
        test.log(Status.PASS, "Clicking the Log in button");
        UserDashboardPage userDashboardPage = myAccountPage.clickLoginButton();
        Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
        userDashboardPage.clickLogoutLink();
    }

}
