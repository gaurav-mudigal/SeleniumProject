package com.gaurav.testCases;

import com.gaurav.Base.TestBase;
import com.gaurav.CommonUtils.RetryFailedTest;
import com.gaurav.PageObjects.HomePage;
import com.gaurav.PageObjects.SearchResults;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * FlightSelectionTest is a end to end workflow of MakeMyTrip flight booking
 *
 * @author Gaurav Mudigal
 */
public class FlightDetailsSelectionTest extends TestBase {

    private HomePage homePage;
    private SearchResults searchResults;

    @BeforeTest
    public void initialize() {
        browserInitialization();
        setDriverCapabilities();
        homePage = new HomePage();
        searchResults = new SearchResults();
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
     * Applies filters by Airlines on search result and asserts airlines are selected
     */
    @Test(priority = 6, retryAnalyzer = RetryFailedTest.class)
    public void filterByAirlinesCheckbox() {
        String filterSelected = searchResults.filterByAirlines();
        Assert.assertEquals(filterSelected, "INDIGO");
    }

    /**
     * Selects the earliest flight from the search results
     */
    @Test(priority = 7, retryAnalyzer = RetryFailedTest.class)
    public void selectEarlyFlight() {
        String isEarliestFlightSelected = searchResults.EarlyFlightSelection();
        Assert.assertEquals(isEarliestFlightSelected, "splitViewListing checked ");
    }

    /**
     * Selects the Book now button and asserts if its clicked
     */
    @Test(priority = 8, retryAnalyzer = RetryFailedTest.class)
    public void bookFlightTicket() {
        String isFlightBooked = searchResults.bookFlight();
        Assert.assertEquals(isFlightBooked, "You have more fares to select from");
    }

    /**
     * Closes the tabs and exits the browser
     */
    @AfterTest
    public void closeSession() {
        exitBrowser();
    }
}
