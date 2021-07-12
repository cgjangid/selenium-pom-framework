package com.actoJava.qa.base;

import com.actoJava.qa.util.WebDriverEventListenerImpl;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    // declaring all variables
    public static Properties prop;
    public static InputStream fileInputStream;

    public static WebDriver driver;
    public static EventFiringWebDriver e_driver;
    public static WebDriverEventListenerImpl eventListener;

    @BeforeMethod
    public void setup() {
    // this method will run Before each @Test method we will have

        try {
            //read properties file
            String propFilePath = System.getProperty("user.dir") + "/src/main/resources/config/config.properties";
            System.out.println(propFilePath);
            fileInputStream = new FileInputStream(propFilePath);

            // load properties file in Properties
            prop = new Properties();
            prop.load(fileInputStream);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // read which browser is required for test from config file
        String browser = prop.getProperty("browser").toUpperCase();

        // use WebDriverManager to automatically setup driver of the browser
        WebDriverManager.getInstance(DriverManagerType.valueOf(browser)).setup();

        // launch appropriate browser
        switch (browser) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "SAFARI":
                driver = new SafariDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            case "OPERA":
                driver = new OperaDriver();
                break;
            case "IEXPLORER":
                driver = new InternetExplorerDriver();
                break;
        }
        Reporter.log("======Launch Browser======", true);

        // initiate Event Firing Driver so that it will capture all events
        e_driver = new EventFiringWebDriver(driver);

        // create an object of WebDriverEventListenerImpl class
        // the WebDriverEventListenerImpl class is an implementation class of Selenium's WebDriverEventListener interface
        // this class is created in the next step.
        eventListener = new WebDriverEventListenerImpl();

        e_driver.register((eventListener));
        driver=e_driver;

        // navigate to application and set the pace of script
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));

    }

    @AfterMethod
    public void tearDown() {
        // this method will run Before each @Test method we will have

        driver.quit();
        Reporter.log("======Browser Closed======");
    }

}