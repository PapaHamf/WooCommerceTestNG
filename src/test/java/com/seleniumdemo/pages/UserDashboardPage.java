package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserDashboardPage {

    protected WebDriver driver;

    @FindBys({@FindBy(className = "woocommerce-MyAccount-content"),
            @FindBy(tagName = "p"), @FindBy(tagName = "strong")})
    private List<WebElement> welcomeText;
    @FindBy(className = "entry-title")
    private WebElement myAccountHeading;
    @FindBy(linkText = "Dashboard")
    private WebElement dashboardText;

    public UserDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getWelcomeText() {
        return welcomeText.get(1).getText();
    }

    public String getMyAccountHeading() {
        return myAccountHeading.getText();
    }

    public WebElement getDashboardText() {
        return dashboardText;
    }

}
