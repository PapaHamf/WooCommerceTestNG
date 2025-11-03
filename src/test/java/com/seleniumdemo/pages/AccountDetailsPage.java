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

    /**
     * Class that holds the locators of the Account Details page and methods to get its webelements.
     * @param driver
     */
    public AccountDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Sets the first name field on the page.
     * @param firstName Text containing the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    /**
     * Sets the last name field on the page.
     * @param lastName Text containing the last name.
     */
    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    /**
     * Sets the display name field on the page.
     * @param displayName Text containing the display name of the user.
     */
    public void setDisplayName(String displayName) {
        this.displayName.sendKeys(displayName);
    }

    /**
     * Sets the email address field on the page.
     * @param emailAddress Text containing the email address of the user.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress.sendKeys(emailAddress);
    }

    /**
     * Sets the current password field on the page.
     * @param currentPassword Text containing the current password of the user.
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword.sendKeys(currentPassword);
    }

    /**
     * Sets the new password field on the page.
     * @param newPassword Text containing the new password of the user.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword.sendKeys(newPassword);
    }

    /**
     * Sets the confirm password field on the page.
     * @param confirmPassword Text containing the confirm password of the user.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.sendKeys(confirmPassword);
    }

    /**
     * Clicks the Save Changes button.
     * @return The AccountDetails object.
     */
    public AccountDetailsPage clickSaveChanges() {
        saveChangesButton.click();
        return this;
    }

}
