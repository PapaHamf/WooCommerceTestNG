package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopPage {

    protected WebDriver driver;

    @FindBy(className = "products")
    private List<WebElement> productsList;
    @FindBys({@FindBy(className = "products"), @FindBy(className = "price")})
    private List<WebElement> productPrices;
    @FindBys({@FindBy(className = "products"), @FindBy(className = "add_to_cart_button")})
    private List<WebElement> productButtons;
    @FindBys({@FindBy(className = "products"), @FindBy(tagName = "h2")})
    private List<WebElement> productNames;
    @FindBy(name = "orderby")
    private WebElement sortOrderList;

    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<WebElement> getProductsList() {
        return productsList;
    }

    public List<String> getProductNames() {
        return productNames
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<Double>();
        for ( WebElement element: productPrices ) {
            String price = element.getText();
            prices.add(Double.parseDouble(price.substring(0, price.indexOf(" "))));
        }
        return prices;
    }

    public ShopPage clickProductButtonNumber(int productNumber) {
        productButtons.get(productNumber).click();
        return this;
    }

    public ShopPage clickProductButtonName(String productName) {
        for ( int i = 0; i < getProductNames().size(); i++ ) {
            if ( getProductNames().equals(productName) ) {
                productButtons.get(i).click();
            }
        }
        return this;
    }

    public void selectProductOrderByIndex(int index) {
        Select select = new Select(sortOrderList);
        select.selectByIndex(index);
    }

    public void selectProductOrderByValue(String name) {
        Select select = new Select(sortOrderList);
        select.selectByValue(name);
    }

}
