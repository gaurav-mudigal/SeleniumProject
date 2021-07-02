package com.gaurav.PageObjects;

import com.gaurav.Base.TestBase;
import com.gaurav.CommonUtils.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Page objects and methods of search results
 */
public class SearchResults extends TestBase {

    @FindBy(xpath = "//div[contains(@class,'priceInfo')]//p")
    private WebElement priceInfo;

    @FindBy(xpath = "//*[@id='root']/div/div[2]/div[2]/div/div[1]/div/div[1]/div/div[1]/div/label/div/span[2]/span")
    private WebElement indigoFilterContainer;

    @FindBy(xpath = "//*[@id='root']/div/div[2]/div[2]/div/div[1]/div/div[1]/div/div[1]/div/label/div/span[2]/span[@title=\"IndiGo\"]")
    private WebElement indigoFilterLocator;

    @FindBy(xpath = "//*[@id=\"listing-id\"]/div/div[1]/div[2]/div[1]/label/div[1]/div[2]/div[1]/div[1]/p[1]/span")
    private List<WebElement> flightTimingLocators;

    @FindBy(xpath = "//*[@id=\"listing-id\"]/div/div[1]/div[2]/div[1]/label/div[1]/div[2]/div[1]/div[1]/p[1]/span")
    private WebElement flightTime;

    @FindBy(xpath = "//button[contains(@id, \"bookbutton\")]")
    private WebElement bookButton;

    @FindBy(xpath = "//span[contains(text() ,\"You have \")]")
    private WebElement isBookButtonSelected;

    @FindBy(xpath = "//ul[@class=\"appliedFilter\"]/li")
    private WebElement flightFilterEnabled;

    public SearchResults() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits until airlines filters are available and clicked on airlines.
     *
     * @return Element text of Airlines selected
     */
    public String filterByAirlines() {

        wait.until(ExpectedConditions.attributeToBe(indigoFilterContainer, "title", "IndiGo"));
        indigoFilterLocator.click();
        TestUtils.waitForVisibility(flightFilterEnabled);
        return flightFilterEnabled.getText();
    }

    /**
     * Method to find the earliest flight available in a particular day
     *
     * @param timingLocators Contains list of flight time WebElements
     * @param earlyOrLate    User preference for selecting early or late flight
     * @return Array of flight timings
     */
    private double getEarlyHour(List<WebElement> timingLocators, String earlyOrLate) {

        int flightTimeListSize = timingLocators.size();
        double[] fightTimeArray = new double[flightTimeListSize];
        double time = 0;
        for (int i = 0; i < flightTimeListSize; i++) {
            String timeAsText = timingLocators.get(i).getText();
            fightTimeArray[i] = Double.parseDouble(timeAsText.replace(":", "."));
        }
        if (earlyOrLate.equals("early")) {
            time = Arrays.stream(fightTimeArray).min().getAsDouble();
        } else if (earlyOrLate.equals("late")) {
            time = Arrays.stream(fightTimeArray).max().getAsDouble();
        }
        return time;
    }

    /**
     * Searches and selects earliest flight for departure
     *
     * @return Class Attribute value of selected flight
     */
    public String EarlyFlightSelection() {

        TestUtils.waitForVisibility(flightTime);
        double earlyHour = getEarlyHour(flightTimingLocators, "early");
        String earlyHourAsString = "" + earlyHour;
        String earliestFlight = earlyHourAsString.replace(".", ":");

        Pattern pattern = Pattern.compile(earliestFlight);
        wait.until(ExpectedConditions.textMatches(By.xpath("//span[contains(text() ,'" + earliestFlight + "')]"), pattern));
        WebElement earliestDepartureContainer = driver.findElement(By.xpath("//span[contains(text() ,'" + earliestFlight + "')]/../../../../../../div[1]"));

        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", earliestDepartureContainer);
        je.executeScript("arguments[0].click(true);", earliestDepartureContainer);

        WebElement earliestDeparture = driver.findElement(By.xpath("//span[contains(text() ,'" + earliestFlight + "')]/../../../../../../div[1]/.."));
        wait.until(ExpectedConditions.attributeContains(earliestDeparture, "class", "checked"));
        return earliestDeparture.getAttribute("class");
    }

    /**
     * Waits until book now button to be visible and clicks on book now button
     *
     * @return Element text value of modal that appears after button is selected
     */
    public String bookFlight() {
        TestUtils.waitForVisibility(bookButton);
        bookButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(isBookButtonSelected, "You have more fares to select from"));
        return isBookButtonSelected.getText();
    }
}
