import Controller.Database;
import Controller.ItemAndCategoryController;
import View.Menus.ShopAndDiscountMenu.ShopMenu;
import View.Menus.View;

public class Main {
    public static void main (String[] args)  {
        Database.getInstance().initiate();
        ShopMenu.getInstance().setCurrentCategory(ItemAndCategoryController.getInstance().getBaseCategory().getName());
        View.run();
    }
}

//edit discount code
//edit category <= bade inke paiini anjam beshe mishe ino anjam dad
//   ||
//   \/
//**MOHEM **  item hameye attribute haye category dashte bashahd  //mirza <=== olaviat!
//   /\
//   ||
//moshahade sabeghe forosh              //mirza
//moshahade buy log     //mirza

//moshahede kharidaran yek mahsol
//selller menu kheili ridmane
//darkhast ezafeye sell

//eslah discount code
//didan discount code haye karbar
// adam tavanayi estefade az code takhfif bad az chand bar

//moshahede vizhegi haye mahsol //mirza
//filter bar asas in stock      //mirza
//filter bar asas esm           //mirza
//filter bar asas attribute     //mirza


//safhe haraj ride

//time: tabe'e deprecate shode ye kari beshe
//method e passtime tuye view => miad discountcode haye expire shodaro hazf mikone va besorate random be karbara code takhfif ba darsad random o ina mide
//age kharid balaye 1 milion bod ye code takhfif ashghal be karbar hedie dade beshe (5% ba saghfe ashghali dar hade chosmesghal)


//dar akhar: agge hich admini vojod nadare nafare aval bayad betone be onvane ADMIN sabtenam kone be mola
//dar akhar: bayad enumerate beshe status ha
