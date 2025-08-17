package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderSummaryPage {

    protected WebDriver driver;

    @FindBy(className = "woocommerce-notice")
    private WebElement orderNotice;
    @FindBy(className = "order")
    private WebElement orderNumber;
    @FindBy(className = "date")
    private WebElement orderDate;
    @FindBy(className = "email")
    private WebElement orderEmail;
    @FindBy(className = "total")
    private WebElement orderTotal;
    @FindBy(className = "order_item")
    private List<WebElement> orderItems;
    By itemName = By.className(" product-name");
    @FindBy(className = "woocommerce-customer-details")
    private WebElement customerDetails;
    By customerAddress = By.tagName("address");
    By customerPhone = By.className("woocommerce-customer-details--phone");
    By customerEmail = By.className("woocommerce-customer-details--email");
    @FindBy(tagName = "tfoot")
    private WebElement tableFoot;
    By rowHeading = By.tagName("th");

    public OrderSummaryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getOrderNotice() {
        return orderNotice.getText();
    }

    public String getOrderNumber() {
        String number = orderNumber.getText();
        return number.substring(number.indexOf(":")).strip();
    }

    public String getOrderDate() {
        String date = orderDate.getText();
        return date.substring(date.indexOf(":")).strip();
    }

    public String getOrderEmail() {
        String email = orderEmail.getText();
        return email.substring(email.indexOf(":")).strip();
    }

    public String getOrderTotal() {
        String total = orderTotal.getText();
        return total.substring(total.indexOf(":")).strip();
    }

    public List<WebElement> getOrderItems() {
        return orderItems;
    }

    public List<String> getOrderItemsName() {
        List<String> names = new ArrayList<String>();
        for ( WebElement element: getOrderItems() ) {
            String name = element.findElement(itemName).getText();
            names.add(name.substring(0, name.indexOf("×")).strip());
        }
        return names;
    }

    public String[] getCustomerAddress() {
        String regex = "\\n";
        String[] addressLines = customerDetails
                .findElement(customerAddress)
                .getText()
                .split(regex);
        return Arrays.copyOfRange(addressLines, 0, 2);
    }

    public String getCustomerPhone() {
        return customerDetails.findElement(customerPhone).getText();
    }

    public String getCustomerEmail() {
        return customerDetails.findElement(customerEmail).getText();
    }


}
