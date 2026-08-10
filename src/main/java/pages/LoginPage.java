package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnLogin;
    @FindBy(xpath = "//app-error/div/div/button")
    WebElement btnLoginMessage;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement dialogMessage;
    @FindBy(xpath = "//div[contains(text(), 'Email is required')]")
    WebElement emailMessage;
    @FindBy(xpath = "//div[contains(text(), 'Password is required')]")
    WebElement passwordMessage;


    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickLoginForm() {
        inputEmail.click();
        inputPassword.click();
        inputEmail.click();
    }

    public void clickBtnLogin() {
        btnLogin.click();
        pause(2000);
    }

    public void clickBtnLoginMessage() {
        btnLoginMessage.click();
    }

    public String getMessage() {
        return dialogMessage.getText();
    }

    public String emailMessage(){
        return emailMessage.getText();
    }

    public String passwordMessage(){
        return passwordMessage.getText();
    }
}
