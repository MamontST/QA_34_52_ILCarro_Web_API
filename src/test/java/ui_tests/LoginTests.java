package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

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
        softAssert.assertTrue(loginPage.isPopUpSuccessLoginDisplayed());
        softAssert.assertEquals(loginPage.getMessage(), "Logged in success");
        softAssert.assertAll();
        loginPage.clickBtnLoginMessage();
    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        User user = wrongPasswordUser();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();

        Assert.assertTrue(loginPage.getMessage().contains("Login or Password incorrect"));
        loginPage.clickBtnLoginMessage();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
    }

    @Test
    public void loginNegativeEmptyPasswordTest() {
        User user = User.builder()
                .username(getProperty("base.properties","email"))
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        softAssert.assertFalse(loginPage.isBtnLoginEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate password message");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyEmailTest() {
        User user = User.builder()
                .username("")
                .password(getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();
        softAssert.assertFalse(loginPage.isBtnLoginEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"), "validate email message");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyAllFields() {
        loginPage.clickLoginForm();
        softAssert.assertEquals(loginPage.emailMessage(), "Email is required", "validate email message");
        softAssert.assertEquals(loginPage.passwordMessage(), "Password is required", "validate password message");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"), "validate email message");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate password message");
        softAssert.assertAll();
    }
}
