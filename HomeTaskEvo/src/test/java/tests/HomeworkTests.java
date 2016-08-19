package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import utils.ClassNameUtil;
import utils.PropertyLoader;

/**
 * Created by borys on 17.08.2016.
 */
public class HomeworkTests extends Fixture {
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    @Test
    public void checkoutTest (){
        sslv.mainPage.openPage();

        sslv.mainPage.switchLanguageToRus();
        sslv.mainPage.goToElectronicsPage();

        sslv.electronicsPage.goToElectronicsSearchPage();

        sslv.electronicsSearchPage.searchForItem("телефон", "riga_f", "0");

        sslv.searchResultPage.sortResultsByPrice();
        sslv.searchResultPage.selectSellTypeOfDeal();
        sslv.searchResultPage.goToElectronicsSearchPage();

        sslv.electronicsSearchPage.changePriceBoundsParameterAndSearch("0", "300");


        sslv.searchResultPage.checkEqualityOfSelectedAndDisplayedItems(3);
//        sslv.screenShotMaker.takeScreenShot(ClassNameUtil.getCurrentClassName());


    }

}
