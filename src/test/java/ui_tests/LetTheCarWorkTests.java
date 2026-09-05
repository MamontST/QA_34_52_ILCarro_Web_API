package ui_tests;

import dto.Car;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.enums.HeaderMenu;

import java.time.LocalDate;

import static utils.CarFactory.positiveCar;
import static utils.UserFactory.positiveUser;

public class LetTheCarWorkTests extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLetTheCarWorkPage() {
//        new HomePage(getDriver()).clickBtnLogin();
//        loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LOGIN);
        loginPage.typeLoginForm(positiveUser());
        loginPage.clickBtnLogin();
        new PopUpPage(getDriver()).clickBtnOk();
        letCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
        logger.info("Navigating to Let the car work page");

    }

    @Test
    public void letCarWorkPositiveTest()  {
        Car car = positiveCar();
        System.out.println(car);
        letCarWorkPage.typeLetCarWorkForm(car);
        letCarWorkPage.downloadImage("cat2.jpg");
        letCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));
    }

    @Test
    public void addNewCarNegativeWrongYearTest() {
        Car car = positiveCar();
        car.setYear(String.valueOf(LocalDate.now().getYear() + 1));
        System.out.println(car);
        letCarWorkPage.typeLetCarWorkForm(car);
        letCarWorkPage.downloadImage("cat2.jpg");
        Assert.assertTrue(letCarWorkPage.isTextInErrorPresent("Wrong year"));
    }

    @Test
    public void addNewCarNegativeWrongYearNotDigitTest() {
        Car car = positiveCar();
        car.setYear("a");
        System.out.println(car);
        letCarWorkPage.typeLetCarWorkForm(car);
        letCarWorkPage.downloadImage("cat2.jpg");
        Assert.assertTrue(letCarWorkPage.isTextInErrorPresent("Year required"));
    }


}
