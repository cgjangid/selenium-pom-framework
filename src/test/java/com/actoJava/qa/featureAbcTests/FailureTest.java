package com.actoJava.qa.featureAbcTests;

import com.actoJava.qa.base.BaseTest;
import com.actoJava.qa.pages.HomePage;
import com.actoJava.qa.pages.LoginPage;
import com.actoJava.qa.pages.UserRegisterPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class FailureTest extends BaseTest {

    @Test(priority = 2, enabled = true)
    public void registerUserFailedTest() {
        Reporter.log("======Open application======", true);
        LoginPage loginPage = new LoginPage();

        Reporter.log("======Navigate to User Register page======", true);
        UserRegisterPage userRegisterPage = loginPage.navigateToRegisterPage();

        Reporter.log("======Verify that Register Page is displayed======", true);
        Assert.assertTrue(userRegisterPage.isRegisterPageDisplayed(), "Register Page is NOT displayed");

        if (userRegisterPage.isRegisterPageDisplayed()) {
            Reporter.log("======Register a User with test data======", true);
            LoginPage loginPage1 = userRegisterPage.registerUser("firstName", "lastName", "email1@test.com", "password1");

            Reporter.log("======Verify that Login Page is displayed======", true);
            Assert.assertTrue(loginPage1.isLoginPageDisplayed(), "Login Page is NOT displayed");

            if (loginPage.isLoginPageDisplayed()) {
                Reporter.log("======Login to application with the registered user======", true);
                HomePage homePage = loginPage.loginToApplication("email@test.com", "password");

                Reporter.log("======Verify that user is logged in and Home Page is displayed======", true);
                Assert.assertTrue(homePage.isHomePageDisplayed(), "Home Page is NOT displayed");

                if (homePage.isHomePageDisplayed()) {
                    Reporter.log("======Verify that username is displayed after login======", true);
                    String loggedInUserName = homePage.getLoggedInUserName();
                    Assert.assertEquals(loggedInUserName, "Leonard Harper", "Logged-in user name is incorrect");

                    Reporter.log("======Logout from application======", true);
                    homePage.logoutFromApp();

                    Reporter.log("======Verify that user is successfully logged out======", true);
                    Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Logout is NOT Successful");
                }
            }
        }
    }
}
