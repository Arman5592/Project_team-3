package Controller;

import Model.DiscountCode;
import Model.DiscountCodeTest;
import Model.Requests.Request;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatabaseTest {

    public void addDiscountCode(){
        ArrayList<String> validUsers=new ArrayList<>();
        String startTime="25-02-2014 22:30";
        String endTime="27-02-2020 22:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime dateTime1 = LocalDateTime.parse(endTime, formatter);
        SaleAndDiscountCodeController.getInstance().addDiscountCode(20,dateTime1,dateTime,validUsers
                ,6,50);
    }

    public void addRequest(){
        UserController.getInstance().registerSeller(500,"Ali","alireza79",
                "reza","pishro","alireza@gmail.com","33824264","benz");
    }

    @Test
    public void getInstance() {
        Database database=Database.getInstance();
        assertNotNull(database);
    }

    @Test
    public void initiate() {
        Database.getInstance().initiate();
    }

    @Test
    public void printFolderContent() {
        addDiscountCode();
        addRequest();
        Database.getInstance().printFolderContent("Requests");
        Database.getInstance().printFolderContent("DiscountCodes");
        ArrayList<Request>allRequests=RequestController.getInstance().getAllRequestFromDataBase();
        ArrayList<DiscountCode> allDiscounts=SaleAndDiscountCodeController.getInstance().getAllDiscountCodesFromDataBase();
        for(DiscountCode discountCode:allDiscounts) Database.getInstance().deleteDiscountCode(discountCode);
        for(Request request:allRequests)Database.getInstance().deleteRequest(request);

    }
}