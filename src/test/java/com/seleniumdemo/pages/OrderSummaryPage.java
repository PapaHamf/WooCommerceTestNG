package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPage {

    protected WebDriver driver;

    @FindBy(className = "woocommerce-notice")
    private WebElement orderNotice;

    public OrderSummaryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getOrderNotice() {
        return orderNotice.getText();
    }

}
