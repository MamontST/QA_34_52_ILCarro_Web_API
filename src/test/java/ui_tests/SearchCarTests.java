package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test(groups = {"smoke", "regress", "car", "positive"})
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now()
                .plusDays(2);
        LocalDate endDate = LocalDate.now()
                .plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarPositiveWithCalendarTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now()
                .plusDays(2);
        LocalDate endDate = LocalDate.now()
                .plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarNegativeSameStartAndEndDatesTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

    @Test
    public void searchCarNegativeMoreOneYearTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1).plusDays(1);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date after one year"));
    }

    @Test(groups = {"calendar"})
    public void searchCarNegativeSameDatesWithCalendarTest() {
        String city = "Haifa";
        LocalDate date = LocalDate.now().plusDays(3);
        homePage.typeSearchFormWithCalendar(city, date, date);
        homePage.clickBtnSubmitWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

//    @Test(groups = {"calendar"})
//    public void searchCarNegativeMoreOneYearWithCalendarTest() {
//        String city = "Haifa";
//        LocalDate startDate = LocalDate.now().plusDays(1);
//        LocalDate endDate = LocalDate.now().plusYears(1).plusDays(2);
//        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
//        homePage.clickBtnSubmitWithJS();
//        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date after one year"));
//    }

}