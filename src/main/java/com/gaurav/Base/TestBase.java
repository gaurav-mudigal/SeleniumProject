package com.gaurav.Base;

import com.gaurav.CommonUtils.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * TestBase is a base class that is extended across all classes. since
 * it defines most commonly used methods like browser, driver and action
 * class initialization, set driver capabilities, launch & exit pages.
 */
public class TestBase {

    private static String URL;
    private static String browser;
    private static String chromePath;
    private static String firefoxPath;
    private static String edgePath;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static Properties prop;
    public static EventFiringWebDriver eventFiringDriver;
    public static WebEventListener webEventListener;


    public TestBase() {
        try {
            prop = new Properties();
            prop.load(new FileInputStream("./config/Config.properties"));
            URL = prop.getProperty("URL");
            browser = prop.getProperty("Browser");
            chromePath = prop.getProperty("chromeDriverPath");
            firefoxPath = prop.getProperty("firefoxDriverPath");
            edgePath = prop.getProperty("edgeDriverPath");
        } catch (Exception e) {
            System.err.println("Unable to read config file" + e.getMessage());
        }
    }

    /**
     * Initializes browser settings
     */
    public static void browserInitialization() {

        switch (browser) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", chromePath);
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", firefoxPath);
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                System.setProperty("webdriver.edge.driver", edgePath);
                driver = new EdgeDriver();
            }
        }
    }

    /**
     * Sets the driver and event listner capabilities globally
     */
    public static void setDriverCapabilities() {
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        actions = new Actions(driver);
        eventFiringDriver = new EventFiringWebDriver(driver);
        webEventListener = new WebEventListener();
        eventFiringDriver.register(webEventListener);
        driver = eventFiringDriver;
    }

    /**
     * Launches the web page
     */
    public static void launchWebPage() {
        driver.get(URL);
    }

    /**
     * Exits the browser
     */
    public static void exitBrowser() {
        driver.close();
        driver.quit();
    }
}
