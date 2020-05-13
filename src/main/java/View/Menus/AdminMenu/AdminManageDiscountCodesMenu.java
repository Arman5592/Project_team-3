package View.Menus.AdminMenu;

import Controller.Database;
import Controller.SaleAndDiscountCodeController;
import View.Menus.LoginRegisterMenu;
import View.Menus.MainMenu;
import View.Menus.UserMenu;
import View.Menus.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;

public class AdminManageDiscountCodesMenu extends UserMenu {
    private static AdminManageDiscountCodesMenu adminManageDiscountCodesMenu;

    private AdminManageDiscountCodesMenu() {
    }

    public static AdminManageDiscountCodesMenu getInstance() {
        if (adminManageDiscountCodesMenu == null)
            adminManageDiscountCodesMenu = new AdminManageDiscountCodesMenu();
        return adminManageDiscountCodesMenu;
    }

    public void run() {
        help();
        String command = View.getRead().nextLine();
        execute(command);
    }

    @Override
    public void execute(String command) {
        Matcher matcher;

        matcher = View.getMatcher("view discount code (\\S+)", command);
        if (matcher.matches()) {
            viewDiscountCode(matcher.group(1));
            return;
        }
        matcher = View.getMatcher("edit discount code (\\S+)", command);
        if (matcher.matches()) {
            editDiscountCode(matcher.group(1));
            return;
        }
        matcher = View.getMatcher("remove discount code (\\S+)", command);
        if (matcher.matches()) {
            deleteDiscountCode(matcher.group(1));
            return;
        } else if (command.equals("view discount codes")) {
            viewAllDiscountCodes();
            View.setCurrentMenu(AdminManageDiscountCodesMenu.getInstance());
            return;
        }
        if (command.equals("help")) {
            help();
            return;
        }
        if (command.equals("back")) {
            View.setCurrentMenu(AdminMenu.getInstance());
            return;
        }
        if(command.equals("logout")){
            LoginRegisterMenu.getInstance().setPreviousMenu(MainMenu.getInstance());
            LoginRegisterMenu.getInstance().logout();
            return;
        }

        System.out.println(View.ANSI_RED + "Invalid command." + View.ANSI_RESET);

    }

    public void help() {
        System.out.println(View.ANSI_WHITE + "Enter your command in the following formats or type back to go to the admin menu." + View.ANSI_RESET);
        System.out.println("view discount code");         //done but need test
        System.out.println("view discount code [id]");   //done
        System.out.println("edit discount code [id]");
        System.out.println("remove discount code [id]");
    }

    private void viewDiscountCode(String id) {
        System.out.println(SaleAndDiscountCodeController.getInstance().printDiscount(id));
    }

    private void editDiscountCode(String discountID) {
        if (SaleAndDiscountCodeController.getInstance().isThereDiscountCodeWithId(discountID) == false) {
            System.out.println("Error: invalid ID");
            return;
        }
        System.out.println("Enter -edit date- if you wish to change the date.\nEnter -edit offpercent- if you wish to change the off percentage.");
        String command = View.getRead().nextLine();
        if (command.equals("edit date")) {
            int day = readNumber(32, "Please enter new expiration date:");
            int month = readNumber(13, "Please enter new expiration month:");
            int year = readNumber(2025, "Please enter new expiration year:");
            Date date = new Date(year - 1900, month - 1, day);
            System.out.println(SaleAndDiscountCodeController.getInstance().editDiscountCodeEndTime(discountID, date));
            return;
        } else if (command.equals("edit offpercent")) {
            int percentage = readNumber(101, "please enter new discount percentage:");
            System.out.println(SaleAndDiscountCodeController.getInstance().editDiscountCodePercentage(discountID, percentage));
            return;
        }
        System.out.println(View.ANSI_RED+"Invalid command."+View.ANSI_RESET);
    }

    private void deleteDiscountCode(String id) {
        System.out.println(SaleAndDiscountCodeController.getInstance().deleteDiscountCode(id));
    }

    private void viewAllDiscountCodes() {
        ArrayList<String> allDiscountCodes = Database.getInstance().printFolderContent("Discount Codes");
        printList(allDiscountCodes);
    }
}
