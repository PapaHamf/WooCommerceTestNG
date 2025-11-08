package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.UserDashboardPage;
import com.seleniumdemo.utils.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegisterTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();
    private static final TestDataProvider dataProvider = new TestDataProvider();

    @Test()
    public void registerUserWithoutEmail() {
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
    public void registerUserWithoutPassword() {
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
    public void registerUserWithInvalidEmail() {
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
    public void registerUserWithOnlyAtSign() {
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
    public void registerUserWithTooShortPassword() {
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
    public void regsiterUserWithoutDifferentCharacters() {
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
    public void registerUserWithValidData() {
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
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
        Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
    }
}
