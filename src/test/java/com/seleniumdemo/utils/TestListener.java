package com.seleniumdemo.utils;

import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.MyAccountPage;
import com.seleniumdemo.pages.UserDashboardPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class TestListener implements ITestListener {

    protected static WebDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            driver = DriverFactory.getDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.get("http://seleniumdemo.com/");
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        Object[] params = iTestResult.getParameters();
        myAccountPage.enterRegisterEmail((String) params[0]);
        myAccountPage.enterRegisterPassword((String) params[1]);
        UserDashboardPage userDashboardPage = myAccountPage.clickRegisterButton();
        Assert.assertTrue(userDashboardPage.getDashboardLink().isDisplayed());
        userDashboardPage.clickLogoutLink();
        driver.quit();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            driver = DriverFactory.getDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        Date randomDate = new Date();
        try {
            FileUtils.copyFile(srcFile, new File("src/test/resources/failedTest" + randomDate.toString() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
