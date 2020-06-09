package Controller.MenuController.LoginRegisterController;

import Controller.SceneSwitcher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.print.DocFlavor;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AdminRegisterController {
    public ComboBox roleChooser;
    public TextField usernameTextField;
    public TextField firstNameTextField;
    public TextField surnameTextField;
    public TextField emailTextField;
    public TextField phoneNumberTextFiled;
    public PasswordField passwordTextField;


    public void initialize(){
        roleChooser.getItems().add("Admin");
        roleChooser.getItems().add("Seller");
        roleChooser.getItems().add("Buyer");
        roleChooser.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String value=(String) roleChooser.getValue();
                if(value.equals("Seller")){
                    SceneSwitcher.getInstance().setSceneTo("SellerRegister");
                }
                else if(value.equals("Buyer")){
                    SceneSwitcher.getInstance().setSceneTo("BuyerRegister");
                }
            }
        });
    }











    public void comboBoxPressed(MouseEvent mouseEvent) {
        roleChooser.getSelectionModel().select("Admin");
    }



}
