package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage {

    protected WebDriver driver;
    final String[] ARTICLES = {"post-41", "post-39"};

    @FindBy(xpath = "//span[contains(text(), 'My account')]")
    //@FindBy(css = "li[id='menu-item-22'] a[class='nav__link']")
    private WebElement myAccountLink;
    @FindBy(xpath = "//span[contains(text(), 'Cart')]")
    private WebElement cartLink;
    @FindBy(xpath = "//span[contains(text(), 'Shop')]")
    private WebElement shopLink;
    @FindBy(className = "nav__search")
    private List<WebElement> searchBarButton;
    @FindBy(css = "input[title='Search …']")
    private List<WebElement> searchField;
    @FindBy(className = "grid__item")
    private List<WebElement> searchResults;
    // Cart Widget Elements
    @FindBy(className = "icn-shoppingcart")
    private List<WebElement> cartIcon;
    @FindBy(className = "total")
    private WebElement productsTotal;
    @FindBy(linkText = "View cart")
    private WebElement viewCartButton;
    @FindBy(linkText = "Checkout")
    private WebElement checkOutButton;
    @FindBy(className = "remove_from_cart_button")
    private List<WebElement> removeIcons;
    By itemName = By.xpath("//a[2]");
    By widgetItems = By.className("mini_cart_item");
    By productTitle = By.className("czr-title");

    /**
     * Class that holds the locators of the Home page and methods to get its webelements.
     * @param driver
     */
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Clicks the My Account link at the header of home page.
     * @return MyAccount page object.
     */
    public MyAccountPage clickMyAccount() {
        myAccountLink.click();
        return new MyAccountPage(driver);
    }

    /**
     * Clicks the Cart link at the header of home page.
     * @return Cart page object.
     */
    public CartPage clickCart() {
        cartLink.click();
        return new CartPage(driver);
    }

    /**
     * Clicks the Shop link at the header of home page.
     * @return Shop page object.
     */
    public ShopPage clickShop() {
        shopLink.click();
        return new ShopPage(driver);
    }

    /**
     * Clicks the Search button at the header of home page.
     * @return Home page object.
     */
    public HomePage clickSearchButton() {
        searchBarButton
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    /**
     * Sets the Search field displayed on the new modal screen on the Home page,
     * then sends the Enter key to perform the search.
     * @param serachTerm Text containing the serach term.
     * @return Home page object.
     */
    public HomePage setSearchTerm(String serachTerm) {
        new Actions(driver)
                .sendKeys(searchField
                    .stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .get(), serachTerm)
                .sendKeys(Keys.ENTER)
                .perform();
        return this;
    }

    /**
     * Returns the list of items' names on the Search results page.
     * @return List of Strings.
     */
    public List<String> getSearchResults() {
        List<String> searchResults = new ArrayList<String>();
        for ( WebElement element: this.searchResults) {
            searchResults.add(element.findElement(productTitle).getText());
        }
        return searchResults;
    }

    /**
     * Verifies if the product name is displayed on the Search results page.
     * @param productName Text containing the name of the product.
     * @return true if the product name was found; otherwise false.
     */
    public boolean searchProductFound(String productName) {
        for ( WebElement element: searchResults ) {
            if ( element.findElement(productTitle).getText().equals(productName) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clicks the selected product title on the Search results page.
     * @param productName Text containing the name of the product.
     * @return Product page object.
     */
    public ProductPage clickFoundProductTitle(String productName) {
        for ( WebElement element: searchResults ) {
            if ( element.findElement(productTitle).getText().equals(productName) ) {
                element.click();
            }
        }
        return new ProductPage(driver);
    }

    /**
     * Clicks the Cart icon at the header of home page.
     * @return Home page object.
     */
    public HomePage clickCartIcon() {
        cartIcon
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    /**
     * Returns the interactive cart icon used to open the cart widget.
     * @return Webelement of cart icon.
     */
    public WebElement getInteractiveCartIcon() {
        return cartIcon
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .get();
    }

    /**
     * Displays the cart widget on the home page.
     * @return Home page object.
     */
    public HomePage displayCartWidget() {
        Actions action = new Actions(driver);
        action.moveToElement(getInteractiveCartIcon()).perform();
        return this;
    }

    /**
     * Returns the cart total amount for all of the products.
     * @return Order total value.
     */
    public String getCartWidgetTotal() {
        displayCartWidget();
        return productsTotal.getText();
    }

    /**
     * Clicks the View cart button inside the widget.
     * @return Cart page object.
     */
    public CartPage clickWidgetViewCartButton() {
        displayCartWidget();
        viewCartButton.click();
        return new CartPage(driver);
    }

    /**
     * Clicks the remove icon inside the widget for the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     */
    public void removeWidgetProductByNumber(int index) {
        displayCartWidget();
        removeIcons.get(index).click();
    }

    /**
     * Clicks the remove icon inside the widget for the product selected by product name.
     * @param name Name of the product on the list.
     */
    public void removeWidgetProductByName(String name) {
        displayCartWidget();
        for ( int i = 0; i < driver.findElements(widgetItems).size(); i++ ) {
            if ( driver.findElements(widgetItems).get(i).findElement(itemName).getText().contains(name) ) {
                removeIcons.get(i).click();
                break;
            }
        }
    }

    /**
     * Returns the list of the names of the products from the cart widget.
     * @return List of product names.
     */
    public List<String> getProductItemsFromWidget() {
        List<String> productNames = new ArrayList<String>();
        for ( WebElement element: driver.findElements(widgetItems) ) {
            productNames.add(element.findElement(itemName).getText());
        }
        return productNames;
    }

    /**
     * Clicks the Checkout button inside the widget.
     * @return Checkout page object.
     */
    public CheckOutPage clickWidgetCheckoutButton() {
        checkOutButton.click();
        return new CheckOutPage(driver);
    }

    /**
     * Clicks the random post published on the home page.
     * @return Post page object.
     */
    public PostPage clickRandomArticle() {
        Random random = new Random();
        driver
                .findElement(By.id(ARTICLES[random.nextInt(ARTICLES.length)]))
                .click();
        return new PostPage(driver);
    }

}
