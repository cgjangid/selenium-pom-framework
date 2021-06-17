package com.actoJava.qa.endToEndTests;

import com.actoJava.qa.base.BaseTest;
import com.actoJava.qa.pages.HomePage;
import com.actoJava.qa.pages.LoginPage;
import com.actoJava.qa.pages.RegisterPage;
import com.actoJava.qa.util.ExcelUtility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultipleTestDataE2ETestExample extends BaseTest {

    @Test(priority = 1, enabled = true, dataProvider = "getUserData")
    public void testNewUserMultipleTestData(String firstName, String lastName, String email, String pwd) {
        Reporter.log("======Open application======", true);
        LoginPage loginPage = new LoginPage();

        Reporter.log("======Navigate to User Register page======", true);
        RegisterPage registerPage = loginPage.navigateToRegisterPage();

        Reporter.log("======Verify that Register Page is displayed======", true);
        Assert.assertTrue(registerPage.isRegisterPageDisplayed(), "Register Page is displayed");

        if (registerPage.isRegisterPageDisplayed()) {
            Reporter.log("======Register a User with test data======", true);
            registerPage.registerUser(firstName, lastName, email, pwd);

            Reporter.log("======Verify that Login Page is displayed======", true);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is displayed");

            if (loginPage.isLoginPageDisplayed()) {
                Reporter.log("======Login to application with the registered user======", true);
                HomePage homePage = loginPage.loginToApplication(email, pwd);

                Reporter.log("======Verify that user is logged in and Home Page is displayed======", true);
                Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is displayed");

                if (homePage.isHomePageDisplayed()) {
                    Reporter.log("======Verify that username is displayed after login======", true);
                    String loggedInUserName = homePage.getLoggedInUserName();
                    Assert.assertEquals(loggedInUserName, "Leonard Harper");

                    Reporter.log("======Logout from application======", true);
                    homePage.logoutFromApp();

                    Reporter.log("======Verify that user is successfully logged out======", true);
                    Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Logout Successful");
                }
            }
        }
    }

    @DataProvider
    public Object[][] getUserData() {
        Object[][] userData = ExcelUtility.getExcelData("NewUserData");
        return userData;
    }

}
