package tests;

import org.testng.annotations.Test;

/**
 * Created by borys on 17.08.2016.
 */
public class HomeworkTests extends Fixture {

    @Test
    public void checkoutTest (){
        sslv.mainPage.openPage();

        sslv.mainPage.switchLanguageToRus();
        sslv.mainPage.goToElectronicsPage();

        sslv.electronicsPage.goToElectronicsSearchPage();

        sslv.electronicsSearchPage.searchForItem("Компьютер", "riga_f", "0");

        sslv.searchResultPage.sortResultsByPrice();
        sslv.searchResultPage.selectSellTypeOfDeal();
        sslv.searchResultPage.goToElectronicsSearchPage();

        sslv.electronicsSearchPage.changePriceBoundsParameterAndSearch("0", "300");


        sslv.searchResultPage.checkEqualityOfSelectedAndDisplayedItems(3);


    }

}
