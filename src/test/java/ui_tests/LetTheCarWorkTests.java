package ui_tests;

import dto.Car;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;

import static utils.CarFactory.positiveCar;
import static utils.UserFactory.positiveUser;

public class LetTheCarWorkTests extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letCarWorkPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLetTheCarWorkPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(positiveUser());
        loginPage.clickBtnLogin();
        loginPage.clickBtnLoginMessage();

        new HomePage(getDriver()).clickBtnLetCarWork();
        letCarWorkPage = new LetTheCarWorkPage(getDriver());
        logger.info("Navigating to Let the car work page");

    }

    @Test
    public void letCarWorkPositiveTest()  {
        Car car = positiveCar();
        letCarWorkPage.typeLetCarWorkForm(car);
        letCarWorkPage.clickBtnSubmitWithJS();
    }

}
