package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import pages.Sslv;
import utils.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by borys on 17.08.2016.
 */
public class Fixture {

    public static Sslv sslv;
    static WebDriverWrapper driver;
    private static final String IMPLICIT_WAIT = PropertyLoader.loadProperty("wait.timeout");
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    @BeforeSuite
    public static void setUp() {
        UIMappingSingleton.getInstance();

        driver = WebDriverFactory.initDriver();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICIT_WAIT), TimeUnit.SECONDS);
        sslv = new Sslv(driver);
        log.info("<=== Start test class - ===>");
    }


    @AfterSuite
    public static void tearDown() {
        log.info("<=== Finished test class ===>");
        log.info("Close Browser!");
        driver.quit();
    }
}
