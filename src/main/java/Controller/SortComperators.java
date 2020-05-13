package Controller;

import Model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortComperators {
    private static SortComperators sortComperators;
    private String currentSort = new String();


    private SortComperators() {
    }

    protected static SortComperators getInstance() {
        if (sortComperators == null)
            sortComperators = new SortComperators();
        return sortComperators;
    }

    protected ArrayList<String> sortByView(ArrayList<String> allItemsId) {
        ArrayList<Item> allItems = getAllItem(allItemsId);
        Collections.sort(allItems, new ViewCountComparator());
        return getAllId(allItems);
    }

    protected ArrayList<String> sortByRating(ArrayList<String> allItemsId) {
        ArrayList<Item> allItems = getAllItem(allItemsId);
        Collections.sort(allItems, new ratingComparator());
        return getAllId(allItems);
    }

    protected ArrayList<String> sortByPriceLowToHigh(ArrayList<String> allItemsId) {
        ArrayList<Item> allItems = getAllItem(allItemsId);
        Collections.sort(allItems, new priceLowToHighComparator());
        return getAllId(allItems);
    }

    protected ArrayList<String> sortByPriceHighToLow(ArrayList<String> allItemsId) {
        ArrayList<Item> allItems = getAllItem(allItemsId);
        Collections.sort(allItems, new priceHighToLowComparator());
        return getAllId(allItems);
    }

    protected ArrayList<String> SortByCommentCount(ArrayList<String> allItemsId) {
        ArrayList<Item> allItems = getAllItem(allItemsId);
        Collections.sort(allItems, new commentCountComparator());
        return getAllId(allItems);
    }

    private ArrayList<Item> getAllItem(ArrayList<String> allItemsId) {
        ArrayList<Item> allItems = new ArrayList<>();
        for (String id : allItemsId) {
            Item item = ItemAndCategoryController.getInstance().getItemById(id);
            if (item != null)
                allItems.add(item);
        }
        return allItems;
    }

    private ArrayList<String> getAllId(ArrayList<Item> allItems) {
        ArrayList<String> allItemsId = new ArrayList<String>();
        for (Item item : allItems) {
            allItemsId.add(item.getId());
        }
        return allItemsId;
    }


    class ViewCountComparator implements Comparator<Item> {
        @Override
        public int compare(Item item1, Item item2) {
            int viewCountDifference = item1.getViewCount() - item2.getViewCount();
            return viewCountDifference;
        }
    }

    class ratingComparator implements Comparator<Item> {
        @Override
        public int compare(Item item1, Item item2) {
            double gradeDifference = item1.getRating() - item2.getRating();
            if (gradeDifference > 0) return 1;
            else if (gradeDifference < 0) return -1;
            return 0;
        }
    }

    class priceLowToHighComparator implements Comparator<Item> {
        @Override
        public int compare(Item item1, Item item2) {
            double priceDifference = item1.getPrice() - item2.getPrice();
            if (priceDifference > 0) return 1;
            else if (priceDifference < 0) return -1;
            else return 0;
        }
    }

    class priceHighToLowComparator implements Comparator<Item> {
        @Override
        public int compare(Item item1, Item item2) {
            double priceDifference = item1.getPrice() - item2.getPrice();
            if (priceDifference > 0) return -1;
            else if (priceDifference < 0) return +1;
            else return 0;
        }
    }

    class commentCountComparator implements Comparator<Item> {
        @Override
        public int compare(Item item1, Item item2) {
            double priceDifference = item1.getAllComments().size() - item2.getAllComments().size();
            if (priceDifference > 0) return 1;
            else if (priceDifference < 0) return -1;
            else return 0;
        }
    }
}





