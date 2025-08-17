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


    public PostPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setComment(String comment) {
        this.comment.sendKeys(comment);
    }

    public void setAuthor(String author) {
        this.author.sendKeys(author);
    }

    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    public void setURL(String url) {
        this.urlAddress.sendKeys(url);
    }

    public PostPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public PostPage clickCancelComment() {
        cancelCommentLink.click();
        return this;
    }

    public List<WebElement> getComments() {
        return comments;
    }

    public List<String> getCommentsText() {
        List<String> commentsText = new ArrayList<String>();
        for ( WebElement element: commentContent ) {
            commentsText.add(element.getText());
        }
        return commentsText;
    }

    public List<String> getCommentsAuthor() {
        List<String> commentsAuthor = new ArrayList<String>();
        for ( WebElement element: this.commentsAuthor ) {
            commentsAuthor.add(element.getText());
        }
        return commentsAuthor;
    }

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
