package ui_tests;

import dto.User;
import manager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.UserFactory.positiveUser;
import static utils.UserFactory.wrongPasswordUser;

public class LoginTests extends AppManager {
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
    }

    @Test
    public void loginPositiveTest() {
        User user = positiveUser();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();

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
        Assert.assertEquals(loginPage.emailMessage(), "Email is required");
        Assert.assertEquals(loginPage.passwordMessage(), "Password is required");
    }
}
