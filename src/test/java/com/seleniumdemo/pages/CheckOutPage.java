package com.seleniumdemo.pages;

import com.seleniumdemo.utils.SeleniumHelper;
import com.seleniumdemo.utils.TestDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckOutPage {

    protected WebDriver driver;

    @FindBy(id = "billing_first_name")
    private WebElement firstName;
    @FindBy(id = "billing_last_name")
    private WebElement lastName;
    @FindBy(id = "billing_company")
    private WebElement companyName;
    @FindBy(id = "billing_country")
    private WebElement selectCountry;
    @FindBy(id = "order_comments")
    private WebElement orderComments;
    @FindBy(id = "billing_address_1")
    private WebElement streetAddress;
    // optional (suite, appartment number)
    @FindBy(id = "billing_address_2")
    private WebElement apartmentNumber;
    @FindBy(id = "billing_postcode")
    private WebElement postCode;
    @FindBy(id = "billing_city")
    private WebElement city;
    @FindBy(id = "billing_phone")
    private WebElement phoneNumber;
    @FindBy(id = "billing_email")
    private WebElement emailAddress;
    // Order summary
    @FindBys({@FindBy(className = "shop_table"), @FindBy(tagName = "tbody"), @FindBy(className = "cart-item")})
    private List<WebElement> productsList;
    @FindBys({@FindBy(className = "shop_table"), @FindBy(tagName = "tbody"), @FindBy(className = "product-name")})
    private List<WebElement> productNames;
    @FindBys({@FindBy(className = "shop_table"), @FindBy(tagName = "tbody"), @FindBy(className = "product-total")})
    private List<WebElement> productTotals;
    @FindBys({@FindBy(className = "shop_table"), @FindBy(tagName = "tfoot"), @FindBy(className = "cart-subtotal")})
    private WebElement orderSubtotal;
    @FindBys({@FindBy(className = "shop_table"), @FindBy(tagName = "tfoot"), @FindBy(className = "order-total")})
    private WebElement orderTotal;
    // @FindBy(id = "place_order")
    // private WebElement placeOrderButton;

    private By table = By.className("shop_table");
    private By tableBody = By.tagName("tbody");
    private By productName = By.className("product-name");
    private By productQuantity = By.className("product-quantity");
    private By productTotal = By.className("product-total");
    private By placeOrderBtn = By.id("place_order");
    private By countryOption = By.tagName("option");
    private By billing_state = By.id("billing_state");

    // Javascripts
    private static final String SCROLL_TO_ORDER_BUTTON_JS = "arguments[0].scrollIntoView(true);";

    /**
     * Class that holds the locators of the Checkout page and methods to get its webelements.
     * @param driver
     */
    public CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Sets the first name field on the page.
     * @param firstName Text containing the first name
     */
    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    /**
     * Sets the last name field on the page.
     * @param lastName Text containing the last name.
     */
    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    /**
     * Sets the copmany name field on the page.
     * @param companyName Text containing the company name of the user.
     */
    public void setCompanyName(String companyName) {
        this.companyName.sendKeys(companyName);
    }

    /**
     * Returns the list of Strings containing the country names.
     * @return List of country names.
     */
    public List<String> getCountryListValues() {
        List<String> countryNames = new ArrayList<String>();
        Select select = new Select(selectCountry);
        //for ( WebElement element:  selectCountry.findElements(countryOption) ) {
        for ( WebElement element:  select.getOptions() ) {
            countryNames.add(element.getText());
        }
        return countryNames;
    }

    /**
     * Selects the country using the index of the country list on the page.
     * @param index Index number of the country list.
     */
    public void selectCountryByIndex(int index) {
        Select select = new Select(selectCountry);
        select.selectByIndex(index);
    }

    /**
     * Selects the country using the name of the country list on the page.
     * @param countryName Country name of the country list.
     */
    public void selectCountryByName(String countryName) {
        Select select = new Select(selectCountry);
        select.selectByVisibleText(countryName);
    }

    /**
     * Sets the order comments field on the page.
     * @param comments Text containing the order comments.
     */
    public void setOrderComments(String comments) {
        orderComments.sendKeys(comments);
    }

    /**
     * Sets the street address field on the page.
     * @param streetAddress Text containing the street address.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress.sendKeys(streetAddress);
    }

    /**
     * Sets the apartment number field on the page.
     * @param apartmentNumber Text containing the apartment number.
     */
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber.sendKeys(apartmentNumber);
    }

    /**
     * Sets the post code field on the page.
     * @param postCode Text containing the post code.
     */
    public void setPostCode(String postCode) {
        this.postCode.sendKeys(postCode);
    }

    /**
     * Sets the city field on the page.
     * @param city Text containing the city.
     */
    public void setCity(String city) {
        this.city.sendKeys(city);
    }

    /**
     * Returns the list of Strings containing the state names.
     * @return List of state names.
     */
    public List<String> getStateListValues() {
        List<String> stateNames = new ArrayList<String>();
        Select select = new Select(driver.findElement(billing_state));
        for ( WebElement element:  select.getOptions() ) {
            stateNames.add(element.getText());
        }
        return stateNames;
    }

    /**
     * Sets the random name of the state if the field is input type or selects the random
     * available state from the list if the field is select type.
     * @param dataProvider TestDataProvider object that allows to use faker.
     */
    public void setState(TestDataProvider dataProvider) {
        String tagName = driver.findElement(billing_state).getTagName();
        if ( tagName.equals("input") ) {
            driver.findElement(billing_state).sendKeys(dataProvider.faker.address().state());
        }
        else if ( tagName.equals("select") ) {
            // fetch data
            List<String> stateNames = getStateListValues();
            Random random = new Random();
            int index = random.nextInt(0, stateNames.size() - 1);
            Select select = new Select(driver.findElement(billing_state));
            select.selectByVisibleText(stateNames.get(index));
        }
    }

    /**
     * Sets the phone number field on the page.
     * @param phoneNumber Text containing the phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.sendKeys(phoneNumber);
    }

    /**
     * Sets the email address field on the page.
     * @param email Text containing the email address.
     */
    public void setEmailAddress(String email) {
        emailAddress.sendKeys(email);
    }

    /**
     * Returns the table w/ the products list.
     * @return Table webelement.
     */
    public WebElement getTable() {
        return driver.findElement(table);
    }

    /**
     * Returns the table body /w the products list.
     * @return Table body webelement.
     */
    public WebElement getTableBody() {
        return getTable().findElement(tableBody);
    }

    /**
     * Returns the product list of the order.
     * @return List of products webelements.
     */
    public List<WebElement> getProductsList() {
        return productsList;
    }

    /**
     * Returns the list of the product names.
     * @return List of products names.
     */
    public List<String> getProductNames() {
        List<String> names = new ArrayList<String>();
        SeleniumHelper.waitForElementToExist(driver, tableBody);
        for ( WebElement element: productNames ) {
            String name = element.getText();
            names.add(name.substring(0, name.indexOf("×")).strip());
        }
        return names;
    }

    /**
     * Returns the list of product totals.
     * @return List of product totals.
     */
    public List<Double> getProductTotals() {
        List<Double> totals = new ArrayList<Double>();
        SeleniumHelper.waitForElementToExist(driver, tableBody);
        for ( WebElement element: productTotals ) {
            String total = element.getText();
            totals.add(Double.parseDouble(total.substring(0, total
                    .indexOf(" "))
                    .replace(",", ".")));
        }
        return totals;
    }

    /**
     * Returns the order Subtotal field value.
     * @return Order subtotal value.
     */
    public Double getOrderSubtotal() {
        String subtotal = orderSubtotal.findElement(By.className("amount")).getText();
        return Double.parseDouble(subtotal.substring(0, subtotal
                .indexOf(" "))
                .replace(",", "."));
    }

    /**
     * Returns the order Total field value.
     * @return Order total value.
     */
    public Double getOrderTotal() {
        String total = orderTotal.findElement(By.className("amount")).getText();
        return Double.parseDouble(total.substring(0, total
                .indexOf(" "))
                .replace(",", "."));
    }

    /**
     * Clicks the Place order button on the page.
     * @return Order Summary page object.
     */
    public OrderSummaryPage clickPlaceOrder() {
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript(SCROLL_TO_ORDER_BUTTON_JS, driver.findElement(placeOrderBtn));
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(placeOrderBtn));
        driver.findElement(placeOrderBtn).click();
        return new OrderSummaryPage(driver);
    }
}
