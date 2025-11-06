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

    /**
     * Class that holds the locators of the Product page and methods to get its webelements.
     * @param driver
     */
    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Clicks the Add to Cart button on the product page.
     * @return Product page object.
     */
    public ProductPage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    /**
     * Returns the product price from the product page.
     * @return Product price.
     */
    public String getProductPrice() {
        return productPrice.getText();
    }

    /**
     * Clicks the View cart button on the product page.
     * @return Cart page object.
     */
    public CartPage clickViewCartAlert() {
        viewCartAlert.click();
        return new CartPage(driver);
    }

    /**
     * Clicks the Description link on the product page.
     * @return Product page object.
     */
    public ProductPage switchToDescription() {
        descriptionLink.click();
        return this;
    }

    /**
     * Clicks the Reviews link on the product page.
     * @return Product page object.
     */
    public ProductPage switchToReviews() {
        reviewsLink.click();
        return this;
    }

    /**
     * Returns the product description on the product page.
     * @return Product description text.
     */
    public String getProductDescription() {
        return productDescription.getText();
    }
}
