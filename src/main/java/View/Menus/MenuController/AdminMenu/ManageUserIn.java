package View.Menus.MenuController.AdminMenu;

import View.Menus.MusicManager;
import View.Menus.SceneSwitcher;
import Controller.UserController;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;

public class ManageUserIn {
   private static String username;

   public static void setUsername(String name) {
      username = name;
   }

   public void initialize(){MusicManager.getInstance().setSongName("first.wav");}

   public void deleteUser(MouseEvent mouseEvent) {
      MusicManager.getInstance().playSound("Button");
      String message=UserController.getInstance().deleteUser(username);
      if(message.startsWith("Error")) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText(message);
         alert.showAndWait();
         return;
      }
      if(message.startsWith("Successful")){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setContentText(message);
         alert.showAndWait();
         back(null);
      }

   }

   public void back(MouseEvent mouseEvent) {
      MusicManager.getInstance().playSound("Button");
      SceneSwitcher.getInstance().setSceneTo("ManageUsers");
   }

   public void viewUser(MouseEvent mouseEvent) {
      MusicManager.getInstance().playSound("Button");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText(UserController.getInstance().viewPersonalInfo(username));
      alert.show();
   }
}
