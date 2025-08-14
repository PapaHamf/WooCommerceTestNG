package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    protected WebDriver driver;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;
    @FindBys({@FindBy(className = "cart_item"), @FindBy(className = "remove")})
    private List<WebElement> removeButtons;
    @FindBys({@FindBy(className = "cart_item"), @FindBy(className = "product-name")})
    private List<WebElement> productNames;
    @FindBys({@FindBy(className = "cart_item"), @FindBy(className = "product-name"), @FindBy(tagName = "a")})
    private List<WebElement> productNameLinks;
    @FindBys({@FindBy(className = "cart_item"), @FindBy(className = "product-price")})
    private List<WebElement> productPrices;
    @FindBys({@FindBy(className = "cart_item"), @FindBy(className = "product-quantity")})
    private List<WebElement> productQuantities;
    @FindBys({@FindBy(className = "cart_item"), @FindBy(className = "product-subtotal")})
    private List<WebElement> productTotals;
    // Quantity field
    private By priceInput = By.xpath("//input[@type='number']");
    // Individual row elements
    private By removeButton = By.className("remove");
    private By productName = By.xpath("//td[@data-title='Product']");
    private By productPrice = By.xpath("//td[@data-title='Price']");
    private By productQuantity = By.xpath("//td[@data-title='Quantity']");
    private By productTotal = By.xpath("//td[@data-title='Total']");
    // Bottom elements
    @FindBy(id = "coupon_code")
    private WebElement couponCode;
    @FindBy(name = "apply_coupon")
    private WebElement applyCouponButton;
    @FindBy(name = "update_cart")
    private WebElement updateCartButton;
    @FindBy(linkText = "\tProceed to checkout")
    private WebElement checkoutButton;
    @FindBys({@FindBy(className = "cart_totals "), @FindBy(xpath = "//td[@data-title='Subtotal']")})
    private WebElement orderSubtotal;
    @FindBys({@FindBy(className = "cart_totals "), @FindBy(xpath = "//td[@data-title='Total']")})
    private WebElement orderTotal;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void removeProductByNumber(int index) {
        removeButtons.get(index).click();
    }

    public void removeProductByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                removeButtons.get(i).click();
                break;
            }
        }
    }

    public ProductPage selectProductByNumber(int index) {
        productNameLinks.get(index).click();
        return new ProductPage(driver);
    }

    public ProductPage selectProductByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                productNameLinks.get(i).click();
            }
        }
        return new ProductPage(driver);
    }

    public String getProductPriceByNumber(int index) {
        return productPrices.get(index).getText();
    }

    public String getProductPriceByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                return productPrices.get(i).getText();
            }
        }
        return null;
    }

    public String getProductTotalByNumber(int index) {
        return productTotals.get(index).getText();
    }

    public String getProductTotalByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                return productTotals.get(i).getText();
            }
        }
        return null;
    }

    public void setProductQuantityByNumber(int index, String quantity) {
        productQuantities.get(index).findElement(priceInput).sendKeys(quantity);
    }

    public void setProductQuantityByName(String name, String quantity) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                productQuantities.get(i).findElement(priceInput).sendKeys(quantity);
                break;
            }
        }
    }

    public void enterCouponCode(String coupon) {
        couponCode.sendKeys(coupon);
    }

    public CartPage applyCouponCode() {
        applyCouponButton.click();
        return this;
    }

    public CartPage updateCart() {
        updateCartButton.click();
        return this;
    }

    public String getOrderSubtotal() {
        return orderSubtotal.getText();
    }

    public String getOrderTotal() {
        return orderTotal.getText();
    }

    public CheckOutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckOutPage(driver);
    }
}
