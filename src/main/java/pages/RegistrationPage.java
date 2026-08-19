package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static pages.BasePage.driver;

public class RegistrationPage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@id='lastName']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYallaRegistration;
    // //div[contains(@class, 'checkbox')]
    // //input[@id='terms-of-use']"
    @FindBy(xpath = "//div[contains(@class, 'checkbox')]")
    WebElement inputTermsOfUse;
    @FindBy(xpath = "//div[contains(text(), 'Name is required')]")
    WebElement nameMessage;
    @FindBy(xpath = "//div[contains(text(), 'Last name is required')]")
    WebElement lastNameMessage;
    @FindBy(xpath = "//div[contains(text(), 'Email is required')]")
    WebElement emailMessage;
    @FindBy(xpath = "//div[contains(text(), 'Password is required')]")
    WebElement passwordMessage;
    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBoxTermsOfUse;

    public void typeRegistrationForm(User user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnRegistration() {
        inputTermsOfUse.click();
        btnYallaRegistration.click();
    }

    public void clickBtnRegistrationWithJS() {
        clickCheckboxTermsOfUse();
        btnYallaRegistration.click();
    }


    public boolean isBtnYallaEnabled() {
        return btnYallaRegistration.isEnabled();
    }

    public String nameMessage() {
        return nameMessage.getText();
    }

    public String lastNameMessage() {
        return lastNameMessage.getText();
    }

    public String emailMessage() {
        return emailMessage.getText();
    }

    public String passwordMessage() {
        return passwordMessage.getText();
    }

    public void clickCheckboxTermsOfUse() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxTermsOfUse);
    }

}

