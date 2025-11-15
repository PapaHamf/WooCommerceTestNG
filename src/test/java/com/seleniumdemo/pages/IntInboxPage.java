package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IntInboxPage {

    protected WebDriver driver;

    @FindAll({@FindBy(className = "msglist-item__message")})
    private List<WebElement> messagesList;

    private By sender = By.className("msglist-item__sender");
    private By subject = By.className("msglist-item__subject");
    private By resetLink = By.linkText("Reset password");
    private By messageIframe = By.className("message__iframe");

    /**
     * Class that holds the locators of the Interia Inbox page and methods to get its webelements.
     * @param driver
     */
    public IntInboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Returns the list of messages item webelements (contains items like sender, subject).
     * @return List of webelements
     */
    public List<WebElement> getMessagesList() {
        return messagesList;
    }

    /**
     * Returns the first element of the list of messages.
     * @return Webelement of newest message
     */
    public WebElement getFirstMessage() {
        return getMessagesList().getFirst();
    }

    /**
     * Returns the webelement of message from the list by the name of the sender.
     * @param sender Text containing the name of the sender.
     */
    public WebElement findMessageBySender(String sender) {
        for ( WebElement element: getMessagesList() ) {
            if ( element.findElement(this.sender).getText().contains(sender) ) {
                return element;
            }
        }
        return null;
    }

    /**
     * Returns the number of messages received from the sender.
     * @param sender Text containing the name of the sender.
     */
    public int getNumberofMessagesBySender(String sender) {
        int noOfMessages = 0;
        for ( WebElement element: getMessagesList() ) {
            if ( element.findElement(this.sender).getText().contains(sender) ) {
                noOfMessages = noOfMessages + 1;
            }
        }
        return noOfMessages;
    }

    /**
     * Selects the email message using the index of the messages list on the page.
     * @param index Index number of messages list.
     */
    public void clickMessageByIndex(int index) {
        getMessagesList().get(index).click();
    }

    /**
     * Selects the email message using the sender name and the content of email subject.
     * @param sender Text containing the name of the sender.
     * @param subject Text containing the email subject.
     */
    public void clickMessageBySender(String sender, String subject) {
        for ( WebElement element: getMessagesList() ) {
            if ( element.findElement(this.sender).getText().contains(sender)
                    && element.findElement(this.subject).getText().contains(subject) ) {
                    element.click();
            }
        }
    }

    /**
     * Clicks the Reset password link in the message body.
     * @return My Account page object
     */
    public UserDashboardPage clickResetLink() {
        WebElement iframe = driver.findElement(messageIframe);
        driver.switchTo().frame(iframe);
        driver.findElement(resetLink).click();
        return new UserDashboardPage(driver);
    }
}
