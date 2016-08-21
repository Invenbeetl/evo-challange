package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by borys on 17.08.2016.
 */
public class WebElementsActions {

    private WebDriver driver;
    private static WebDriverWait waitForElement;
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());


    public WebElementsActions(WebDriver driver) {
        this.driver = driver;
        waitForElement = new WebDriverWait(driver, 20);
    }


    public void clickElement(String elementLocator) {
        driver.findElement(UIMappingSingleton.ui(elementLocator)).click();
    }


    /**
     * Click link
     */
    public void clickLink(String linkLocator)  {
        driver.findElement(UIMappingSingleton.ui(linkLocator)).click();
        log.info("Click on Link " + linkLocator);
    }

    /**
     * Insert value into text input HTML field
     */
    public void input(String inputLocator, String inputData)  {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).clear();
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }


    /**
     * Insert value into text input HTML field and Click ENTER for Field which used "Value" in the xpath expression
     */
    public void inputAndClickEnter(String inputLocator, String inputData) {
        driver.findElement(UIMappingSingleton.ui(inputLocator)).clear();
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(inputData);
        driver.findElement(UIMappingSingleton.ui(inputLocator)).sendKeys(Keys.ENTER);
        log.info("Input in " + inputLocator + ", value - " + inputData);
    }


    /**
     * Method is used to check that element is present on page.
     */
    public boolean isElementPresent(String elementLocator) {
        List<WebElement> list = driver.findElements(UIMappingSingleton.ui(elementLocator));

        if (list.size() == 0) {
            log.warn("Element _" + elementLocator + "_is NOT Present in DOM!");
            return false;
        }

        if (list.get(0).isDisplayed()) {
            log.info("Element _" + elementLocator + "_ is Present");
            return true;
        }
        else {
            log.error("Element _" + elementLocator + "_ is NOT Displayed Present!");
            return false;
        }
    }

    public List<WebElement> getElements(String elementsLocator)  {
        return driver.findElements(UIMappingSingleton.ui(elementsLocator));
    }



    /**
     * Select value from drop down list
     */
    public void selectFromList(String listLocator, String listValue)  {
        new Select(driver.findElement(UIMappingSingleton.ui(listLocator))).selectByValue(listValue);
        log.info("ListLocator " + listLocator + ", value - " + listValue);
    }


    /**
     * Wait for invisibility Of Element on page
     */
    public void waitForElementSelected (WebElement element) {
        log.info("*Start TO* Wait For Element Not Visible _" + element + "_");
        waitForElement.until(ExpectedConditions.elementToBeSelected(element));
    }


}

