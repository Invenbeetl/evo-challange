package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ClassNameUtil;
import utils.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
//      Cycle to mark item, ensure that item is marked and add ID of item to list of selected IDs
        for (int i = 0; i < numberOfItems; i++){
            Random random = new Random(System.currentTimeMillis());
            int idx = random.nextInt(listOfResultItems.size());

            listOfResultItems.get(idx).click();
            web.waitForElementSelected(listOfResultItems.get(idx));
            Assert.assertTrue(listOfResultItems.get(idx).isSelected(), "Element "
                    + listOfResultItems.get(idx) +" was not selected from list");
            log.info("Element with id " + listOfResultItems.get(idx).getAttribute("id") + " is selected from result list");

//          Using '.remove' to guarantee that even with same idx from '.random.nextInt' we will have unique ID of item
            listOfSelectedIDs.add(listOfResultItems.remove(idx).getAttribute("id"));
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
        ArrayList<String> listOfDisplayedItemsIDs = new ArrayList<>();

        if (listOfDisplayedItems.size() <=0 ||
                listOfDisplayedItems.size() != (listOfSelectedIDs.size())){
            log.warn("Displayed not enough results that were filtered");
            Assert.fail("Amount of displayed result items are not equal to those that were selected");
        }

//      Add ID of each displayed element to ArrayList
        listOfDisplayedItems.forEach(item -> listOfDisplayedItemsIDs.add(item.getAttribute("id")));

//      Cycle to check presence of selected items IDs in list of displayed items IDs
        listOfSelectedIDs.forEach(id->{
            if (!listOfDisplayedItemsIDs.contains(id)){
                log.warn("Selected and shown result are different");
                Assert.fail("Selected items list is differ than displayed items list");
            }
        });
    }
}
