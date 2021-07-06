package com.actoJava.qa.endToEndTests;

import com.actoJava.qa.base.BaseTest;
import com.actoJava.qa.pages.HomePage;
import com.actoJava.qa.pages.LoginPage;
import com.actoJava.qa.pages.UserRegisterPage;
import com.actoJava.qa.util.ExcelUtility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultipleTestDataE2ETestExample extends BaseTest {

    // this test uses test data from dataProvider which fetches data from excel
    @Test(priority = 2, enabled = true, dataProvider = "getUserData")
    public void testNewUserMultipleTestData(String firstName, String lastName, String email, String pwd) {
        Reporter.log("======Open application======", true);
        LoginPage loginPage = new LoginPage();

        Reporter.log("======Navigate to User Register page======", true);
        UserRegisterPage userRegisterPage = loginPage.navigateToRegisterPage();

        Reporter.log("======Verify that Register Page is displayed======", true);
        Assert.assertTrue(userRegisterPage.isRegisterPageDisplayed(), "Register Page is NOT displayed");

        if (userRegisterPage.isRegisterPageDisplayed()) {
            Reporter.log("======Register a User with test data======", true);
            userRegisterPage.registerUser(firstName, lastName, email, pwd);

            Reporter.log("======Verify that Login Page is displayed======", true);
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login Page is NOT displayed");

            if (loginPage.isLoginPageDisplayed()) {
                Reporter.log("======Login to application with the registered user======", true);
                HomePage homePage = loginPage.loginToApplication(email, pwd);

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

    @DataProvider
    public Object[][] getUserData() {
        Object[][] userData = ExcelUtility.getExcelData("NewUserData");
        return userData;
    }

}
