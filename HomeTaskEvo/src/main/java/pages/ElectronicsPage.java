package pages;

import utils.ClassNameUtil;
import utils.WebDriverWrapper;
import org.apache.log4j.Logger;

/**
 * Created by borys on 18.08.2016.
 */
public class ElectronicsPage extends Page {

    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public ElectronicsPage(WebDriverWrapper dr) {
        super(dr);
    }


    public void goToElectronicsSearchPage() {
        web.clickLink("SearchPageLink");
        log.info("Link to search page (Electronics search) is clicked");

    }
}
