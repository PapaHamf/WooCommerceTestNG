package com.seleniumdemo.pages;

import org.openqa.selenium.By;
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

    @FindBy(className = "product")
    private List<WebElement> productsList;
    @FindBys({@FindBy(className = "products"), @FindBy(className = "price")})
    private List<WebElement> productPrices;
    @FindBys({@FindBy(className = "products"), @FindBy(className = "add_to_cart_button")})
    private List<WebElement> productButtons;
    @FindBys({@FindBy(className = "products"), @FindBy(tagName = "h2")})
    private List<WebElement> productNames;
    @FindBy(name = "orderby")
    private WebElement sortOrderList;

    private By addedToCart = By.cssSelector("a.button.added");
    private By viewCart = By.linkText("View cart");

    /**
     * Class that holds the locators of the Shop page and methods to get its webelements.
     * @param driver
     */
    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Returns the list of products on the Shop page.
     * @return List of product webelements.
     */
    public List<WebElement> getProductsList() {
        return productsList;
    }

    /**
     * Returns the list of products names on the Shop page.
     * @return List of product names.
     */
    public List<String> getProductNames() {
        return productNames
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of products prices on the Shop page.
     * @return List of product prices.
     */
    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<Double>();
        for ( WebElement element: productPrices ) {
            String price = element.getText();
            prices.add(Double.parseDouble(price.substring(0, price
                    .indexOf(" "))
                    .replace(",", ".")));
        }
        return prices;
    }

    /**
     * Clicks the product Add to cart button on the Shop page for the product selected by index.
     * @param productNumber Number of the product from the list, starts w/ 0.
     * @return Shop page object.
     */
    public ShopPage clickProductButtonByNumber(int productNumber) {
        productButtons.get(productNumber).click();
        return this;
    }

    /**
     * Clicks the product Add to cart button on the Shop page for the product selected by name.
     * @param productName Name of the product from the list.
     * @return Shop page object.
     */
    public ShopPage clickProductButtonByName(String productName) {
        for ( int i = 0; i < getProductNames().size(); i++ ) {
            if ( getProductNames().get(i).equals(productName) ) {
                productButtons.get(i).click();
            }
        }
        return this;
    }

    /**
     * Clicks the product tile on the Shop page for the product selected by index.
     * @param productNumber Number of the product from the list, starts w/ 0.
     * @return Product page object.
     */
    public ProductPage clickProductTileByNumber(int productNumber) {
        productsList.get(productNumber).click();
        return new ProductPage(driver);
    }

    /**
     * Clicks the product tile on the Shop page for the product selected by name.
     * @param productName Name of the product from the list.
     * @return Product page object.
     */
    public ProductPage clickProductTileByName(String productName) {
        for ( int i = 0; i < getProductNames().size(); i++ ) {
            if ( getProductNames().get(i).equals(productName) ) {
                productsList.get(i).click();
            }
        }
        return new ProductPage(driver);
    }

    /**
     * Checks if the product was added to cart using the checkmark on the Add to cart button.
     * @param productNumber Number of the product from the list, starts w/ 0.
     * @return true when the product is added to cart; otherwise false.
     */
    public boolean productIsAddedByNumber(int productNumber) {
        return getProductsList()
                .get(productNumber)
                .findElement(addedToCart)
                .isDisplayed();
    }

    /**
     * Checks if the product was added to cart using the checkmark on the Add to cart button.
     * @param productName Name of the product from the list.
     * @return true when the product is added to cart; otherwise false.
     */
    public boolean productIsAddedByName(int productName) {
        for ( int i = 0; i < getProductNames().size(); i++ ) {
            if ( getProductNames().get(i).equals(productName) ) {
                return getProductsList().get(i).findElement(addedToCart).isDisplayed();
            }
        }
        return false;
    }

    /**
     * Clicks the View cart link in the product tile that was most recently added to the cart.
     * @param element Webelement of the product added to cart.
     * @return Cart Page object.
     */
    public CartPage clickViewCartLink(WebElement element) {
        element.findElement(viewCart).click();
        return new CartPage(driver);
    }

    /**
     * Selects the product sort order by index of the list.
     * @param index Number of sort option, starts w/ 0.
     */
    public void selectProductOrderByIndex(int index) {
        Select select = new Select(sortOrderList);
        select.selectByIndex(index);
    }

    /**
     * Selects the product sort order by name.
     * @param name Name of sort option.
     */
    public void selectProductOrderByValue(String name) {
        Select select = new Select(sortOrderList);
        select.selectByValue(name);
    }

}
