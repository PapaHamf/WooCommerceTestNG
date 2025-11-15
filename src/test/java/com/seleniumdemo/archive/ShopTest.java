package com.seleniumdemo.archive;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumdemo.models.Customer;
import com.seleniumdemo.pages.*;
import com.seleniumdemo.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ShopTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();

    @Test()
    public void checkoutTest() {
        ExtentTest test = extentReports.createTest("Add product to cart test");
        Customer customer = new Customer();
        HomePage homePage = new HomePage(driver);
        logger.info("Entering the shop page");
        test.log(Status.PASS, "Entering the shop page");
        ShopPage shopPage = homePage.clickShop();
        // losowy wybór produktu
        Random random = new Random();
        int randomNum = random.nextInt(3);
        shopPage.clickProductButtonByNumber(randomNum);
        shopPage.clickProductButtonByNumber(0);
        CartPage cartPage = homePage.clickViewCartButton();
        CheckOutPage checkOutPage = cartPage.clickCheckoutButton();
        checkOutPage.setFirstName(customer.getFirstName());
        checkOutPage.setLastName(customer.getLastName());
        checkOutPage.setCompanyName(customer.getCompanyName());
        checkOutPage.selectCountryByName(customer.getCountry());
        checkOutPage.setStreetAddress(customer.getStreet());
        checkOutPage.setApartmentNumber(customer.getHouseNumber());
        checkOutPage.setPostCode(customer.getPostCode());
        checkOutPage.setPhoneNumber(customer.getPhoneNumber());
        checkOutPage.setCity(customer.getCity());
        checkOutPage.setEmailAddress(customer.getEmail());
        OrderSummaryPage orderSummaryPage = checkOutPage.clickPlaceOrder();
        Assert.assertEquals(orderSummaryPage.getOrderNotice(), "Thank you. Your order has been received.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
