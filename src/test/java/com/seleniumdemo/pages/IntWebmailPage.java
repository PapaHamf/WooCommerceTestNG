package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IntWebmailPage {

    protected WebDriver driver;

    @FindBy(id = "emailId")
    private WebElement emailAddress;
    @FindBy(id = "passwordId")
    private WebElement emailPassword;
    @FindBy(css = "button[type='submit']")
    private WebElement emailLoginButton;

    //variables
    public static final String TICKET_SERVER = "http://poczta.int.pl";
    public static final String TICKET_EMAIL = "mareczek_testowy@int.pl";
    public static final String TICKET_PASSWORD = "m4reczek1234!";

    /**
     * Class that holds the locators of the Interia webmail page and methods to get its webelements.
     * @param driver
     */
    public IntWebmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Sets the login user email field on the page.
     * @param email Text containing the email address used to log in user.
     */
    public void enterEmailAddress(String email) {
        emailAddress.sendKeys(email);
    }

    /**
     * Sets the login user password field on the page.
     * @param password Text containing the password used to log in user.
     */
    public void enterEmailPassword(String password) {
        emailPassword.sendKeys(password);
    }

    /**
     * Clicks the Login button on the page.
     * @return IntInbox page object
     */
    public IntInboxPage clickLoginButton() {
        emailLoginButton.click();
        return new IntInboxPage(driver);
    }

}
