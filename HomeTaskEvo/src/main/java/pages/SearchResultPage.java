package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by borys on 18.08.2016.
 */
public class SearchResultPage extends Page {
    private static final Logger log = Logger.getLogger(ClassNameUtil.getCurrentClassName());

    public SearchResultPage(WebDriverWrapper dr) {
        super(dr);
    }

    public void sortResultsByPrice() {
        web.clickLink("PriceSortingLink");
        log.info("Link for sorting by price is clicked");
    }

    public void selectSellTypeOfDeal() {
        web.clickElement("TypeOfDealDDL");
        web.clickElement("OptionSellTypeOfDeal");
        log.info("\"Sell\" type of deal is selected from DDL");
    }

    public void goToElectronicsSearchPage() {
        web.clickLink("AdvancedSearchLink");
        log.info("Advanced search link is clicked");
    }

    public void checkEqualityOfSelectedAndDisplayedItems(int numberOfItems){
        ArrayList<String> listOfSelectedItems = selectItemsFromResultList(numberOfItems);
        showSelectedItems();
        checkEqualityOfSelectedAndDisplayedItemsIDs(listOfSelectedItems);

    }

//    Select items by marking checkboxes. Method return ArrayList with IDs of selected items
    public ArrayList<String> selectItemsFromResultList(int numberOfItems) {
        List<WebElement> listOfResultItems = web.getElements("SearchResultItems");
        ArrayList<String> listOfSelectedIDs = new ArrayList<>();

        if (numberOfItems <= 0 &&
                listOfResultItems.size() < numberOfItems){
            log.warn("Result list contains not enough elements");
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numberOfItems; i++){
            listOfResultItems.get(i).click();
            web.waitForElementSelected(listOfResultItems.get(i));
            Assert.assertTrue(listOfResultItems.get(i).isSelected(), "Element "
                    + listOfResultItems.get(i) +" was not selected from list");
            log.info("Element " + listOfResultItems.get(i)+ " is selected from result list");
            listOfSelectedIDs.add(listOfResultItems.get(i).getAttribute("id"));
        }
        return listOfSelectedIDs;
    }

    public void showSelectedItems() {
        web.clickLink("SelectedItemsLink");
        log.info("Switch to Selected results page is performed");
    }

//    Method receive ArrayList with IDs of selected items and check equality between received IDs and shown
    public void checkEqualityOfSelectedAndDisplayedItemsIDs(ArrayList<String> listOfSelectedIDs) {
        List<WebElement> listOfDisplayedItems = web.getElements("FilteredResultItems");
        log.info("----------------"+listOfDisplayedItems.size());
        ArrayList<String> listOfDisplayedItemsIDs = new ArrayList<>();

        if (listOfDisplayedItems.size() <=0 ||
                listOfDisplayedItems.size() != (listOfSelectedIDs.size())){
            log.warn("Displayed not enough results that were filtered");
            Assert.fail("Amount of displayed result items are not equal to those that were selected");
        }
        for (int i=0; i<listOfDisplayedItems.size(); i++){
            listOfDisplayedItemsIDs.add(listOfDisplayedItems.get(i).getAttribute("id"));

        }


        /*Cycle to check presence of displayed item IDs in list of selected item IDs*/
        int counter=0;
        for (int i=0; i < listOfDisplayedItemsIDs.size(); i++){
            for(int j=0; j < listOfSelectedIDs.size(); j++){
                if (listOfDisplayedItemsIDs.get(i).equals(listOfSelectedIDs.get(j))){
                    counter++;
                }
            }
            if (counter == (i)){
                log.warn("Selected and shown result are different");
                Assert.fail("Selected items list is differ than displayed items list");
            }
        }

    }
}
