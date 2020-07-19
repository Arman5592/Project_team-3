package Project.Client;

import Project.Client.Model.Comment;
import Project.Client.Model.Item;
import Project.Client.Model.Logs.SaleLog;
import Project.Client.Model.Sale;
import Project.Client.Model.Users.Admin;
import Project.Client.Model.Users.Buyer;
import Project.Client.Model.Users.Seller;
import Project.Client.Model.Users.User;
import Project.Client.Model.Category;
import Project.Client.Model.Logs.BuyLog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectMapper {

   public static User jsonToUser(JsonObject json){
      if(getJsonStringField(json,"type").equals("Admin")){
         return jsonToAdmin(json);
      }else if(getJsonStringField(json,"type").equals("Buyer")){
        return jsonToBuyer(json);
      }else if(getJsonStringField(json,"type").equals("Seller")){
       return jsonToSeller(json);
      }
      return null;
   }

   public static Admin jsonToAdmin(JsonObject json){
      String name = getJsonStringField(json,"name");
      String lastName = getJsonStringField(json,"lastName");
      String password = getJsonStringField(json,"password");
      String username = getJsonStringField(json,"username");
      String email = getJsonStringField(json,"email");
      String number = getJsonStringField(json,"number");
      return new Admin(username,password,name,lastName,email,number);
   }

   public static Seller jsonToSeller(JsonObject json){
      String name = getJsonStringField(json,"name");
      String lastName = getJsonStringField(json,"lastName");
      String password = getJsonStringField(json,"password");
      String username = getJsonStringField(json,"username");
      String email = getJsonStringField(json,"email");
      String number = getJsonStringField(json,"number");
      double money= json.get("money").getAsDouble();
      String company=getJsonStringField(json,"companyName");
      return new Seller(money,username,password,name,lastName,email,number,company);
   }

   public static Buyer jsonToBuyer(JsonObject json){
      String name = getJsonStringField(json,"name");
      String lastName = getJsonStringField(json,"lastName");
      String password = getJsonStringField(json,"password");
      String username = getJsonStringField(json,"username");
      String email = getJsonStringField(json,"email");
      String number = getJsonStringField(json,"number");
      double money= json.get("money").getAsDouble();
      return new Buyer(money,username,password,name,lastName,email,number);
   }

   public static Item jsonToItem(JsonObject json){
      System.out.println(json.toString()+"ine");
      String name=getJsonStringField(json,"name");
      String brand=getJsonStringField(json,"brand");
      double price=json.get("price").getAsDouble();
      String description=getJsonStringField(json,"description");
      String productId=getJsonStringField(json,"id");
      double rating=MakeRequest.getItemRating(productId);
      String sellerName=getJsonStringField(json,"sellerName");
      String imageName=getJsonStringField(json,"imageName");
      String categoryName=getJsonStringField(json,"categoryName");
      int inStock=json.get("inStock").getAsInt();
      int viewCount=json.get("viewCount").getAsInt();
      int timesBought=json.get("timesBought").getAsInt();
      Gson gson = new Gson();
      HashMap attributes=gson.fromJson(json.get("attributes"), HashMap.class);
      ArrayList<String> allBuyers=gson.fromJson(json.get("buyerUserName"), ArrayList.class);
      ArrayList<Comment> allComments=new ArrayList<>();
      JsonArray comments=json.getAsJsonArray("allComments");
      for (JsonElement comment : comments) {
         allComments.add(jsonToComment(comment.getAsJsonObject()));
      }
      String saleId=getJsonStringField(json,"saleId");
      return new Item(productId,description,name,brand,timesBought,price,inStock,viewCount,attributes,allBuyers,imageName,sellerName,categoryName,rating,allComments,saleId);
   }

   public static Comment jsonToComment(JsonObject json){
      String username=getJsonStringField(json,"username");
      String text=getJsonStringField(json,"text");
      Boolean hasBought=json.get("hasBought").getAsBoolean();
      ArrayList<Comment> allReplies=new ArrayList<>();
      Gson gson = new Gson();
      JsonArray replies=json.getAsJsonArray("allReplies");
      for (JsonElement reply : replies) {
         allReplies.add(jsonToComment(reply.getAsJsonObject()));
      }
      return new Comment(username,text,hasBought,allReplies);
   }

   public static Category jsonToCategory(JsonObject json){
        Gson gson = new Gson();
        String name=getJsonStringField(json,"name");
        String parent=getJsonStringField(json,"parent");
        ArrayList<String> allItemsID=gson.fromJson(json.get("allItemsID"), ArrayList.class);
        ArrayList<String> attributes=gson.fromJson(json.get("attributes"), ArrayList.class);
        ArrayList<String> subCategories=gson.fromJson(json.get("subCategories"), ArrayList.class);
        return new Category(name,parent,allItemsID,attributes,subCategories);
   }

   public static Sale jsonToSale(JsonObject json){
      int percent=json.get("offPercentage").getAsInt();
      String start=getJsonStringField(json,"startTime");
      String end=getJsonStringField(json,"endTime");
      return new Sale(percent,start,end);
   }

   public static SaleLog jsonToSaleLog(JsonObject json){
      String time=getJsonStringField(json,"time");
      double price=json.get("price").getAsDouble();
      String itemId=getJsonStringField(json,"itemId");
      String buyerName=getJsonStringField(json,"buyerName");
      int count=json.get("count").getAsInt();
      String sellerUsername=getJsonStringField(json,"sellerUsername");
      return new SaleLog(time,price,itemId,buyerName,count,sellerUsername);
   }

   public static String getJsonStringField(JsonObject json,String field){
      return json.get(field).toString().replace("\"","");
   }
}
