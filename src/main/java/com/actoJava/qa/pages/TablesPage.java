package com.actoJava.qa.pages;

import com.actoJava.qa.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TablesPage extends BaseTest {

    // object repository for tables page
    @FindBy(tagName = "table")
    WebElement exampleTable;

    // constructor with PageFactory to initiate all the page objects
    public TablesPage() {
        PageFactory.initElements(driver, this);
    }

    // actions or functions on the Tables Page
    public boolean isExampleTableDisplayed() {
        return exampleTable.isDisplayed();
    }
}
