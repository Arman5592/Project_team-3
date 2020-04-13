public class MainMenu extends Menu{
    private static MainMenu mainMenu;
    private int optionCount = 4;
    private MainMenu(){ }

    public static MainMenu getInstance(){
        if(mainMenu==null)
            mainMenu = new MainMenu();
        return mainMenu;
    }
    @Override
    public void show(){
        System.out.println("1-Login");
        System.out.println("2-Register");
        System.out.println("3-Shop");
        System.out.println("4-exit");
    }

    @Override
    public String toString(){
        return "1-Login\n2-Register\n3-Shop\n4-exit";
    }
    @Override
    public void execute(String command){
        if(!Controller.getInstance().isAValidCommand(command)){
            System.out.println("Invalid command. Try again.");
            return;
        }
        int choice = Integer.parseInt(command);

        if(choice==1){

        }
        else if(choice==2){

        }
        else if(choice==3){

        }
        else{
            System.out.println("Invalid command. Try again.");
        }
    }

}
