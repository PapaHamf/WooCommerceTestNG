package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
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
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;
    // Bottom elements
    @FindBy(id = "coupon_code")
    private WebElement couponCode;
    @FindBy(name = "apply_coupon")
    private WebElement applyCouponButton;
    @FindBy(name = "update_cart")
    private WebElement updateCartButton;
    @FindBy(linkText = "Proceed to checkout")
    private WebElement checkoutButton;
    @FindBys({@FindBy(className = "cart_totals"), @FindBy(xpath = "//td[@data-title='Subtotal']")})
    private WebElement orderSubtotal;
    @FindBys({@FindBy(className = "cart_totals"), @FindBy(xpath = "//td[@data-title='Total']")})
    private WebElement orderTotal;

    // Quantity field
    private By priceInput = By.xpath("//input[@type='number']");
    private By quntityInput = By.tagName("input");
    // Individual row elements
    private By removeButton = By.className("remove");
    private By productName = By.xpath("//td[@data-title='Product']");
    private By productPrice = By.xpath("//td[@data-title='Price']");
    private By productQuantity = By.xpath("//td[@data-title='Quantity']");
    private By productTotal = By.xpath("//td[@data-title='Total']");

    // Messages
    public static final String PRODUCT_REMOVED = "removed.";

    /**
     * Class that holds the locators of the Cart page and methods to get its webelements.
     * @param driver
     */
    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Returns the number of different products in cart.
     * @return Number of products.
     */
    public int getNumberOfProducts() {
        return cartItems.size();
    }

    /**
     * Returns the list of Strings with the product names in cart.
     * @return List of Strings w/ product names.
     */
    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<String>();
        for ( WebElement element: this.productNames ) {
            productNames.add(element.getText());
        }
        return productNames;
    }

    /**
     * Returns the total number of product items in cart.
     * @return Number of items.
     */
    public int getTotalNumberOfProductItems() {
        int numberOfItems = 0;
        for ( WebElement element: productQuantities ) {
            numberOfItems += Integer.parseInt(element.findElement(quntityInput).getDomAttribute("value"));
        }
        return numberOfItems;
    }

    /**
     * Clicks the Remove button of the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     */
    public void removeProductByNumber(int index) {
        removeButtons.get(index).click();
    }

    /**
     * Clicks the Remove button of the product selected by product name.
     * @param name Name of the product on the list.
     */
    public void removeProductByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                removeButtons.get(i).click();
                break;
            }
        }
    }

    /**
     * Clicks the product link on the cart page, redirecting user to the Product page.
     * @param index Number of the product on the list, starts w/ 0.
     * @return Product page object.
     */
    public ProductPage selectProductByNumber(int index) {
        productNameLinks.get(index).click();
        return new ProductPage(driver);
    }

    /**
     * Clicks the product link on the cart page, redirecting user to the Product page.
     * @param name Name of the product on the list.
     * @return Product page object.
     */
    public ProductPage selectProductByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                productNameLinks.get(i).click();
            }
        }
        return new ProductPage(driver);
    }

    /**
     * Returns the price of the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     * @return Product price.
     */
    public String getProductPriceByNumber(int index) {
        return productPrices.get(index).getText();
    }

    /**
     * Returns the price of the product selected by product name.
     * @param name Name of the product on the list.
     * @return Product price.
     */
    public String getProductPriceByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                return productPrices.get(i).getText();
            }
        }
        return null;
    }

    /**
     * Returns the total price of the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     * @return Product total.
     */
    public String getProductTotalByNumber(int index) {
        return productTotals.get(index).getText();
    }

    /**
     * Returns the total price of the product selected by product name.
     * @param name Name of the product on the list.
     * @return Product total.
     */
    public String getProductTotalByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                return productTotals.get(i).getText();
            }
        }
        return null;
    }

    /**
     * Sets the quantity of the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     * @param quantity Quantity to set.
     */
    public void setProductQuantityByNumber(int index, String quantity) {
        productQuantities.get(index).findElement(priceInput).sendKeys(quantity);
    }

    /**
     * Sets the quantity of the product selected by product name.
     * @param name Name of the product on the list.
     * @param quantity Quantity to set.
     */
    public void setProductQuantityByName(String name, String quantity) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                productQuantities.get(i).findElement(priceInput).sendKeys(quantity);
                break;
            }
        }
    }

    /**
     * Returns the quantity of the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     * @return Product quantity.
     */
    public String getProductQuantityByNumber(int index) {
        return productQuantities.get(index).findElement(priceInput).getText();
    }

    /**
     * Returns the quantity of the product selected by product name.
     * @param name Name of the product on the list.
     * @return Product quantity.
     */
    public String getProductQuantityByName(String name) {
        for ( int i = 0; i < productNames.size(); i++ ) {
            if ( productNames.get(i).getText().equals(name) ) {
                return productNames.get(i).getText();
            }
        }
        return null;
    }

    /**
     * Sets the coupon code field on the page.
     * @param coupon Text containing the coupon.
     */
    public void setCouponCode(String coupon) {
        couponCode.sendKeys(coupon);
    }

    /**
     * Clicks the Apply Coupon button on the page.
     * @return Cart page object.
     */
    public CartPage clickApplyCouponCode() {
        applyCouponButton.click();
        return this;
    }

    /**
     * Clicks the Update cart button on the page.
     * @return Cart page object.
     */
    public CartPage clickUpdateCart() {
        updateCartButton.click();
        return this;
    }

    /**
     * Returns the order Subtotal field value.
     * @return Order subtotal value.
     */
    public String getOrderSubtotal() {
        return orderSubtotal.getText();
    }

    /**
     * Returns the order Total field value.
     * @return Order total value.
     */
    public String getOrderTotal() {
        return orderTotal.getText();
    }

    /**
     * Clicks the Proceed to checkout button on the page.
     * @return Checkout Page object.
     */
    public CheckOutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckOutPage(driver);
    }

    /**
     * Returns the text message displayed in the alert block.
     * @return String from the alert.
     */
    public String getAlertMessage() {
        return alertMessage.getText();
    }
}
