package View.Menus;

public class AdminMenu extends UserMenu {
    private static AdminMenu adminMenu;
    private int optionCount = 9;
    private AdminMenu(){ }

    public static AdminMenu getInstance(){
        if(adminMenu==null)
            adminMenu = new AdminMenu();
        return adminMenu;
    }


    @Override
    public void show(){
        help();
        String command = View.read.nextLine();
        execute(command);
    }

    @Override
    public void execute(String command){
       if(command.equals("1")) {
           AddAdminAccount();
       }
    }

    @Override
    public void help(){
    System.out.println("1-add admin account");
    }


    private void seeAllRequests(){
        ///set current menu to request menu
    }

    private void AddAdminAccount(){
       registerAdmin();
    }

    private void showAllSales(){

    }

    private void addSpecialSale(){

    }

    private void viewUser() {

    }

    public void deleteUser(){

    }

    public void showAllCategories(){
        //set current menu to category menu
    }

    public void addCategory(){

    }

    public void editCategory(){

    }

    public void removeCategory(){

    }

    public void removeProducts(){

    }

    public void createDiscountCode(){

    }

    public void viewAllDiscountCodes(){

    }

    public void viewOneDiscountCode(){

    }

    public void editDiscountCode(String discountID){

    }

    public void removeDiscountCode(){

    }

    public void manageAllProducts(){

    }

    public void manageRequests(){

    }

    public void requestDetails(){

    }

    public void manageCategories(){

    }



    public void removeProduct(){

    }




}
