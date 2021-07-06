package com.actoJava.qa.pages;

import com.actoJava.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {

    // object repository for home page
    @FindBy(xpath = "//*[@id=\"userDropdown\"]/span")
    WebElement loggedInUserName;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/h1")
    WebElement homePageHeader;

    @FindBy(xpath = "//*[@id=\"accordionSidebar\"]/li[6]/a")
    WebElement linkForTablesPage;

    @FindBy(xpath = "//*[@id=\"content\"]/nav/ul/li[4]/div/a[4]")
    WebElement logoutLink;

    @FindBy (xpath = "//*[@id=\"logoutModal\"]/div/div/div[3]/a")
    WebElement logoutButtonInPopup;

    // constructor with PageFactory to initiate all the page objects
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // actions or functions on the Home Page
    // recommended update for isDisplayed code : wrap it with try catch, as done in Login Page
    public boolean isHomePageDisplayed() {
        return homePageHeader.isDisplayed();
    }

    public String getLoggedInUserName() {
        return loggedInUserName.getText();
    }

    public LoginPage logoutFromApp() {
        loggedInUserName.click();
        logoutLink.click();
        logoutButtonInPopup.click();
        return new LoginPage();
    }
}
