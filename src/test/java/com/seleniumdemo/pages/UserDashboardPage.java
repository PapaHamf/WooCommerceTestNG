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
    @FindBy(linkText = "Orders")
    private WebElement ordersLink;
    @FindBy(linkText = "Addresses")
    private WebElement addressesLink;
    @FindBy(linkText = "Account details")
    private WebElement accountDetailsLink;
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

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

    public OrdersPage clickOrdersLink() {
        ordersLink.click();
        return new OrdersPage(driver);
    }

    public AddressesPage clickAddressesLink() {
        addressesLink.click();
        return new AddressesPage(driver);
    }

    public AccountDetailsPage clickAccountDetailsLink() {
        accountDetailsLink.click();
        return new AccountDetailsPage(driver);
    }

    public MyAccountPage clickLogoutLink() {
        logoutLink.click();
        return new MyAccountPage(driver);
    }

}
