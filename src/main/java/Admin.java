public class Admin extends User {

    public Admin(String username, String password, String name, String lastName, String email, String type, String number) {
        super(username, password, name, lastName, email, type, number);
    }

    public void addAdminAccount(String username, String password, String name, String lastName, String email, String type, String number) {

    }

    public void deleteUser(String username) {

    }

    public String showCategory(String categoryName) {
           return "salam";
    }

    // public void addCategory(  ta vaghti category neveshte beshe  ) {
    //   }

}
