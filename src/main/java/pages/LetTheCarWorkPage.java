package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utils.enums.Fuel;

import java.io.File;

public class LetTheCarWorkPage extends BasePage{
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement inputLocation;
    @FindBy(xpath = "//input[@id='make']")
    WebElement inputManufacture;
    @FindBy(xpath = "//input[@id='model']")
    WebElement inputModel;
    @FindBy(xpath = "//input[@id='year']")
    WebElement inputYear;
    @FindBy(xpath = "//select[@id='fuel']")
    WebElement selectFuel;
    @FindBy(xpath = "//input[@id='seats']")
    WebElement inputSeats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement inputCarClass;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement inputCarRegistrationNumber;
    @FindBy(xpath = "//input[@id='price']")
    WebElement inputPrice;
    @FindBy(id = "photos")
    WebElement inputImage;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement textAreaAbout;


    public void clickBtnSubmitWithJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute('disabled')");
        pause(2000);
        btnSubmit.click();
    }

    public void typeLetCarWorkForm(Car car){
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputCarRegistrationNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(Double.toString(car.getPricePerDay()));
        textAreaAbout.sendKeys(car.getAbout());
    }

    private void chooseFuel(Fuel fuel) {
        selectFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void downloadImage(String fileName) {
        inputImage.sendKeys(new File("src/test/resources/"
                + fileName).getAbsolutePath());
    }
}
