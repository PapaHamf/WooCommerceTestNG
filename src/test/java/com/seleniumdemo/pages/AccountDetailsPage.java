package com.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDetailsPage {

    protected WebDriver driver;

    @FindBy(id = "account_first_name")
    private WebElement firstName;
    @FindBy(id = "account_last_name")
    private WebElement lastName;
    @FindBy(id = "account_display_name")
    private WebElement displayName;
    @FindBy(id = "account_email")
    private WebElement emailAddress;
    @FindBy(id = "password_current")
    private WebElement currentPassword;
    @FindBy(id = "password_1")
    private WebElement newPassword;
    @FindBy(id = "password_2")
    private WebElement confirmPassword;
    @FindBy(name = "save_account_details")
    private WebElement saveChangesButton;

    public AccountDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void setDisplayName(String displayName) {
        this.displayName.sendKeys(displayName);
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.sendKeys(emailAddress);
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword.sendKeys(currentPassword);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword.sendKeys(newPassword);
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.sendKeys(confirmPassword);
    }

    public AccountDetailsPage clickSaveChanges() {
        saveChangesButton.click();
        return this;
    }

}
