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

    /**
     * Class that holds the locators of the Orders page and methods to get its webelements.
     * @param driver
     */
    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Returns the list of the table rows containing the orders.
     * @return List of table webelements.
     */
    public List<WebElement> getTableRows() {
        return tableRows;
    }

    /**
     * Returns the order from the orders list selected by the index.
     * @param index Number of the order on the list, starts w/0.
     * @return Table row webelement.
     */
    public WebElement getOrderByIndex(int index) {
        return getTableRows().get(index);
    }

    /**
     * Returns the order from the orders list selected by the number of the order.
     * @param number Number of the order.
     * @return Table row webelement.
     */
    public WebElement getOrderByNumber(String number) {
        for ( WebElement element: getTableRows() ) {
            if ( element.findElement(orderLink).getText().substring(1).equals(number) ) {
                return element;
            }
        }
        return null;
    }

    /**
     * Returns the order from the orders list selected by the date of the order.
     * @param dateString Date the order was placed.
     * @return Table row webelement.
     */
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

    /**
     * Returns the order status for the order selected by the number.
     * @param number Number of the order.
     * @return Status of the order.
     */
    public String getOrderStatusByNumber(String number) {
        return getOrderByNumber(number).findElement(orderStatus).getText();
    }

    /**
     * Returns the total amount of order for the order selected by the number.
     * @param number Number of the order.
     * @return Total amount of the order.
     */
    public String getOrderTotalByNumber(String number) {
        return getOrderByNumber(number).findElement(orderTotal).getText();
    }

    /**
     * Clicks the View button for the order selected by the number.
     * @param number Number of the order.
     * @return Order details page.
     */
    public OrderDetails clickViewOrderButtonByNumber(String number) {
        getOrderByNumber(number).findElement(orderViewButton).click();
        return new OrderDetails(driver);
    }

}
