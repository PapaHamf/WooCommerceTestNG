package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

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

    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<WebElement> getProductsList() {
        return productsList;
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<String>();
        for ( WebElement element: productNames ) {
            names.add(element.getText());
        }
        return names;
    }

    public List<Integer> getProductPrices() {
        List<Integer> prices = new ArrayList<Integer>();
        for ( WebElement element: productPrices ) {
            String price = element.getText();
            prices.add(Integer.parseInt(price.substring(0, price.indexOf(" "))));
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

}
