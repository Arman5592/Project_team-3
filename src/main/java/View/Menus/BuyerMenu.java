package View.Menus;

import ControllerTest.CartController;
import ControllerTest.ItemAndCategoryController;
import ControllerTest.UserController;
import Model.Users.Buyer;
import Model.Users.User;
import View.Menus.ShopMenu.ShopMenu;

import java.util.regex.Matcher;

public class BuyerMenu extends UserMenu {
    private static BuyerMenu buyerMenu;
    private BuyerMenu(){ }

    public static BuyerMenu getInstance(){
        if(buyerMenu==null)
            buyerMenu = new BuyerMenu();
        return buyerMenu;
    }

    @Override
    public void run(){
        System.out.println(View.ANSI_BLUE+"You are in the Buyer menu."+View.ANSI_RESET);
        String command = View.read.nextLine();
        execute(command);
    }

    @Override
    public void execute(String command) {
        Matcher matcher;
        if(command.equals("logout")){
            logout();
            View.setCurrentMenu(MainMenu.getInstance());
        }
        if(command.equals("offs")){
            View.previousMenu = BuyerMenu.getInstance();
            View.setCurrentMenu(DiscountsMenu.getInstance());
        }
        if(command.equals("view personal info")){
            viewPersonalInfo();
        }
        matcher = View.getMatcher("edit (\\S+)", command);
        if (matcher.matches()) {
            editPersonalInfo(matcher.group(1));
        } else if(command.equals("products")){
            View.previousMenu = BuyerMenu.getInstance();
            View.setCurrentMenu(ShopMenu.getInstance());
        } else if(command.equals("view cart")){
            View.previousMenu = BuyerMenu.getInstance();
            View.setCurrentMenu(CartMenu.getInstance());
        }/*
        else if(command.equals("purchase")){
            purchase();//
        }*/
        else if(command.equals("view orders")){
            viewOrders();
        }
        else if(command.startsWith("show order ")){
            //
        }
        else if(command.startsWith("rate ")){
          matcher=View.getMatcher("rate (\\S+) ([1-2-3-4-5])",command);
          if(matcher.matches()){
              rate(matcher.group(1),Integer.parseInt(matcher.group(2)));
          }
        }
        else if(command.equals("view balance")){
            viewBalance();
        }
        else if(command.equals("view discount codes")){
            viewDiscountCodes();
        }
        else if(command.equals("back")){
            View.setCurrentMenu(MainMenu.getInstance());
        }
        else if(command.equals("help")){
            help();
        }
        /*else if(command.startsWith("view ")){
            matcher=View.getMatcher("view (\\S+)",command);
            if(matcher.matches()){
                viewItem(matcher.group(1));
            }
        }*/
        else{
            System.out.println(View.ANSI_RED+"Invalid command."+View.ANSI_RESET);
        }
    }



    @Override
    public void help(){
        System.out.println(View.ANSI_BLUE+"You are in the Buyer menu.\nType your command in one of these formats:"+View.ANSI_RESET);
        System.out.println("view personal info");
        System.out.println("edit [field]");
        System.out.println("view cart");
        System.out.println("view orders");
        System.out.println("show order [orderId]");
        System.out.println("rate [productId] [1-5]");
        System.out.println("view balance");
        System.out.println("view discount codes");
        System.out.println("logout");
        System.out.println("back");
    }

    private void showTotalPrice(){
        User user = UserController.getInstance().getCurrentOnlineUser();
        System.out.print(View.ANSI_BLUE+"Total Price:"+View.ANSI_RESET);
        System.out.println(CartController.getInstance().getCurrentShoppingCart().getCartPriceWithoutDiscountCode());
    }

    private void purchase(){
        View.setCurrentMenu(PurchaseMenu.getInstance());
    }

    private void viewOrders(){
        Buyer buyer = (Buyer)UserController.getInstance().getCurrentOnlineUser();
        System.out.println(buyer.getBuyLogsString());
    }

    private void viewBalance(){
        System.out.print("Your current balance is:");
        System.out.println(View.ANSI_BLUE+UserController.getInstance().currentOnlineUserBalance()+View.ANSI_RESET);
    }


    private void viewDiscountCodes(){
        Buyer buyer = (Buyer)UserController.getInstance().getCurrentOnlineUser();
        System.out.println(buyer.getDiscountCodes());
    }

    private void rate(String itemid,int score){
       System.out.println(ItemAndCategoryController.getInstance().rate(score,itemid));
    }

}
