package Project.Client.Model.Users;

import Server.Controller.Database;

import java.util.HashMap;

public abstract class User {

    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String number;
    private String type;
    private HashMap<String,String> allRequests=new HashMap<>();

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }



    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public abstract String getPersonalInfo();

    public String getAllRequests() {
        return allRequests.toString().replace("{","").replace("}","").replace(",","\n");
    }

    public HashMap<String,String> getReqMap() {return allRequests;}

    public void setAllRequests(HashMap<String, String> allRequests) {
        this.allRequests = allRequests;
    }
}