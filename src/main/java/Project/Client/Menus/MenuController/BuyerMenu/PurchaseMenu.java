package Project.Client.Menus.MenuController.BuyerMenu;

import Project.Client.MakeRequest;
import Project.Client.Model.Cart;


import Project.Client.Menus.MusicManager;
import Project.Client.Menus.SceneSwitcher;
import Project.Client.CLI.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PurchaseMenu {
   @FXML TextField address;
   @FXML Label priceLabel;
   @FXML ListView itemListView;
   @FXML ChoiceBox discounts;
   private Boolean discountIsValid=false;
   @FXML private AnchorPane pane;
   @FXML
   public void initialize()  {
      View.setFonts(pane);
      MusicManager.getInstance().setSongName("first.wav");
      discounts.getItems().addAll(MakeRequest.makeGetBuyerDiscountCodesRequest());
      discounts.getItems().add("NONE");
      discounts.setValue("NONE");
      priceLabel.setText("price="+String.valueOf(MakeRequest.getCartPriceWithoutDiscount()));
      itemListView.getItems().addAll(Cart.getInstance().toString());
      update();
   }

   public void update(){
   if(discountIsValid)
      priceLabel.setText("cart price before discount="+MakeRequest.getCartPriceWithoutDiscount()+"\ncart price after discount="+MakeRequest.makeGetCartPriceWithDiscountCode(discounts.getValue().toString()));
   }

   public void discountChange(ActionEvent actionEvent) {
      if(discounts.getValue().toString().equals("NONE")){
         discountIsValid=false;
      }else{
         discountIsValid=true;
      }
   }

   public void back(MouseEvent mouseEvent){
      SceneSwitcher.getInstance().setSceneTo("CartMenu");
   }

   public void buy(MouseEvent mouseEvent){
      if(validateAddress()==false) {
         MusicManager.getInstance().playSound("notify");
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setContentText("enter a valid address");
         alert.showAndWait();
         return;
      }
      String message="";
      if(discountIsValid) {
         message=MakeRequest.buyCart(discounts.getValue().toString(),address.getText());
      }else {
         message=MakeRequest.buyCart(null,address.getText());
      }
      MusicManager.getInstance().playSound("notify");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText(message);
      alert.showAndWait();
      if(message.startsWith("Successful")) SceneSwitcher.getInstance().back();
   }

   private Boolean validateAddress(){
      try{
         String text=address.getText();
         if((text.isEmpty())||(text.length()<5)) return false;
         return true;
      }catch (Exception e){
         return false;
      }
   }

   public String getDiscountCode(){
      return discounts.getValue().toString().substring(16,21);
   }

}
