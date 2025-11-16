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
    protected static final TestDataProvider dataProvider = new TestDataProvider();

    //variables
    // Number of user regs data sets
    protected int noOfUserRegs = 5;
    // Name of the file used for user logging
    protected String userLoginsCSV = "userlogins.csv";

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
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.get("http://seleniumdemo.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider()
    public Object[][] userLogins() throws IOException, CsvException {
        List<String[]> data = TestDataProvider.readCSV("src/test/resources/" + userLoginsCSV);
        // number of users records
        int noOfRecords = data.size();
        // number of properties in user record
        int noOfProperties = data.getFirst().length;
        Object[][] userLogins = new Object[noOfRecords][noOfProperties];
        for ( int i = 0; i <= noOfRecords - 1; i++ ) {
            System.arraycopy(data.get(i), 0, userLogins[i], 0, noOfProperties);
        }
        return userLogins;
    }

    @DataProvider()
    public Object[][] registerUserData() {
        Object[][] userData = new Object[noOfUserRegs][2];
        for (int i = 0; i <= noOfUserRegs; i++ ) {
            userData[i][0] = dataProvider.faker.internet().emailAddress();
            userData[i][1] = dataProvider.generateCorrectPassword();
        }
        return userData;
    }

}