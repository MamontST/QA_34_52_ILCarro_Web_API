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

public class LoginTests extends AppManager {
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
    }

    @Test
    public void LoginPositiveTest() {
        User user = User.builder()
                .username("mammoth.isr@gmail.com")
                .password("Zaqxsw39!")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnLogin();

        WebElement messageSuccess = getDriver().findElement(By.xpath("//h2[@class='message']"));
        Assert.assertEquals(messageSuccess.getText(), "Logged in success");
        loginPage.clickBtnLoginMessage();
    }
}
