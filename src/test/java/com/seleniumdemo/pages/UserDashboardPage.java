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

    /**
     * Class that holds the locators of the User Dashboard page and methods to get its webelements.
     * @param driver
     */
    public UserDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Returns the welcome message text containing the username.
     * @return Welcome message text.
     */
    public String getWelcomeText() {
        return welcomeText.get(1).getText();
    }

    /**
     * Returns the User Dashboard page heading.
     * @return Header text.
     */
    public String getMyAccountHeading() {
        return myAccountHeading.getText();
    }

    /**
     * Returns the Dashboard link on the User Dashboard page.
     * @return Dashboard webelement.
     */
    public WebElement getDashboardLink() {
        return dashboardText;
    }

    /**
     * Clicks the Orders link on the User Dashboard page.
     * @return Orders page object.
     */
    public OrdersPage clickOrdersLink() {
        ordersLink.click();
        return new OrdersPage(driver);
    }

    /**
     * Clicks the Adresses link on the User Dashboard page.
     * @return Adresses page object.
     */
    public AddressesPage clickAddressesLink() {
        addressesLink.click();
        return new AddressesPage(driver);
    }

    /**
     * Clicks the Account Details link on the User Dashboard page.
     * @return Account Details page object.
     */
    public AccountDetailsPage clickAccountDetailsLink() {
        accountDetailsLink.click();
        return new AccountDetailsPage(driver);
    }

    /**
     * Clicks the Logout link on the User Dashboard page.
     * @return My Account page object.
     */
    public MyAccountPage clickLogoutLink() {
        logoutLink.click();
        return new MyAccountPage(driver);
    }

}
