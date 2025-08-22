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

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void enterRegisterEmail(String email) {
        registerEmail.sendKeys(email);
    }

    public void enterRegisterPassword(String password) {
        registerPassword.sendKeys(password);
    }

    public UserDashboardPage clickRegisterButton() {
        registerButton.click();
        return new UserDashboardPage(driver);
    }

    public void clickRegisterButtonInvalid() {
        registerButton.click();
    }

    public void enterLoginEmail(String email) {
        loginEmail.sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        loginPassword.sendKeys(password);
    }

    public UserDashboardPage clickLoginButton() {
        loginButton.click();
        return new UserDashboardPage(driver);
    }

    public void  clickLoginButtonInvalid() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorContainer.getText();
    }
}
