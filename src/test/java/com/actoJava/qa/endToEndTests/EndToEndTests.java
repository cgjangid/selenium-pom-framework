package com.actoJava.qa.endToEndTests;

import com.actoJava.qa.base.BaseTest;
import com.actoJava.qa.pages.HomePage;
import com.actoJava.qa.pages.LoginPage;
import com.actoJava.qa.pages.UserRegisterPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {

    // this test uses hardcoded test data
    // another class is created which uses parameterised test data from Excel
    @Test(priority = 1, enabled = true)
    public void testNewUserRegisterAndLogin() {
        Reporter.log("======Open application======", true);
        LoginPage loginPage = new LoginPage();

        Reporter.log("======Navigate to User Register page======", true);
        UserRegisterPage userRegisterPage = loginPage.navigateToRegisterPage();

        Reporter.log("======Verify that Register Page is displayed======", true);
        Assert.assertTrue(userRegisterPage.isRegisterPageDisplayed(), "Register Page is NOT displayed");

        if (userRegisterPage.isRegisterPageDisplayed()) {
            Reporter.log("======Register a User with test data======", true);
            userRegisterPage.registerUser("firstName", "lastName", "email@test.com", "password");

            Reporter.log("======Verify that Login Page is displayed======", true);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is NOT displayed");

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
                    Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Logout is NOT successful");
                }
            }
        }
    }
}
