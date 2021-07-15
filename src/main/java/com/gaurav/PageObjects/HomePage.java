package com.gaurav.PageObjects;

import com.gaurav.Base.TestBase;
import com.gaurav.CommonUtils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page objects and methods for homepage test cases
 */
public class HomePage extends TestBase {

    @FindBy(xpath = "//li[text() = 'Round Trip']")
    private WebElement roundTrip;

    @FindBy(xpath = "//input[@id= 'fromCity']")
    private WebElement fromCity;

    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement fromTextBox;

    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement toCityTextBox;

    @FindBy(id = "toCity")
    private WebElement toCity;

    @FindBy(linkText = "SEARCH")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@id='search-button']")
    private WebElement searchButtonDisabled;

    @FindBy(xpath = "//div[@class='dateInnerCell']")
    private WebElement dateInCalendar;


    /**
     * PageFactory initializes every every WebElement variables
     */
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for visibility of search button to ensure page is loaded
     *
     * @return Title of the page
     */
    public String validateHomepage() {
        TestUtils.waitForVisibility(searchButton);
        return driver.getTitle();
    }

    /**
     * Ensures sign in popup's are exited and round trip radio button is clicked
     *
     * @return Round trip class attribute
     */
    public String selectRoundTrip() {
        actions.moveByOffset(1, 1).click().perform();
        TestUtils.waitForElementToBeClickable(roundTrip);
        roundTrip.click();
        return roundTrip.getAttribute("class");
    }

    /**
     * Waits for from city elements to be clickable and sets city value
     *
     * @param city Desired from city
     * @return Value of from city
     */
    public String setFromCity(String city) {
        TestUtils.waitForElementToBeClickable(fromCity);
        fromCity.click();
        fromTextBox.sendKeys(city);

        fromTextBox.sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
        return fromCity.getAttribute("value");
    }

    /**
     * Waits for to city element to clickable and sets city value
     *
     * @param city Desired to city
     * @return Value of to city
     */
    public String setToCity(String city) {

        TestUtils.waitForElementToBeClickable(toCityTextBox);
        toCityTextBox.click();
        toCityTextBox.sendKeys(city);

        wait.until(ExpectedConditions.textToBePresentInElementValue(toCityTextBox, city));
        toCityTextBox.sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
        return toCity.getAttribute("value");
    }

    /**
     * Waits for search button to be clickable and clicks on search
     *
     * @return Attribute of disabled search button class
     */
    public String searchFlights() {
        TestUtils.waitForElementToBeClickable(searchButton);
        searchButton.click();
        return searchButtonDisabled.getAttribute("class");
    }

    /**
     * Waits until dates in calendar are clickable and clicks on
     * tomorrow's date by default
     */
    public void selectDeparture() {
        TestUtils.waitForElementToBeClickable(dateInCalendar);
        driver.findElement(By.xpath("//div[contains(@aria-label,'" + TestUtils.getTomorrowDate() + "')]")).click();
    }

    /**
     * Selects return date of the round trip
     *
     * @param days Days after which user wishes to return
     */
    public void selectReturnDateAfter(int days) {
        driver.findElement(By.xpath("//div[contains(@aria-label,'" + TestUtils.getDateAfterXDays(days) + "')]")).click();
    }
}
