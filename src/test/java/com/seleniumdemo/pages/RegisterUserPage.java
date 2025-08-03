package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterUserPage {

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

    public RegisterUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void enterRegisterEmail(String email) {
        registerEmail.sendKeys(email);
    }

    public void enterRegisterPassword(String password) {
        registerPassword.sendKeys(password);
    }

    public MyAccountPage clickRegisterButton() {
        registerButton.click();
        return new MyAccountPage(driver);
    }

    public void enterLoginEmail(String email) {
        loginEmail.sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        loginPassword.sendKeys(password);
    }

    public MyAccountPage clickLoginButton() {
        loginButton.click();
        return new MyAccountPage(driver);
    }

}
