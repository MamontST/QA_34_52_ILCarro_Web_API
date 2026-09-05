package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

    }

    @FindBy(xpath = "//a[@ng-reflect-router-link='login']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[@ng-reflect-router-link='registration']")
    WebElement btnRegistration;
    @FindBy(xpath = "//a[@ng-reflect-router-link='let-car-work']")
    WebElement btnLetCarWork;
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;


    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void clickBtnRegistration() {
        btnRegistration.click();
    }

    public void clickBtnLetCarWork() {
        btnLetCarWork.click();
    }

    public void typeSearchForm(String city,
                               LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        System.out.println(startDate);
        System.out.println(endDate);
        // 2026-09-04  9/4/2026 - 9/10/2026
        System.out.println(startDate.getMonthValue());
        System.out.println(startDate.getDayOfMonth());
        String dates =
                startDate.getMonthValue() + "/"
                        + startDate.getDayOfMonth() + "/"
                        + startDate.getYear() + " - "
                        + endDate.getMonthValue() + "/"
                        + endDate.getDayOfMonth() + "/"
                        + endDate.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }


}
