package ui_tests;

import data_providers.UserDataProvider;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.TestNGListener;

import static utils.UserFactory.positiveRegistrationUser;

@Listeners(TestNGListener.class)
public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToSighUpPage() {
        logger.info("Navigating to Registration page");
        new HomePage(getDriver()).clickBtnRegistration();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        User user = positiveRegistrationUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnRegistration();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTestWithJS() {
        User user = positiveRegistrationUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnRegistrationWithJS();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTestWithActions() {
        User user = positiveRegistrationUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test(dataProvider = "dataProviderForRegistrationWrongNameOrPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void registrationWrongNameOrPasswordOrEmail(User user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnRegistration();
        Assert.assertFalse(registrationPage.isBtnYallaEnabled());
    }


}
