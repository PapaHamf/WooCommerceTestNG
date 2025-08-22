package com.seleniumdemo.pages;

import com.seleniumdemo.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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
    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    private By table = By.className("shop_table");
    private By tableBody = By.tagName("tbody");
    private By productName = By.className("product-name");
    private By productQuantity = By.className("product-quantity");
    private By productTotal = By.className("product-total");
    private By placeOrderBtn = By.id("place_order");

    // Javascripts
    private static final String SCROLL_TO_ORDER_BUTTON_JS = "arguments[0].scrollIntoView(true);";

    public CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void setCompanyName(String companyName) {
        this.companyName.sendKeys(companyName);
    }

    public void selectCountryByIndex(int index) {
        Select select = new Select(selectCountry);
        select.selectByIndex(index);
    }

    public void selectCountryByName(String countryName) {
        Select select = new Select(selectCountry);
        select.selectByVisibleText(countryName);
    }

    public void setOrderComments(String comments) {
        orderComments.sendKeys(comments);
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress.sendKeys(streetAddress);
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber.sendKeys(apartmentNumber);
    }

    public void setPostCode(String postCode) {
        this.postCode.sendKeys(postCode);
    }

    public void setCity(String city) {
        this.city.sendKeys(city);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.sendKeys(phoneNumber);
    }

    public void setEmailAddress(String email) {
        emailAddress.sendKeys(email);
    }

    public WebElement getTable() {
        return driver.findElement(table);
    }

    public WebElement getTableBody() {
        return getTable().findElement(tableBody);
    }

    public List<WebElement> getProductsList() {
        return productsList;
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<String>();
        SeleniumHelper.waitForElementToExist(driver, tableBody);
        for ( WebElement element: productNames ) {
            String name = element.getText();
            names.add(name.substring(0, name.indexOf("×")).strip());
        }
        return names;
    }

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

    public Double getOrderSubtotal() {
        String subtotal = orderSubtotal.findElement(By.className("amount")).getText();
        return Double.parseDouble(subtotal.substring(0, subtotal
                .indexOf(" "))
                .replace(",", "."));
    }

    public Double getOrderTotal() {
        String total = orderTotal.findElement(By.className("amount")).getText();
        return Double.parseDouble(total.substring(0, total
                .indexOf(" "))
                .replace(",", "."));
    }

    public OrderSummaryPage clickPlaceOrder() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(SCROLL_TO_ORDER_BUTTON, driver.findElement(placeOrderBtn));
        SeleniumHelper.waitForElementToBeClickable(driver, driver.findElement(placeOrderBtn));
        driver.findElement(placeOrderBtn).click();
        return new OrderSummaryPage(driver);
    }
}
