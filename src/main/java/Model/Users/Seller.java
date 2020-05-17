package Model.Users;

import Controller.Database;
import Controller.ItemAndCategoryController;
import Model.Item;
import Model.Logs.SaleLog;
import Model.Sale;

import java.util.ArrayList;

public class Seller extends User {
    private String companyName;
    private ArrayList<SaleLog> sellLogs;
    private ArrayList<String> soldItemsId;
    private ArrayList<String> allItemsId;
    private ArrayList<String> allSaleId;
    private boolean valid;
    private double money;

    public Seller(double money, String username, String password, String name, String lastName, String email, String number, String companyName) {
        super(username, password, name, lastName, email, number, "Seller");
        this.companyName = companyName;
        sellLogs = new ArrayList<>();
        soldItemsId = new ArrayList<>();
        allItemsId = new ArrayList<>();
        allSaleId = new ArrayList<>();
        valid = false;
        this.money = money;
    }

    @Override
    public String getPersonalInfo() {
        String response = "";
        response += "Seller\n";
        response += "Name: " + getName() + "\n";
        response += "Surname: " + getLastName() + "\n";
        response += "Email: " + getEmail() + "\n";
        response += "Number: " + getNumber() + "\n";
        response += "Company: " + companyName + "\n";
        return response;
    }

    public Boolean getValid(){
        return valid;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void addSaleLog(SaleLog saleLog) {
        sellLogs.add(saleLog);
        money+=saleLog.getPrice();
    }

    public void addItemID(String id) {
        allItemsId.add(id);
    }

    public void deleteItem(String itemId){
        if(!this.hasItem(itemId)) return;
        allItemsId.remove(itemId);
    }

    public void delete(){
        for (String id : allItemsId) {
            Item item=ItemAndCategoryController.getInstance().getItemById(id);
            if(item==null)
                continue;
            item.delete();
            Database.getInstance().deleteItem(item);
        }
    }

    public boolean hasItem(String id) {
        return allItemsId.contains(id);
    }

    public void addAllSaleId(String id) {
        if(hasSale(id)==true) return;
        allSaleId.add(id);
    }

    public boolean hasSale(String id) {
        return allSaleId.contains(id);
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return this.money;
    }

    public void validate() {
        this.valid = true;
    }

    public String getSaleLogsString(){
        String ans="ID          buyer name        count         price\n";
        int count=1;
        for(SaleLog saleLog:sellLogs){
            ans +=count+"-";
            ans += saleLog.toSimpleString();
            ans += "\n";
            count++;
        }
        return ans;
    }

    public String getAllItemsString(){
        String ans="ID         name        price\n";
        for(String id:allItemsId){
            Item item=ItemAndCategoryController.getInstance().getItemById(id);
            if(item==null){
                return "Error:";
            }
            ans+= item.toSimpleString();
            ans+="\n";
        }
        return ans;
    }

    public ArrayList<String> getAllItemsId() {
        return allItemsId;
    }
}