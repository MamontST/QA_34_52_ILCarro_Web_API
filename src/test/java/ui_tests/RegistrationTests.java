package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SighUpPage;

import static utils.UserFactory.positiveRegistrationUser;

public class RegistrationTests extends AppManager {
    SighUpPage signUpPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToSighUpPage() {
        new HomePage(getDriver()).clickBtnRegistration();
        signUpPage = new SighUpPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        User user = positiveRegistrationUser();
        signUpPage.typeLoginForm(user);
        signUpPage.clickBtnRegistration();
    }

}
