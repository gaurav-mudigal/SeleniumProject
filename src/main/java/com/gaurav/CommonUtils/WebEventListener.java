package com.gaurav.CommonUtils;

import com.gaurav.Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Event listeners that are triggered before or after events
 */
public class WebEventListener extends TestBase implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(WebDriver driver) {
        //TODO Yet to be implemented
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void onException(Throwable error, WebDriver driver) {
        System.out.println("Exception occured: " + error);
        TestUtils.takeScreenShot("errorFound");
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        //TODO Yet to be implemented

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        //TODO Yet to be implemented

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        //TODO Yet to be implemented

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        //TODO Yet to be implemented

    }
}
