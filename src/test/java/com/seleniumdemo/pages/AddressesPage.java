package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddressesPage {

    protected WebDriver driver;

    @FindBys({@FindBy(className = "col-1"), @FindBy(linkText = "Edit")})
    private WebElement billingAddressEdit;
    @FindBys({@FindBy(className = "col-2"), @FindBy(linkText = "Edit")})
    private WebElement shippingAddressEdit;
    @FindBy(id = "billing_first_name")
    private WebElement billingFirstName;
    @FindBy(id = "billing_last_name")
    private WebElement billingLastName;
    @FindBy(id = "billing_company")
    private WebElement billingCompanyName;
    @FindBy(id = "billing_country")
    private WebElement billingSelectCountry;
    @FindBy(id = "order_comments")
    private WebElement billingOrderComments;
    @FindBy(id = "billing_address_1")
    private WebElement billingStreetAddress;
    @FindBy(id = "billing_address_2")
    private WebElement billingApartmentNumber;
    @FindBy(id = "billing_postcode")
    private WebElement billingPostCode;
    @FindBy(id = "billing_city")
    private WebElement billingCity;
    @FindBy(id = "billing_phone")
    private WebElement billingPhoneNumber;
    @FindBy(id = "billing_email")
    private WebElement billingEmailAddress;
    @FindBy(id = "shipping_first_name")
    private WebElement shippingFirstName;
    @FindBy(id = "shipping_last_name")
    private WebElement shippingLastName;
    @FindBy(id = "shipping_company")
    private WebElement shippingCompanyName;
    @FindBy(id = "shipping_country")
    private WebElement shippingSelectCountry;
    @FindBy(id = "order_comments")
    private WebElement shippingOrderComments;
    @FindBy(id = "shipping_address_1")
    private WebElement shippingStreetAddress;
    @FindBy(id = "shipping_address_2")
    private WebElement shippingApartmentNumber;
    @FindBy(id = "shipping_postcode")
    private WebElement shippingPostCode;
    @FindBy(id = "shipping_city")
    private WebElement shippingCity;
    @FindBy(id = "shipping_phone")
    private WebElement shippingPhoneNumber;
    @FindBy(id = "shipping_email")
    private WebElement shippingEmailAddress;
    @FindBy(name = "save_address")
    private WebElement saveAddressButton;

    public AddressesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AddressesPage clickBillingAddressEdit() {
        billingAddressEdit.click();
        return this;
    }

    public AddressesPage clickShippingAddressEdit() {
        shippingAddressEdit.click();
        return this;
    }

    public void setBillingFirstName(String firstName) {
        billingFirstName.sendKeys(firstName);
    }

    public void setBillingLastName(String lastName) {
        billingLastName.sendKeys(lastName);
    }

    public void setBillingCompanyName(String companyName) {
        billingCompanyName.sendKeys(companyName);
    }

    public void selectBillingCountryByIndex(int index) {
        Select select = new Select(billingSelectCountry);
        select.selectByIndex(index);
    }

    public void selectBillingCountryByName(String countryName) {
        Select select = new Select(billingSelectCountry);
        select.selectByVisibleText(countryName);
    }

    public void setBillingOrderComments(String comments) {
        billingOrderComments.sendKeys(comments);
    }

    public void setBillingStreetAddress(String streetAddress) {
        billingStreetAddress.sendKeys(streetAddress);
    }

    public void setBillingApartmentNumber(String apartmentNumber) {
        billingApartmentNumber.sendKeys(apartmentNumber);
    }

    public void setBillingPostCode(String postCode) {
        billingPostCode.sendKeys(postCode);
    }

    public void setBillingCity(String city) {
        billingCity.sendKeys(city);
    }

    public void setBillingPhoneNumber(String phoneNumber) {
        billingPhoneNumber.sendKeys(phoneNumber);
    }

    public void setBillingEmailAddress(String email) {
        billingEmailAddress.sendKeys(email);
    }

    public void setShippingFirstName(String firstName) {
        shippingFirstName.sendKeys(firstName);
    }

    public void setShippingLastName(String lastName) {
        shippingLastName.sendKeys(lastName);
    }

    public void setShippingCompanyName(String companyName) {
        shippingCompanyName.sendKeys(companyName);
    }

    public void selectShippingCountryByIndex(int index) {
        Select select = new Select(shippingSelectCountry);
        select.selectByIndex(index);
    }

    public void selectShippingCountryByName(String countryName) {
        Select select = new Select(shippingSelectCountry);
        select.selectByVisibleText(countryName);
    }

    public void setShippingOrderComments(String comments) {
        shippingOrderComments.sendKeys(comments);
    }

    public void setShippingStreetAddress(String streetAddress) {
        shippingStreetAddress.sendKeys(streetAddress);
    }

    public void setShippingApartmentNumber(String apartmentNumber) {
        shippingApartmentNumber.sendKeys(apartmentNumber);
    }

    public void setShippingPostCode(String postCode) {
        shippingPostCode.sendKeys(postCode);
    }

    public void setShippingCity(String city) {
        shippingCity.sendKeys(city);
    }

    public void setShippingPhoneNumber(String phoneNumber) {
        shippingPhoneNumber.sendKeys(phoneNumber);
    }

    public void setShippingEmailAddress(String email) {
        shippingEmailAddress.sendKeys(email);
    }

    public AddressesPage clickSaveAddressButton() {
        saveAddressButton.click();
        return this;
    }

}
