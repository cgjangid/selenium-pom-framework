package com.actoJava.qa.pages;

import com.actoJava.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegisterPage extends BaseTest {

    // object repository for register page
    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div/div[1]/h1")
    private WebElement registerPageGreet;

    @FindBy(id = "exampleFirstName")
    private WebElement firstNameRegister;

    @FindBy(id = "exampleLastName")
    private WebElement lastNameRegister;

    @FindBy(xpath = "//*[@id=\"exampleInputEmail\"]")
    private WebElement emailAddressRegister;

    @FindBy(id = "exampleInputPassword")
    private WebElement passwordRegister;

    @FindBy(id = "exampleRepeatPassword")
    private WebElement repeatPasswordRegister;

    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div/form/button")
    private WebElement registerButtonRegister;

    // constructor with PageFactory to initiate all the page objects
    public UserRegisterPage() {
        PageFactory.initElements(driver, this);
    }

    // actions or functions on the Home Page
    // recommended update for isDisplayed code : wrap it with try catch, as done in Login Page
    public boolean isRegisterPageDisplayed() {
        return registerPageGreet.isDisplayed();
    }

    public LoginPage registerUser(String firstName, String lastName, String emailId, String pwd) {
        // Sleep time added for demonstration purpose only
        firstNameRegister.sendKeys(firstName);
        lastNameRegister.sendKeys(lastName);
        emailAddressRegister.sendKeys(emailId);
        passwordRegister.sendKeys(pwd);
        repeatPasswordRegister.sendKeys(pwd);
        registerButtonRegister.click();
        return new LoginPage();
    }
}
