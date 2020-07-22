package Project.Client.Menus.MenuController;

import Project.Client.CLI.ItemMenu;
import Project.Client.CLI.View;
import Project.Client.Client;
import Project.Client.MakeRequest;
import Project.Client.Menus.MusicManager;
import Project.Client.Menus.SceneSwitcher;
import Project.Client.Model.Auction;
import Project.Client.Model.Item;
import Project.Client.Model.Users.Seller;
import Project.Client.Model.Users.User;
import Project.Client.ObjectMapper;
import Server.Controller.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class AuctionMenu {
    private static Auction auction;

    @FXML private AnchorPane pane;
    @FXML private TextArea chatBox;
    @FXML private ListView listView;
    @FXML private Label errorLabel;
    @FXML private Label bidLabel;
    @FXML private TextArea description;
    @FXML private Label nameLabel;
    @FXML private Label brandLabel;

    @FXML private TextField bidValue;

    protected static void setAuction(Auction newAuction){
        auction = newAuction;
    }

    @FXML  private void back() {
        MusicManager.getInstance().playSound("Button");
        SceneSwitcher.getInstance().setSceneTo("AllAuctionsMenu");
    }

    @FXML private void initialize(){
        View.setFonts(pane);
        initializeChat();
        errorLabel.setTextFill(Color.RED);
        Item item= MakeRequest.getItem(auction.getItemID());
        nameLabel.setText(item.getName()+"        Sold by:  "+item.getSellerName());
        description.setText(item.getDescription());
        brandLabel.setText(item.getBrand());
        bidLabel.setText(Double.toString(auction.getHighestBid()));
        //bayad biaim menush ro update konim

        //ghabeliat chat beine hamme (login shode bashe!)

        //ghabeliat bid baraye buyer ha
    }

    @FXML private void bid(){
        String bidVal = bidValue.getText();
        if(!isValidPrice(bidVal)){
            errorLabel.setText("Invalid bid value.");
            return;
        }
        if((Client.getInstance().getToken()==null)||(MakeRequest.isTokenValid()==false)){
            errorLabel.setText("You must be logged in to send a message");
            return;
        }
        if(!MakeRequest.makeGetUserRequest().type.equals("Buyer")){
            errorLabel.setText("Only customers can bid!");
            return;
        }
        errorLabel.setText(MakeRequest.addBid(auction.getId(),bidVal));
    }

    private boolean isValidPrice(String price){
        double d = -1;
        try {
            d = Double.parseDouble(price);
        }catch (Exception e){
            return false;
        }
        return d > 0;
    }

    @FXML private void refresh(){
        ArrayList<Auction> allAuctions = ObjectMapper.jsonToAuction(MakeRequest.getAllAuctions());
        String id = auction.getId();
        Auction newAuc = getById(id,allAuctions);
        if(newAuc==null){
            SceneSwitcher.getInstance().setSceneTo("AllAuctionsMenu");
        }else{
            auction = newAuc;
            initialize();
        }
    }

    private Auction getById(String id, ArrayList<Auction> allAuctions){
        for(Auction auction:allAuctions){
            if(auction.getId().equals(id)){
                return auction;
            }
        }
        return null;
    }

    private void initializeChat(){
        listView.getItems().clear();
        chatBox.clear();
        HashMap<String,String> chat = auction.getChat();
        for(String sender:chat.keySet()){
            listView.getItems().add(sender+": "+chat.get(sender));
        }
    }

    @FXML private void clear(){
        chatBox.clear();
    }

    @FXML private void send(){
        if((Client.getInstance().getToken()==null)||(MakeRequest.isTokenValid()==false)){
            errorLabel.setText("You must be logged in to send a message");
            return;
        }
        if(chatBox.getText().isEmpty()){
            errorLabel.setText("Type something to send");
            return;
        }
        MakeRequest.addAuctionChat(auction.getId(),chatBox.getText());
        String message = chatBox.getText();
        initializeChat();
        listView.getItems().add("You just said: "+message);
        errorLabel.setText("Message sent!");
    }

    @FXML private void learnMore(){
        ItemMenuController.setItemID(auction.getItemID());
        SceneSwitcher.getInstance().saveScene("AuctionMenu");
        SceneSwitcher.getInstance().setSceneTo("ItemMenu");
    }

}
