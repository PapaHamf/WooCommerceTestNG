package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.exceptions.CsvException;
import com.seleniumdemo.utils.DriverFactory;
import com.seleniumdemo.utils.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseTest {

    protected static WebDriver driver;
    // Added as fields to be able to use them in all methods.
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

    @DataProvider()
    public Object[][] userLogins() throws IOException, CsvException {
        List<String[]> data = TestDataProvider.readCSV("src/test/resources/userlogins.csv");
        // number of users records
        int noOfRecords = data.size();
        // number of properties in user record
        int noOfProperties = data.getFirst().length;
        Object[][] userLogins = new Object[noOfRecords][noOfProperties];
        for (int i = 0; i <= noOfRecords - 1; i++) {
            System.arraycopy(data.get(i), 0, userLogins[i], 0, noOfProperties);
        }
        return userLogins;
    }

}