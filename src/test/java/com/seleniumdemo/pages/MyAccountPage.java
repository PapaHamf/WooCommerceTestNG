package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    protected WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
