package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.seleniumdemo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    // dodanie jako pola, aby można było używać we wszystkich metodach
    protected static ExtentSparkReporter sparkReporter;
    protected static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuite() {
        sparkReporter = new ExtentSparkReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void afterSuite() {
        extentReports.flush();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.get("http://seleniumdemo.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}