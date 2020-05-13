package Model.Users;

import Controller.SaleAndDiscountCodeController;
import Model.Cart;
import Model.Logs.BuyLog;

import java.util.ArrayList;

public class Buyer extends User {

    private double money;
    private ArrayList<BuyLog> buyLogs;
    private Cart cart;
    private ArrayList<String>allDiscounts;
    public Buyer(double money,String username, String password, String name, String lastName, String email, String number) {
        super(username,password,name,lastName,email,number,"Buyer");
        this.money=money;
        buyLogs=new ArrayList<>();
        allDiscounts=new ArrayList<>();
        cart=new Cart();
    }

    @Override
    public  String getPersonalInfo(){
        String response="";
        response+="You are a buyer.\n";
        response+="Name: "+getName()+"\n";
        response+="Surname: "+getLastName()+"\n";
        response+="Email: "+getEmail()+"\n";
        response+="Number: "+getNumber()+"\n";
        return response;
    }

    public String getBuyLogsString(){
        String ans = "";
        for(BuyLog buyLog:buyLogs){
            ans += buyLog.toString();
        }
        return ans;
    }

    public void addBuyLog(BuyLog buyLog){buyLogs.add(buyLog);}

    public void addDiscount(String discountId){
        this.allDiscounts.add(discountId);
    }

    public void removeDiscount(String discountID){
        if(this.allDiscounts.contains(discountID)) this.allDiscounts.remove(discountID);
    }

    public boolean hasDiscountID(String id){
        return  this.allDiscounts.contains(id);
    }

    public String getDiscountById(String id){
        if(this.hasDiscountID(id)){
            for(String iterateID:allDiscounts){
                if(iterateID.equals(id)) return iterateID;
            }

        }
        return null;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDiscountCodes() {
        String ans = "";
        for(String id:allDiscounts){
            ans += SaleAndDiscountCodeController.getInstance().getDiscountCodeById(id).toString();
            ans += "\n";
        }
        return ans;
    }

    public BuyLog getBuyLogByID(String ID){
        for(BuyLog buyLog:buyLogs){
            if(buyLog.getId().equals(ID)) return buyLog;
        }
        return null;
    }

}
