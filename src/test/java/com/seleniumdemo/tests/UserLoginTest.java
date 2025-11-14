package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;
import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.UserDashboardPage;
import com.seleniumdemo.utils.DriverFactory;
import com.seleniumdemo.utils.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
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

    @Test()
    public void 

}
