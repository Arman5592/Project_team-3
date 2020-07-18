package Project.Client.Menus.MenuController.BuyerMenu;

import Project.Client.MakeRequest;
import Project.Client.Model.Users.Buyer;
import Project.Client.Menus.MenuController.ViewRequestUser;
import Project.Client.Menus.MusicManager;
import Project.Client.Menus.SceneSwitcher;
import Project.Client.CLI.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.MalformedURLException;

public class BuyerMenuController {


    @FXML public ImageView userImage;
    @FXML private Label personalInfo;
    @FXML private AnchorPane pane;
    public void initialize(){
        MakeRequest.makeUpdateDateAndTimeRequest();
        View.setFonts(pane);
        MusicManager.getInstance().setSongName("first.wav");
       if(MakeRequest.isTokenValid() && MakeRequest.makeGetUserRequest() instanceof Buyer) {
           Image image=MakeRequest.getImageFromServer(MakeRequest.makeGetUserRequest().getUsername(),"user");
           userImage.setImage(image);
//           String path = MakeRequest.makeUserImagePathRequest();
//           File file = new File(path);
//           try {
//               userImage.setImage(new Image(String.valueOf(file.toURI().toURL())));
//           } catch (MalformedURLException e) {
//               e.printStackTrace();
//           }
           personalInfoUpdate();
       }
    }

    public void personalInfoUpdate(){
        Buyer buyer=(Buyer) MakeRequest.makeGetUserRequest();
        personalInfo.setText(buyer.getPersonalInfo());
    }

    @FXML
    private void back(ActionEvent actionEvent)
    {
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().back();
    }
    @FXML
    private void logout(ActionEvent actionEvent) {
        MusicManager.getInstance().playSound("Button");
        MakeRequest.makeLogoutRequest();
        SceneSwitcher.getInstance().clearRecentScene();
        SceneSwitcher.getInstance().setSceneTo("MainMenu");
    }

    @FXML
    private void viewOrders()
    {
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().setSceneTo("BuyLog");
    }

    @FXML
    private void viewDiscountCodes(){
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().setSceneTo("DiscountsMenu");
    }

    @FXML
    private void viewShop(){
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().saveScene("BuyerMenu");
        SceneSwitcher.getInstance().setSceneTo("ShopMenu");
    }

    @FXML
    private void viewCart(){
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().saveScene("BuyerMenu");
        SceneSwitcher.getInstance().setSceneTo("CartMenu",620,427);
    }

    public void viewEditPersonalInfo() {
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().saveScene("BuyerMenu");
        SceneSwitcher.getInstance().setSceneTo("BuyerEditPersonalInfo");
    }

    public void viewRequests(ActionEvent actionEvent) {
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().saveScene("BuyerMenu");
        ViewRequestUser.setUsername(MakeRequest.makeGetUserRequest().getUsername());
        SceneSwitcher.getInstance().setSceneTo("ViewRequests");
    }
}


