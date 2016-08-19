package pages;

import utils.ScreenShotMaker;
import utils.WebDriverWrapper;
import utils.WebElementsActions;

/**
 * Created by borys on 17.08.2016.
 */
public class Sslv {
    public WebElementsActions web;
    public MainPage mainPage;
    public ElectronicsPage electronicsPage;
    public ElectronicsSearchPage electronicsSearchPage;
    public SearchResultPage searchResultPage;
    public ScreenShotMaker screenShotMaker;


    public Sslv(WebDriverWrapper driver) {
        web  = new WebElementsActions(driver);

        mainPage = new MainPage(driver);
        electronicsPage = new ElectronicsPage(driver);
        electronicsSearchPage = new ElectronicsSearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        screenShotMaker = new ScreenShotMaker(driver);
    }
}
