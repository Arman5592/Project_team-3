package Model;

import Controller.Controller;
import Controller.Database;
import Controller.ItemAndCategoryController;
import Controller.UserController;
import Controller.SaleAndDiscountCodeController;
import Model.Users.Seller;
import com.google.gson.internal.$Gson$Preconditions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Item {
    private String id;
    private String state;
    private String description;
    private String name;
    private String brand;
    private int timesBought;
    private double price;
    private int inStock;
    private int viewCount;
    private HashMap<String, String> attributes;
    private String sellerName;
    private String categoryName;
    private ArrayList<String> buyerUserName;
    private ArrayList<Rating> allRatings;
    private ArrayList<Comment> allComments;
    private String saleId;

    private String addedTime;

    //constructor
    public Item(String name, String brand, String description, String state, double price, String sellerName, String categoryName, HashMap<String, String> attributes, int inStock) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.state = state;
        this.price = price;
        this.timesBought = 0;
        this.sellerName = sellerName;
        this.categoryName = categoryName;
        this.attributes = attributes;
        this.inStock = inStock;
        saleId = "";
        this.id = Controller.getInstance().getAlphaNumericString(Controller.getInstance().getIdSize(), "Items");
        timesBought = 0;
        allRatings = new ArrayList<>();
        allComments = new ArrayList<>();
        buyerUserName = new ArrayList<>();
    }

    public Item(Item item) {
        this.name = item.name;
        this.brand = item.brand;
        this.description = item.description;
        this.state = item.state;
        this.price = item.price;
        this.timesBought = 0;
        this.sellerName = item.sellerName;
        this.categoryName = item.categoryName;
        this.attributes = item.attributes;
        this.inStock = item.inStock;
        this.id = Controller.getInstance().getAlphaNumericString(Controller.getInstance().getIdSize(), "Items");
        timesBought = 0;
        saleId = "";
        allRatings = new ArrayList<>();
        allComments = new ArrayList<>();
        buyerUserName = new ArrayList<>();
        UserController.getInstance().assignItemToSeller(id, sellerName);
    }
    //getters

    public void delete() {
        Category category = ItemAndCategoryController.getInstance().getCategoryByName(categoryName);
        Sale sale = SaleAndDiscountCodeController.getInstance().getSaleById(id);
        if (category != null) {
            category.removeItem(id);
            Database.getInstance().saveCategory(category);
        }
        if (sale != null) {
            sale.removeItemFromSale(id);
            Database.getInstance().saveSale(sale);
        }
        Seller seller = (Seller) UserController.getInstance().getUserByUsername(this.getSellerName());
        seller.deleteItem(this.getId());
        Database.getInstance().saveUser(seller);
    }

    public double getPrice() {
        return price;
    }

    public double getPriceWithSale() {
        Sale sale = SaleAndDiscountCodeController.getInstance().getSaleById(saleId);
        if (sale == null) {
            return price;
        }
        LocalDateTime startTime = sale.getStartTime();
        if (startTime.isAfter(LocalDateTime.now())) {
            return price;
        }
        return price * (100 - sale.getOffPercentage()) / 100;

    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasUserRated(String username) {
        for (Rating rating : allRatings) {
            if (rating.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public double getRating() {
        double ratingSum = 0;
        if (allRatings.size() == 0) {
            return 0;
        } else {
            for (Rating rating : allRatings) {
                ratingSum += rating.getScore();
            }
            return ratingSum / allRatings.size();
        }
    }

    //setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }


    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void addAttribute(String key,String value){
        attributes.put(key,value);
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void addComment(Comment newComment) {
        this.allComments.add(newComment);
        Database.getInstance().saveItem(this);
    }

    public void addRating(Rating newRating) {
        this.allRatings.add(newRating);
        Database.getInstance().saveItem(this);
    }

    public ArrayList<String> getBuyerUserName() {
        return buyerUserName;
    }

    public boolean isInSale() {
        Sale sale = SaleAndDiscountCodeController.getInstance().getSaleById(saleId);
        return sale != null;
    }

    public void addBuyerUserName(String userName) {
        buyerUserName.add(userName);
    }

    public boolean isBuyerWithUserName(String userName) {
        if (buyerUserName.contains(userName)) return true;
        return false;
    }

    public void setSale(String saleId) {
        String previousSale = saleId;
        this.saleId = saleId;
        Sale sale = SaleAndDiscountCodeController.getInstance().getSaleById(previousSale);
        if (sale == null) return;
        sale.removeItemFromSale(this.id);
    }

    public String showAttributes() {
        String string = "";
        ArrayList<String> attributesKey = ItemAndCategoryController.getInstance().getCategoryByName(categoryName).getAttributes();
        for (String key : attributesKey) {
            string += key + ":" + attributes.get(key) + "\n";
        }
        return string;
    }

    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    public void setAttribute(String attributeName, String value) {
        attributes.replace(attributeName, value);
    }

    @Override
    public String toString() {
        String string = name + "\nID: " + id + "\nSeller:" + sellerName + "\nStock:" + inStock + "\nPrice:" + price;
        if (isInSale()) {
            Sale sale = SaleAndDiscountCodeController.getInstance().getSaleById(saleId);
            string += "\nprice after sale: " + price * (100-sale.getOffPercentage()) / 100;
        }
        string += "\nRating= " + getRating();
        return string;
    }

    public String toSimpleString() {
        return id + "        " + name + "        " + price;
    }

    public String showIdWithName() {
        if (!isInSale())
            return "id: " + id + " name: " + name + " price=" + price;
        Sale sale = SaleAndDiscountCodeController.getInstance().getSaleById(saleId);
        LocalDateTime startTime = sale.getStartTime();
        if (startTime.isAfter(LocalDateTime.now())) {
            return "id: " + id + " name: " + name + " price=" + price;
        }
        return "id: " + id + " name: " + name + " price=" + price + "  price w/ sale:" + getPriceWithSale();
    }

    public void addViewsBy(int count) {
        this.viewCount += count;
    }

    public void addTimesBoughtBy(int count) {
        this.timesBought += count;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime.toString();
    }
}
