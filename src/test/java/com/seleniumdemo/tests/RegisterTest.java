package com.seleniumdemo.tests;

import com.seleniumdemo.pages.HomePage;
import com.seleniumdemo.pages.RegisterUserPage;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void registerUserTest() {
        HomePage homePage = new HomePage(driver);
        RegisterUserPage registerUserPage = homePage.clickMyAccount();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
