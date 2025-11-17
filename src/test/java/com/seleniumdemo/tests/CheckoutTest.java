package com.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class CheckoutTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void proceedToCheckoutTest() {
        ExtentTest test = extentReports.createTest("Proceed to checkout");
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the Shop page");
        test.log(Status.PASS, "Entering the Shop page");
        ShopPage shopPage = homePage.clickShop();
        Random random = new Random();
        int randomNum = random.nextInt(0, shopPage.getProductsList().size());
        logger.info("Adding random product to cart, " + randomNum);
        test.log(Status.PASS, "Adding random product to cart, " + randomNum);
        shopPage.clickProductButtonByNumber(randomNum);
        logger.info("Clicking View cart link");
        test.log(Status.PASS, "Clicking View cart link");
        CartPage cartPage = shopPage.clickViewCartLink(shopPage.getProductsList().get(randomNum));
        logger.info("Clicking the Proceed to checkout button");
        test.log(Status.PASS, "Clicking the Proceed to checkout button");
        CheckOutPage checkOutPage = cartPage.clickCheckoutButton();
        String firstName = dataProvider.faker.name().firstName();
        logger.info("Entering the first name: "+ firstName);
        test.log(Status.PASS, "Entering the first name: "+ firstName);
        checkOutPage.setFirstName(firstName);
        String lastName = dataProvider.faker.name().lastName();
        logger.info("Entering the last name: "+ lastName);
        test.log(Status.PASS, "Entering the last name: "+ lastName);
        checkOutPage.setLastName(lastName);
        String companyName = dataProvider.faker.company().name();
        logger.info("Entering the company name: "+ companyName);
        test.log(Status.PASS, "Entering the company name: "+ companyName);
        checkOutPage.setCompanyName(companyName);
        List<String> countries = checkOutPage.getCountryListValues();
        // String countryName = countries.get(random.nextInt(0, countries.size()));
        int index = random.nextInt(0, countries.size());
        String countryName = countries.get(index);
        logger.info("Selecting the country: "+ countryName);
        test.log(Status.PASS, "Selecting the country: "+ countryName);
        // checkOutPage.selectCountryByName(countryName);
        checkOutPage.selectCountryByIndex(index);
        String streetAddress = dataProvider.faker.address().streetAddress();
        logger.info("Entering the street address: "+ streetAddress);
        test.log(Status.PASS, "Entering the street address: "+ streetAddress);
        checkOutPage.setStreetAddress(streetAddress);
        String houseNumber = dataProvider.faker.address().streetAddressNumber();
        logger.info("Entering the address bulding number: "+ houseNumber);
        test.log(Status.PASS, "Entering the address bulding number: "+ houseNumber);
        checkOutPage.setApartmentNumber(houseNumber);
        String postCode = dataProvider.faker.address().zipCode();
        logger.info("Entering the post code: "+ postCode);
        test.log(Status.PASS, "Entering the post code: "+ postCode);
        checkOutPage.setPostCode(postCode);
        String phoneNumber = dataProvider.faker.phoneNumber().cellPhone();
        logger.info("Entering the phone number: "+ phoneNumber);
        test.log(Status.PASS, "Entering the phone number: "+ phoneNumber);
        checkOutPage.setPhoneNumber(phoneNumber);
        String city = dataProvider.faker.address().city();
        logger.info("Entering the city name: "+ city);
        test.log(Status.PASS, "Entering the city name: "+ city);
        checkOutPage.setCity(city);
        try {
            checkOutPage.setState(dataProvider);
        }
        catch (Exception e) {
            test.log(Status.FAIL, "State tag not found.");
        }
        String emailAddress = dataProvider.faker.internet().emailAddress();
        logger.info("Entering the email address: "+ emailAddress);
        test.log(Status.PASS, "Entering the email address: "+ emailAddress);
        checkOutPage.setEmailAddress(emailAddress);
        logger.info("Clicking the Place order button");
        test.log(Status.PASS, "Clicking the Place order button");
        OrderSummaryPage orderSummaryPage = checkOutPage.clickPlaceOrder();
        Assert.assertEquals(orderSummaryPage.getOrderNotice(), OrderSummaryPage.ORDER_SUCCESS);
    }


}
