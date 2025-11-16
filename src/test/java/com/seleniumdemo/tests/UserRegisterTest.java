package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.UserDashboardPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegisterTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void registerUserWithoutEmailTest() {
        ExtentTest test = extentReports.createTest("Register user without email adddress");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        logger.info("Clicking the Register button");
        test.log(Status.PASS, "Clicking the Register button");
        myAccountPage.clickRegisterButton();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.INVALID_EMAIL);
    }

    @Test()
    public void registerUserWithoutPasswordTest() {
        ExtentTest test = extentReports.createTest("Register user without password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = dataProvider.faker.internet().emailAddress();
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        logger.info("Clicking the Register button");
        test.log(Status.PASS, "Clicking the Register button");
        myAccountPage.clickRegisterButton();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.INVALID_PASSWORD);
    }

    @Test()
    public void registerUserWithInvalidEmailTest() {
        ExtentTest test = extentReports.createTest("Register user with invalid email address");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String invalidEmail = dataProvider.faker.internet().emailAddress().replace("@", "");
        logger.info("Entering email address: " + invalidEmail);
        test.log(Status.PASS, "Entering email address: " + invalidEmail);
        myAccountPage.enterRegisterEmail(invalidEmail);
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        logger.info("Clicking the Register button");
        test.log(Status.PASS, "Clicking the Register button");
        myAccountPage.clickRegisterButton();
        Assert.assertEquals(myAccountPage.getTooltipErrorMessage(),
                MyAccountPage.EMAIL_ADDR_WITHOUT_SIGN.replace("xxx", invalidEmail));
    }

    @Test()
    public void registerUserWithOnlyAtSignTest() {
        ExtentTest test = extentReports.createTest("Register user with invalid email " +
                "address containing only @ char");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "@";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        logger.info("Clicking the Register button");
        test.log(Status.PASS, "Clicking the Register button");
        myAccountPage.clickRegisterButton();
        Assert.assertEquals(myAccountPage.getTooltipErrorMessage(), MyAccountPage.EMAIL_ADDR_WITH_SIGN_ONLY);
    }

    @Test()
    public void registerUserWithTooShortPasswordTest() {
        ExtentTest test = extentReports.createTest("Register user with too short password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = dataProvider.faker.internet().emailAddress();
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = dataProvider.generatePlainPasswordShorterThan8Chars();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        Assert.assertEquals(myAccountPage.getPasswordStrengthMessage(), MyAccountPage.PASSWORD_WEAK);
    }

    @Test()
    public void regsiterUserWithoutDifferentCharactersTest() {
        ExtentTest test = extentReports.createTest("Register user with password without" +
                " upper case letters, numbers and special characters");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = dataProvider.faker.internet().emailAddress();
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = dataProvider.generatePasswordWithoutOtherCharacters();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        Assert.assertEquals(myAccountPage.getPasswordStrengthMessage(), MyAccountPage.PASSWORD_VERY_WEAK);
    }

    @Test()
    public void registerUserWithValidDataTest() {
        ExtentTest test = extentReports.createTest("Register user with correct email address and password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = dataProvider.faker.internet().emailAddress();
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
        Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
    }

    @Test(dataProvider = "registerUserData")
    public void registerUserWithDataProviderTest(String email, String password) {
        ExtentTest test = extentReports.createTest("Register user with correct email address " +
                "and password using data provider");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
        Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
        userDashboardPage.clickLogoutLink();
    }
}
