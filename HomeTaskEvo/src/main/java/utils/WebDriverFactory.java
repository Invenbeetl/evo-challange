package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

/**
 * Created by borys on 17.08.2016.
 */
public class WebDriverFactory {

    public static WebDriverWrapper driverWrapper;

    /*Browsers constants*/
    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";

    public static final String browserName = PropertyLoader.loadProperty("browser.name");

    public static WebDriverWrapper initDriver(){
        if(FIREFOX.equals(browserName)){
            driverWrapper = new WebDriverWrapper( new FirefoxDriver());
        } else if(CHROME.equals(browserName)){
            System.setProperty("webdriver.chrome.driver", "C:\\Tool\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            driverWrapper = new WebDriverWrapper( new ChromeDriver(options));

        }  else {
            Assert.fail("Invalid driver name");
        }

        driverWrapper.manage().deleteAllCookies();
        driverWrapper.manage().window().maximize();

        return driverWrapper;
    }
}
