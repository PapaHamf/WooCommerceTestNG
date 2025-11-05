package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    protected WebDriver driver;

    @FindBy(id = "reg_email")
    private WebElement registerEmail;
    @FindBy(id = "reg_password")
    private WebElement registerPassword;
    @FindBy(css = "button[name='register']")
    private WebElement registerButton;
    @FindBy(id = "username")
    private WebElement loginEmail;
    @FindBy(id = "password")
    private WebElement loginPassword;
    @FindBy(css = "button[name='login']")
    private WebElement loginButton;
    @FindBy(className = "woocommerce-error")
    private WebElement errorContainer;

    // Error messages
    public static final String ACCOUNT_EXISTS = "Error: An account is already registered with your email address. Please log in.";
    public static final String INVALID_PASS_USER = "ERROR: Incorrect username or password.";
    public static final String EMAIL_ADDR_WITHOUT_SIGN = "Uwzględnij znak „@” w adresie e-mail. W adresie „dupa” brakuje znaku „@”."
    public static final String EMAIL_ADDR_WITH_SIGN_ONLY = "Podaj część przed znakiem „@”. Adres „@” jest niepełny.";

    // Javascripts
    private static final String VALIDATION_MESSAGE_JS = "document.getElementById(\"reg_email\").validationMessage";

    /**
     * Class that holds the locators of the MyAccount page and methods to get its webelements.
     * @param driver
     */
    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Sets the register user email field on the page.
     * @param email Text containing the email address used to register user.
     */
    public void enterRegisterEmail(String email) {
        registerEmail.sendKeys(email);
    }

    /**
     * Sets the register user password field on the page.
     * @param password Text containing the password used to register user.
     */
    public void enterRegisterPassword(String password) {
        registerPassword.sendKeys(password);
    }

    /**
     * Clicks the Register button on the page.
     * @return UserDashboard page object.
     */
    public UserDashboardPage clickRegisterButton() {
        registerButton.click();
        return new UserDashboardPage(driver);
    }

    /**
     * Clicks the Register button on the page and does not return the UserDashboard page object.
     */
    public void clickRegisterButtonInvalid() {
        registerButton.click();
    }

    /**
     * Sets the login user email field on the page.
     * @param email Text containing the email address used to login user.
     */
    public void enterLoginEmail(String email) {
        loginEmail.sendKeys(email);
    }

    /**
     * Sets the login user password field on the page.
     * @param password Text containing the password used to login user.
     */
    public void enterLoginPassword(String password) {
        loginPassword.sendKeys(password);
    }

    /**
     * Clicks the Login button on the page.
     * @return UserDashboard page object.
     */
    public UserDashboardPage clickLoginButton() {
        loginButton.click();
        return new UserDashboardPage(driver);
    }

    /**
     * Clicks the Login button on the page and does not return the UserDashboard page object.
     */
    public void  clickLoginButtonInvalid() {
        loginButton.click();
    }

    /**
     * Returns the validation errors of the login & register forms.
     * @return Error message text.
     */
    public String getErrorMessage() {
        return errorContainer.getText();
    }
}
