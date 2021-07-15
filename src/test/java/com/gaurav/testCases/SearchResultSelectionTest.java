package com.gaurav.testCases;

import com.gaurav.Base.TestBase;
import com.gaurav.CommonUtils.RetryFailedTest;
import com.gaurav.PageObjects.SearchResults;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchResultSelectionTest extends TestBase {

    private SearchResults searchResults;

    @BeforeTest
    public void initialize() {
        searchResults = new SearchResults();
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

    @AfterSuite
    public void closeSession(){
        exitBrowser();
    }
}
