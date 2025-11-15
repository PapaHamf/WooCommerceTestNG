package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;
import com.seleniumdemo.pages.*;
import com.seleniumdemo.utils.DriverFactory;
import com.seleniumdemo.utils.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class UserLoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @BeforeTest
    public void beforeTest() throws IOException, CsvException {
        try {
            driver = DriverFactory.getDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.get("http://seleniumdemo.com/");
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        List<String[]> data = TestDataProvider.readCSV("src/test/resources/" + userLoginsCSV);
        int noOfRecords = data.size();
        for ( int i = 0; i <= noOfRecords - 1; i++ ) {
            myAccountPage.enterRegisterEmail(data.get(i)[0]);
            myAccountPage.enterRegisterPassword(data.get(i)[1]);
            UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
            if ( myAccountPage.getErrorMessage().equals(MyAccountPage.ACCOUNT_EXISTS) ) {
                break;
            }
            Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
            userDashboardPage.clickLogoutLink();
        }
        driver.quit();
    }

    @Test(dataProvider = "userLogins") @Ignore
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

    @Test() @Ignore
    public void loginUserWithoutEmailAddress() {
        ExtentTest test = extentReports.createTest("Login user without email address");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering the password: " + password);
        test.log(Status.PASS, "Entering the password: " + password);
        myAccountPage.enterLoginPassword(password);
        logger.info("Clicking the Log in button");
        test.log(Status.PASS, "Clicking the Log in button");
        UserDashboardPage userDashboardPage = myAccountPage.clickLoginButton();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.INVALID_USER);
    }

    @Test() @Ignore
    public void loginUserWithoutPassword() {
        ExtentTest test = extentReports.createTest("Login user without password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "test@test.pl";
        logger.info("Entering the email address: " + email);
        test.log(Status.PASS, "Entering the email address: " + email);
        myAccountPage.enterLoginEmail(email);
        logger.info("Clicking the Log in button");
        test.log(Status.PASS, "Clicking the Log in button");
        UserDashboardPage userDashboardPage = myAccountPage.clickLoginButton();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.EMPTY_PASSWORD);
    }

    @Test() @Ignore
    public void loginUserWithInvalidPassword() {
        ExtentTest test = extentReports.createTest("Login user with incorrect password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "test@test.pl";
        logger.info("Entering the email address: " + email);
        test.log(Status.PASS, "Entering the email address: " + email);
        myAccountPage.enterLoginEmail(email);
        String password = dataProvider.generateCorrectPassword();
        logger.info("Entering the password: " + password);
        test.log(Status.PASS, "Entering the password: " + password);
        myAccountPage.enterLoginPassword(password);
        logger.info("Clicking the Log in button");
        test.log(Status.PASS, "Clicking the Log in button");
        UserDashboardPage userDashboardPage = myAccountPage.clickLoginButton();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.INVALID_PASS_USER);
    }

    @Test() @Ignore
    public void loginUserWithoutData() {
        ExtentTest test = extentReports.createTest("Login user with incorrect password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        logger.info("Clicking the Log in button");
        test.log(Status.PASS, "Clicking the Log in button");
        UserDashboardPage userDashboardPage = myAccountPage.clickLoginButton();
        Assert.assertEquals(myAccountPage.getErrorMessage(), MyAccountPage.INVALID_USER);
    }

    @Test()
    public void resetUserPassword() {
        // message data
        String sender = "Selenium Demo";
        String subject = "Reset password";
        ExtentTest test = extentReports.createTest("Reset the user account password");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the My Account page");
        test.log(Status.PASS, "Entering the My Account page");
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        String email = "mareczek_testowy@int.pl";
        logger.info("Entering email address: " + email);
        test.log(Status.PASS, "Entering email address: " + email);
        myAccountPage.enterRegisterEmail(email);
        String password = "m4reczek1234!1";
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        myAccountPage.enterRegisterPassword(password);
        UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
        userDashboardPage.clickLogoutLink();
        logger.info("Clicking the Lost the password link");
        test.log(Status.PASS, "Clicking the Lost the password link");
        myAccountPage.clickLostPasswordLink();
        logger.info("Entering reset password email address: " + email);
        test.log(Status.PASS, "Entering reset password email address: " + email);
        myAccountPage.enterLostPasswordEmail(email);
        logger.info("Clicking the Reset password button");
        test.log(Status.PASS, "Clicking the Reset password button");
        IntWebmailPage intWebmailPage = myAccountPage.clickResetPasswordButton();
        String currentHandle = driver.getWindowHandle();
        logger.info("Switching to new tab");
        test.log(Status.PASS, "Switching to new tab");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(IntWebmailPage.TICKET_SERVER);
        logger.info("Entering reset password email address: " + email);
        test.log(Status.PASS, "Entering reset password email address: " + email);
        intWebmailPage.enterEmailAddress(email);
        logger.info("Entering password: " + password);
        test.log(Status.PASS, "Entering password: " + password);
        intWebmailPage.enterEmailPassword(password);
        logger.info("Clicking the Loguję się button");
        test.log(Status.PASS, "Clicking the Loguję się button");
        IntInboxPage intInboxPage = intWebmailPage.clickLoginButton();
        Assert.assertTrue(intInboxPage.getNumberofMessagesBySender(sender) > 0);
        logger.info("Clicking the message w/ proper sender and subject");
        test.log(Status.PASS, "Clicking the message w/ proper sender and subject");
        intInboxPage.clickMessageBySender(sender, subject);
        logger.info("Clicking the Reset password link");
        test.log(Status.PASS, "Clicking the Reset password link");
        intInboxPage.clickResetLink();
        driver.close();
        driver.switchTo().window(currentHandle);
        String newPassword = dataProvider.generateCorrectPassword();
        logger.info("Entering the new password: " + newPassword);
        test.log(Status.PASS, "Entering the new password: " + newPassword);
        myAccountPage.enterNewPassword(newPassword);
        logger.info("Entering the repeated new password: " + newPassword);
        test.log(Status.PASS, "Entering the repeated new password: " + newPassword);
        myAccountPage.enterRepeatNewPassword(newPassword);
        logger.info("Clicking the Save password button");
        test.log(Status.PASS, "Clicking the Save password button");
        myAccountPage.clickSavePasswordButton();
        Assert.assertEquals(userDashboardPage.getDashboardMessage(), UserDashboardPage.PASSWORD_CHANGED);
    }
}
