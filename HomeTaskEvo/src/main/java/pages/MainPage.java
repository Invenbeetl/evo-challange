package pages;

import org.apache.log4j.Logger;
import utils.ClassNameUtil;
import utils.PropertyLoader;
import utils.WebDriverWrapper;

/**
 * Created by borys on 17.08.2016.
 */
public class MainPage extends Page {

    private static final String MAIN_PAGE = PropertyLoader.loadProperty("site.url");
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public MainPage(WebDriverWrapper dr) {
        super(dr, MAIN_PAGE);
    }


    public void switchLanguageToRus() {
        web.clickLink("ChangeLanguageLink");
        log.info("Change language link is clicked");
    }

    public void goToElectronicsPage() {
        web.clickLink("ElectronicsPageLink");
        log.info("Link to Electronics page is clicked");
    }
}