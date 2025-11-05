package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    @FindBy(className = "mini_cart_item")
    private List<WebElement> widgetItems;
    By itemName = By.xpath("//a[2]");

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
    public CartPage clickViewCartButton() {
        displayCartWidget();
        viewCartButton.click();
        return new CartPage(driver);
    }

    /**
     * Clicks the remove icon inside the widget for the product selected by index.
     * @param index Number of the product on the list, starts w/ 0.
     */
    public void removeProductByNumber(int index) {
        displayCartWidget();
        removeIcons.get(index).click();
    }

    /**
     * Clicks the remove icon inside the widget for the product selected by product name.
     * @param name Name of the product on the list.
     */
    public void removeProductByName(String name) {
        displayCartWidget();
        for ( int i = 0; i < widgetItems.size(); i++ ) {
            if ( widgetItems.get(i).findElement(itemName).getText().contains(name) ) {
                removeIcons.get(i).click();
                break;
            }
        }
    }

    /**
     * Clicks the Checkout button inside the widget.
     * @return Checkout page object.
     */
    public CheckOutPage clickCheckoutButton() {
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
