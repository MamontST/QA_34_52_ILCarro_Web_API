package ui_tests;

import dto.User;
import manager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

import static utils.UserFactory.positiveUser;
import static utils.UserFactory.wrongPasswordUser;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        User user = positiveUser();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.isPopUpSuccessLoginDisplayed());
        Assert.assertEquals(loginPage.getMessage(), "Logged in success");
        loginPage.clickBtnLoginMessage();
    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        User user = wrongPasswordUser();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();

        Assert.assertTrue(loginPage.getMessage().contains("Login or Password incorrect"));
        loginPage.clickBtnLoginMessage();
    }

    @Test
    public void loginNegativeEmptyAllFields() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLoginForm();
        softAssert.assertEquals(loginPage.emailMessage(), "Email is required", "validate email message");
        softAssert.assertEquals(loginPage.passwordMessage(), "Password is required", "validate password message");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"), "validate email message");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate password message");
        softAssert.assertAll();
    }
}
