package com.gaurav.testCases;

import com.gaurav.Base.TestBase;
import com.gaurav.CommonUtils.RetryFailedTest;
import com.gaurav.PageObjects.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * FlightHomePageTest is a MakeMyTrip Home Page Test
 *
 * @author Gaurav Mudigal
 */
public class FlightHomePageTest extends TestBase {

    private HomePage homePage;

    @BeforeSuite
    public void initialize() {
        browserInitialization();
        setDriverCapabilities();
        homePage = new HomePage();
    }

    /**
     * Launches flight booking site and validates the title of the page
     */
    @Test(priority = 1, retryAnalyzer = RetryFailedTest.class)
    public void launchFlightBookingSite() {
        launchWebPage();
        String homePageTitle = homePage.validateHomepage();
        Assert.assertEquals(homePageTitle, "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
    }

    /**
     * Selects the round trip radio box and asserts if it is selected
     */
    @Test(priority = 2, retryAnalyzer = RetryFailedTest.class)
    public void selectRoundTrip() {
        String isRoundTripSelected = homePage.selectRoundTrip();
        Assert.assertEquals(isRoundTripSelected, "selected");
    }

    /**
     * Selects the from & to destinations and asserts if right details are selected.
     */
    @Test(priority = 3, retryAnalyzer = RetryFailedTest.class)
    public void selectDestinations() {
        String fromCitySelected = homePage.setFromCity("PNQ");
        Assert.assertEquals(fromCitySelected, "Pune");
        String toCitySelected = homePage.setToCity("DEL");
        Assert.assertEquals(toCitySelected, "Delhi");
    }

    /**
     * Selects departure and arrival dates from the calendar.
     */
    @Test(priority = 4, retryAnalyzer = RetryFailedTest.class)
    public void selectDepartureDates() {
        homePage.selectDeparture();
        homePage.selectReturnDateAfter(5);

    }

    /**
     * Finds and selects the search button and asserts if it is selected
     */
    @Test(priority = 5, retryAnalyzer = RetryFailedTest.class)
    public void selectSearchButton() {
        String isSearchButtonDisabled = homePage.searchFlights();
        Assert.assertEquals(isSearchButtonDisabled, "disable-btn");
    }



    /**
     * Closes the tabs and exits the browser
     */
    @AfterSuite
    public void closeSession() {
        exitBrowser();
    }
}
