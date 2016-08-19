package pages;

import utils.ClassNameUtil;
import utils.WebDriverWrapper;
import org.apache.log4j.Logger;


/**
 * Created by borys on 18.08.2016.
 */
public class ElectronicsSearchPage extends Page {
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public ElectronicsSearchPage(WebDriverWrapper dr) {super(dr); }

    public void searchForItem(String searchItemName, String locationToSearch, String periodToSearch) {
        web.selectFromList("LocationDDL", locationToSearch);
        web.selectFromList("PeriodDDL", periodToSearch);
        web.inputAndClickEnter("SearchingPhraseInputField", searchItemName);
        log.info("Parameters to search for are selected and confirmed");

    }

    public void changePriceBoundsParameterAndSearch(String minPrice, String maxPrice) {
        web.input("MinPriceInput", minPrice);
        web.inputAndClickEnter("MaxPriceInput", maxPrice);
        log.info("Min and Max price to search are filled");
    }
}
