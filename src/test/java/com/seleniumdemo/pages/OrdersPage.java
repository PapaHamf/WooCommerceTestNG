package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrdersPage {

    protected WebDriver driver;

    @FindBys({@FindBy(tagName = "tbody"), @FindBy(tagName = "tr")})
    private List<WebElement> tableRows;
    By orderLink = By.tagName("//td[@data-title='Order']/a");
    By orderDate = By.xpath("//td[@data-title='Date']");
    By orderStatus = By.xpath("//td[@data-title='Status']");
    By orderTotal = By.xpath("//td[@data-title='Total']");
    By orderViewButton = By.tagName("//td[@data-title='Actions']/a");

    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<WebElement> getTableRows() {
        return tableRows;
    }

    public WebElement getOrderByIndex(int index) {
        return getTableRows().get(index);
    }

    public WebElement getOrderByNumber(String number) {
        for ( WebElement element: getTableRows() ) {
            if ( element.findElement(orderLink).getText().substring(1).equals(number) ) {
                return element;
            }
        }
        return null;
    }

    public List<WebElement> getOrderByDate(String dateString) {
        List<WebElement> ordersList = new ArrayList<WebElement>();
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        for ( WebElement element: getTableRows() ) {
            if ( element.findElement(orderDate).getText().equals(date.toString()) ) {
                ordersList.add(element);
            }
        }
        return ordersList;
    }

    public String getOrderStatusByNumber(String number) {
        return getOrderByNumber(number).findElement(orderStatus).getText();
    }

    public String getOrderTotalByNumber(String number) {
        return getOrderByNumber(number).findElement(orderTotal).getText();
    }

    public OrderDetails clickViewOrderButtonByNumber(String number) {
        getOrderByNumber(number).findElement(orderViewButton).click();
        return new OrderDetails(driver);
    }

}
