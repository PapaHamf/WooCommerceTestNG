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

    /**
     * Class that holds the locators of the Addresses page and methods to get its webelements.
     * @param driver
     */
    public AddressesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Clicks the billing address edit link.
     * @return Addresses page object
     */
    public AddressesPage clickBillingAddressEdit() {
        billingAddressEdit.click();
        return this;
    }

    /**
     * Clicks the shipping address edit link.
     * @return Addresses page object
     */
    public AddressesPage clickShippingAddressEdit() {
        shippingAddressEdit.click();
        return this;
    }

    /**
     * Sets the billing address first name field on the page.
     * @param firstName Text containing the first name of the address.
     */
    public void setBillingFirstName(String firstName) {
        billingFirstName.sendKeys(firstName);
    }

    /**
     * Sets the billing address last name field on the page.
     * @param lastName Text containing the last name of the address.
     */
    public void setBillingLastName(String lastName) {
        billingLastName.sendKeys(lastName);
    }

    /**
     * Sets the billing address company name field on the page.
     * @param companyName Text containing the company name of the address.
     */
    public void setBillingCompanyName(String companyName) {
        billingCompanyName.sendKeys(companyName);
    }

    /**
     * Selects the billing address country using the index of the country list on the page.
     * @param index Index number of the country list.
     */
    public void selectBillingCountryByIndex(int index) {
        Select select = new Select(billingSelectCountry);
        select.selectByIndex(index);
    }

    /**
     * Selects the billing address country using the name of the country list on the page.
     * @param countryName Country name of the country list.
     */
    public void selectBillingCountryByName(String countryName) {
        Select select = new Select(billingSelectCountry);
        select.selectByVisibleText(countryName);
    }

    /**
     * Sets the billing address street address field on the page.
     * @param streetAddress Text containing the street address.
     */
    public void setBillingStreetAddress(String streetAddress) {
        billingStreetAddress.sendKeys(streetAddress);
    }

    /**
     * Sets the billing address apartment number field on the page.
     * @param apartmentNumber Text containing the apartment number.
     */
    public void setBillingApartmentNumber(String apartmentNumber) {
        billingApartmentNumber.sendKeys(apartmentNumber);
    }

    /**
     * Sets the billing address post code field on the page.
     * @param postCode Text containing the post code.
     */
    public void setBillingPostCode(String postCode) {
        billingPostCode.sendKeys(postCode);
    }

    /**
     * Sets the billing address city field on the page.
     * @param city Text containing the city.
     */
    public void setBillingCity(String city) {
        billingCity.sendKeys(city);
    }

    /**
     * Sets the billing address phone number field on the page.
     * @param phoneNumber Text containing the phone number.
     */
    public void setBillingPhoneNumber(String phoneNumber) {
        billingPhoneNumber.sendKeys(phoneNumber);
    }

    /**
     * Sets the billing address email address field on the page.
     * @param email Text containing the email address.
     */
    public void setBillingEmailAddress(String email) {
        billingEmailAddress.sendKeys(email);
    }

    /**
     * Sets the shipping address first name field on the page.
     * @param firstName Text containing the first name of the address.
     */
    public void setShippingFirstName(String firstName) {
        shippingFirstName.sendKeys(firstName);
    }

    /**
     * Sets the shipping address last name field on the page.
     * @param lastName Text containing the last name of the address.
     */
    public void setShippingLastName(String lastName) {
        shippingLastName.sendKeys(lastName);
    }

    /**
     * Sets the shipping address company name field on the page.
     * @param companyName Text containing the company name of the address.
     */
    public void setShippingCompanyName(String companyName) {
        shippingCompanyName.sendKeys(companyName);
    }

    /**
     * Selects the shipping address country using the index of the country list on the page.
     * @param index Index number of the country list.
     */
    public void selectShippingCountryByIndex(int index) {
        Select select = new Select(shippingSelectCountry);
        select.selectByIndex(index);
    }

    /**
     * Selects the shipping address country using the name of the country list on the page.
     * @param countryName Country name of the country list.
     */
    public void selectShippingCountryByName(String countryName) {
        Select select = new Select(shippingSelectCountry);
        select.selectByVisibleText(countryName);
    }

    /**
     * Sets the shipping address street address field on the page.
     * @param streetAddress Text containing the street address.
     */
    public void setShippingStreetAddress(String streetAddress) {
        shippingStreetAddress.sendKeys(streetAddress);
    }

    /**
     * Sets the shipping address apartment number field on the page.
     * @param apartmentNumber Text containing the apartment number.
     */
    public void setShippingApartmentNumber(String apartmentNumber) {
        shippingApartmentNumber.sendKeys(apartmentNumber);
    }

    /**
     * Sets the shipping address post code field on the page.
     * @param postCode Text containing the post code.
     */
    public void setShippingPostCode(String postCode) {
        shippingPostCode.sendKeys(postCode);
    }

    /**
     * Sets the shipping address city field on the page.
     * @param city Text containing the city.
     */
    public void setShippingCity(String city) {
        shippingCity.sendKeys(city);
    }

    /**
     * Sets the shipping address phone number field on the page.
     * @param phoneNumber Text containing the phone number.
     */
    public void setShippingPhoneNumber(String phoneNumber) {
        shippingPhoneNumber.sendKeys(phoneNumber);
    }

    /**
     * Sets the shipping address email address field on the page.
     * @param email Text containing the email address.
     */
    public void setShippingEmailAddress(String email) {
        shippingEmailAddress.sendKeys(email);
    }

    /**
     * Clicks the Save Address button on the page.
     * @return Addresses page object.
     */
    public AddressesPage clickSaveAddressButton() {
        saveAddressButton.click();
        return this;
    }

}
