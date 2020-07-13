package Project.Client.Model.Users;

import Server.Controller.Database;
import Server.Controller.ItemAndCategoryController;
import Server.Model.Item;
import Server.Model.Logs.SaleLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends User {
    private String companyName;
    private ArrayList<SaleLog> sellLogs;
    private ArrayList<String> allItemsId;
    private boolean valid;
    private double money;



    @Override
    public String getPersonalInfo() {
        String response = "";
        response += "Seller\n";
        response += "Name: " + getName() + "\n";
        response += "Surname: " + getLastName() + "\n";
        response += "Email: " + getEmail() + "\n";
        response += "Number: " + getNumber() + "\n";
        response += "money: " + getMoney() + "\n";
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
            Item item= ItemAndCategoryController.getInstance().getItemById(id);
            if(item==null)
                continue;
            item.delete();
            Database.getInstance().deleteItem(item);
        }
    }

    public boolean hasItem(String id) {
        return allItemsId.contains(id);
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
        String ans="ID     buyer name     count     price\n";
        int count=1;
        for(SaleLog saleLog:sellLogs){
            ans +=count+"-";
            ans += saleLog.toSimpleString();
            ans += "\n";
            count++;
        }
        return ans;
    }

    public ArrayList<SaleLog> getSellLogs() {
        return sellLogs;
    }

    public ArrayList<String> getAllItemsId() {
        return allItemsId;
    }


}