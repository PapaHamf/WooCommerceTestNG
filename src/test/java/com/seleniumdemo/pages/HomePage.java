package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    protected WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'My account')]")
    //@FindBy(css = "li[id='menu-item-22'] a[class='nav__link']")
    private WebElement myAccountLink;
    @FindBy(xpath = "//span[contains(text(), 'Cart')]")
    private WebElement cartLink;
    @FindBy(xpath = "//span[contains(text(), 'Shop')]")
    private WebElement shopLink;
    @FindBy(className = "nav__search")
    private List<WebElement> searchBarButton;
    // Cart Widget Elements
    @FindBy(className = "icn-shoppingcart")
    private List<WebElement> cartIcon;
    @FindBy(className = "total")
    private WebElement productsTotal;
    @FindBy(linkText = "View cart")
    private WebElement viewCartButton;
    @FindBy(linkText = "Checkout")
    private WebElement checkOutButton;
    // dodać remove button
    // remove_from_cart_button class

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MyAccountPage clickMyAccount() {
        myAccountLink.click();
        return new MyAccountPage(driver);
    }

    public CartPage clickCart() {
        cartLink.click();
        return new CartPage(driver);
    }

    public ShopPage clickShop() {
        shopLink.click();
        return new ShopPage(driver);
    }

    public HomePage clickSearchButton() {
        searchBarButton
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    public HomePage clickCartIcon() {
        cartIcon
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    public WebElement getInteractiveCartIcon() {
        return cartIcon
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .get();
    }

    public HomePage displayCartWidget() {
        Actions action = new Actions(driver);
        action.moveToElement(getInteractiveCartIcon()).perform();
        return this;
    }

    public String getCartWidgetTotal() {
        return productsTotal.getText();
    }

    public CartPage clickViewCartButton() {
        viewCartButton.click();
        return new CartPage(driver);
    }

    public CheckOutPage clickCheckoutButton() {
        checkOutButton.click();
        return new CheckOutPage(driver);
    }

}
