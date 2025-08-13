package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    protected WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'My account')]")
    //@FindBy(css = "li[id='menu-item-22'] a[class='nav__link']")
    private WebElement myAccountLink;
    @FindBy(xpath = "//span[contains(text(), 'Cart')]")
    private WebElement cartLink;
    @FindBy(xpath = "//span[contains(text(), 'Shop')]")

    private WebElement shopLink;

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
}
