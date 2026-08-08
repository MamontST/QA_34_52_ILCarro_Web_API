package ui_tests;

import dto.AuthenticationBody;
import manager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

import static java.sql.DriverManager.getDriver;

public class LoginTests extends AppManager {
    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
    }

    @Test
    public void LoginPositiveTest(){
        AuthenticationBody user = AuthenticationBody.builder()
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
