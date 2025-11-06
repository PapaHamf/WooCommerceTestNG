package com.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostPage {

    protected WebDriver driver;

    @FindBy(id = "comment")
    private WebElement comment;
    @FindBy(id = "author")
    private WebElement author;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "url")
    private WebElement urlAddress;
    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(className = "nav-item")
    private WebElement commentsNumber;
    @FindBy(id = "comments")
    private WebElement commentsContainer;
    @FindBys({@FindBy(className = "comment-list"), @FindBy(tagName = "li")})
    private List<WebElement> comments;
    @FindBys({@FindBy(className = "comment-list"), @FindBy(className = "comment-author")})
    private List<WebElement> commentsAuthor;
    By replyButton = By.className("comment-reply-link");
    @FindBy(id = "cancel-comment-reply-link")
    private WebElement cancelCommentLink;
    @FindBys({@FindBy(className = "comment-content"), @FindBy(tagName = "p")})
    private List<WebElement> commentContent;
    By commReplies = By.className("children");
    By commAuthor = By.className("comment-author");
    By commText = By.tagName("p");

    /**
     * Class that holds the locators of the Post page and methods to get its webelements.
     * @param driver
     */
    public PostPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Sets the comment field on the post page.
     * @param comment Text containing the comment.
     */
    public void setComment(String comment) {
        this.comment.sendKeys(comment);
    }

    /**
     * Sets the author name of the comment.
     * @param author Text containing the author name.
     */
    public void setAuthor(String author) {
        this.author.sendKeys(author);
    }

    /**
     * Sets the email address of comment author.
     * @param email Text containing the email address.
     */
    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    /**
     * Sets the URL address of comment author.
     * @param url Text containing the URL address.
     */
    public void setURL(String url) {
        this.urlAddress.sendKeys(url);
    }

    /**
     * Clicks the Submit button on the post page.
     * @return Post page object.
     */
    public PostPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    /**
     * Clicks the Cancel button on the post page.
     * @return Post page object.
     */
    public PostPage clickCancelComment() {
        cancelCommentLink.click();
        return this;
    }

    /**
     * Returns the list of the comments on the post page.
     * @return List of comments webelements.
     */
    public List<WebElement> getComments() {
        return comments;
    }

    /**
     * Returns the list of the comment texts on the post page.
     * @return List of comments texts.
     */
    public List<String> getCommentsText() {
        List<String> commentsText = new ArrayList<String>();
        for ( WebElement element: commentContent ) {
            commentsText.add(element.getText());
        }
        return commentsText;
    }

    /**
     * Returns the list of the comment authors on the post page.
     * @return List of comments authors.
     */
    public List<String> getCommentsAuthor() {
        List<String> commentsAuthor = new ArrayList<String>();
        for ( WebElement element: this.commentsAuthor ) {
            commentsAuthor.add(element.getText());
        }
        return commentsAuthor;
    }

    /**
     * Returns the list of the comment replies on the post page.
     * @return List of comments replies.
     */
    public List<String> getCommentReplies(String author, String commentText) {
        List<String> commentReplies = new ArrayList<String>();
        for ( int i = 0; i < getComments().size(); i++ ) {
            WebElement comment = getComments().get(i);
            if ( comment.findElement(commAuthor).getText().contains(author)
                    && comment.findElement(commText).getText().contains(commentText) ) {
                commentReplies = comment
                        .findElements(commReplies)
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
            }
        }
        return commentReplies;
    }

}
