package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    protected WebDriver driver;

    @FindBy(name = "add-to-cart")
    private WebElement addToCartButton;
    @FindBy(className = "entry-summary")
    private WebElement productEntry;
    @FindBys({@FindBy(className = "entry-summary"), @FindBy(className = "amount")})
    private WebElement productPrice;
    @FindBy(id = "tab-title-description")
    private WebElement descriptionLink;
    @FindBy(id = "tab-title-reviews")
    private WebElement reviewsLink;
    @FindBy(id = "tab-description")
    private WebElement productDescriptionContainer;
    @FindBys({@FindBy(id = "tab-description"), @FindBy(tagName = "p")})
    private WebElement productDescription;

    @FindBys({@FindBy(xpath = "//div[@role='alert']"), @FindBy(linkText = "View cart")})
    private WebElement viewCartAlert;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProductPage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public CartPage clickViewCartAlert() {
        viewCartAlert.click();
        return new CartPage(driver);
    }

    public ProductPage switchToDescription() {
        descriptionLink.click();
        return this;
    }

    public ProductPage switchToReviews() {
        reviewsLink.click();
        return this;
    }

    public String getProductDescription() {
        return productDescription.getText();
    }
}
