package View.Menus.MenuController.AdminMenu;

import Controller.CommercialController;
import View.Menus.MusicManager;
import View.Menus.SceneSwitcher;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class ManageCommercialIn {

   private static String requestId;

   public static void setRequestId(String requestId) {
      ManageCommercialIn.requestId = requestId;
   }

   public void initialize(){MusicManager.getInstance().setSongName("first.wav");}

   public void accept(MouseEvent mouseEvent) {
      MusicManager.getInstance().playSound("Button");
      String message = CommercialController.getInstance().acceptCommercial(requestId);
      showAlertBox(message);
      SceneSwitcher.getInstance().closeSecondStage();
      SceneSwitcher.getInstance().closeSecondStage();
      SceneSwitcher.getInstance().setSceneTo("ManageRequests");
   }

   public void decline(MouseEvent mouseEvent) {
      MusicManager.getInstance().playSound("Button");
      CommercialController.getInstance().declineCommercial(requestId);
      showAlertBox("Successful: request declined");
      SceneSwitcher.getInstance().closeSecondStage();
      SceneSwitcher.getInstance().closeSecondStage();
      SceneSwitcher.getInstance().setSceneTo("ManageRequests");
   }


   private void showAlertBox(String message) {
      MusicManager.getInstance().playSound("notify");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText(message);
      alert.showAndWait();
   }
}
