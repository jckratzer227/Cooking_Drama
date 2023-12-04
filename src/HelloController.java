/**********************************************************************
 * Displays screens for Shop section
 * Relies on shop.fxml, dishesforpurchase.fxml, upgradeshop.fxml
 * Creator: Kaycee Rigor
 * Modification date: 12-2-2023
 **********************************************************************/

package Cooking;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
//import Cooking.Player.getCookingCoins();
//import static Cooking.Player.cookingCoins;
//import static Cooking.Player.getCookingCoins;
import static Cooking.GameWindows.restaurant;
import static Cooking.GameWindows.window;
import static Cooking.ShopClassLogic.*;


public class HelloController{

    @FXML
    public Text FloorplanConfirmationText;
    @FXML
    public Text MenuConfirmationText;
    @FXML
    private Text cookingCoinsText;
    @FXML
    private Button GoToAvailableDishes;
    @FXML
    private Button GoToUpgrades;

    @FXML
    private Button GoBackHandler;

    @FXML
    private Button BackToMainMenuHandler;

    @FXML
    void initialize() {
        //cookingCoinsText.getText();
        if(ShopClassLogic.inventory.isEmpty()){
            Shop();
        }
        if (cookingCoinsText != null) {
            cookingCoinsText.setText("" + Player.getCookingCoins());
        }
    }

    public void buyItem(String string){
        Item item = findItem(string);
        if (item != null) {
                if (Player.getCookingCoins() >= item.getPrice())
                {
                    ShopClassLogic.buyItem(item);
                    cookingCoinsText.setText("" + Player.getCookingCoins());
                    if(item instanceOf Recipe){
                        MenuConfirmationText.setText("New recipe unlocked! Yippee!");
                    }
                    else{
                        FloorplanConfirmationText.setText("New floorplan unlocked! Yippee!");
                    }
                }
                else{
                    MenuConfirmationText.setText("Not enough funds");
                    FloorplanConfirmationText.setText("Not enough funds");
                }
            else{
                MenuConfirmationText.setText("Looks like we're out of stock");
                FloorplanConfirmationText.setText("Looks like we're out of stock");
            }
        }
    }

    /*******************************************************************
     * Shop Main Menu -> Recipe Shop
     ******************************************************************/
    @FXML // goes to available menu items for sale
    void RecipeShopMenu (MouseEvent mouseEvent) throws IOException {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dishesforpurchase.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("Menu Shop");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e ){
            System.out.println("something went wrong w fxml file");
        }
    }
    /*******************************************************************
     * Shop Main Menu -> Upgrades Shop
     ******************************************************************/
    @FXML // goes to available upgrade items for sale
    void UpgradeShopMenu (MouseEvent mouseEvent) throws IOException {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("upgradeshop.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("Upgrades Shop");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e ){
            System.out.println("something went wrong w fxml file");
        }
    }
    /*******************************************************************
     * Recipe/Upgrade Shop -> Shop Main Menu
     ******************************************************************/
    @FXML
    void GoBackToMenu(MouseEvent event) throws IOException {

        try {
            Stage newStage = (Stage) GoBackHandler.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shop.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
            newStage.setScene(scene);
            newStage.show();
            newStage.setTitle("The Shop!");

        } catch (IOException e) {
            System.out.println("something went wrong w fxml file");
        }
    }
    /*******************************************************************
     * Shop Main Menu -> Main Hub
     ******************************************************************/
   @FXML
    void GoBackToMainMenu(MouseEvent event) {
       //GameWindows.window.setScene(restaurant);
       GameWindows.restaurantView();
       //window.show();
    }
    /*******************************************************************
     * Below deals with purchasing of items, with a click on the image
     * the sprite's message should confirm whether or not the item has been successfully bought or not
     ******************************************************************/
    @FXML
    void BuyRetroChair(MouseEvent event) {
        Item retroChairs = findItem("Fancy Retro Dining Chairs");
        if (retroChairs != null) {
            buyItem(retroChairs);
            updateCookingCoinsText();
        }

    }

    @FXML
    void BuyWhiteTable(MouseEvent event) {
        Item whiteTable = findItem("White Tables");
        if (whiteTable != null) {
            buyItem(whiteTable);
            updateCookingCoinsText();
        }
    }

    @FXML
    void BuyChickenParm(MouseEvent event) {
        Item chickenParm = findItem("Chicken Parm");
        if (chickenParm != null) {
            buyItem(chickenParm);
            updateCookingCoinsText();
        }
    }

    @FXML
    void BuyMashedPotatoes(MouseEvent event) {
        Item mashedPotatoes = findItem("Rosemary Mashed Potatoes");
        if (mashedPotatoes != null) {
            buyItem(mashedPotatoes);
            updateCookingCoinsText();
        }
    }

    @FXML
    void BuyPaella(MouseEvent event) {
        Item paella = findItem("Seafood and Chicken Paella");
        if (paella != null) {
            buyItem(paella);
            updateCookingCoinsText();
        }
    }

    @FXML
    void BuyRangoons(MouseEvent event) {
        Item crabRangoons = findItem("Crab Rangoons");
        if (crabRangoons != null) {
            buyItem(crabRangoons);
            updateCookingCoinsText();
        }
    }

    void Shop() {

        //List<Item> inventory = new ArrayList<>();
        //ArrayList userInventory = new ArrayList<>();

        // for dishes
        ArrayList<String> recipe1Ingredients = new ArrayList<>();
        recipe1Ingredients.add("Chicken");
        recipe1Ingredients.add("Seafood");
        recipe1Ingredients.add("Butter");

        Recipe paella = new Recipe("Seafood and Chicken Paella", 240, recipe1Ingredients, 60, 350);

        ArrayList<String> recipe2Ingredients = new ArrayList<>();
        recipe2Ingredients.add("Potatoes");
        recipe2Ingredients.add("Seasoning");
        recipe2Ingredients.add("Butter");

        Recipe mashedPotatoes = new Recipe("Rosemary Mashed Potatoes", 230, recipe2Ingredients, 35, 210);

        ArrayList<String> recipe3Ingredients = new ArrayList<>();
        recipe3Ingredients.add("Chicken");
        recipe3Ingredients.add("Cheese");
        recipe3Ingredients.add("Sauce");

        Recipe chickenParm = new Recipe("Chicken Parm", 210, recipe3Ingredients, 45, 450);

        ArrayList<String> recipe4Ingredients = new ArrayList<>();
        recipe4Ingredients.add("Chicken");
        recipe4Ingredients.add("Cheese");
        recipe4Ingredients.add("Sauce");

        Recipe crabRangoons = new Recipe("Crab Rangoons", 200, recipe4Ingredients, 40, 325);

        ShopClassLogic.inventory.add(paella);
        ShopClassLogic.inventory.add(mashedPotatoes);
        ShopClassLogic.inventory.add(chickenParm);
        ShopClassLogic.inventory.add(crabRangoons);

        // for floorplan

        Image whitetablebackground = new Image("Menu Images and Icons\\ShopImages\\whitetablefloorplan.jpg");
        Floorplan whiteTable = new Floorplan("White Tables", 1000, 30, whitetablebackground);

        Image retrobackground = new Image("Menu Images and Icons\\ShopImages\\fancyretrochairsfloorplan.png");
        Floorplan retroChairs = new Floorplan("Fancy Retro Dining Chairs", 2000, 60, retrobackground);

        ShopClassLogic.inventory.add(whiteTable);
        ShopClassLogic.inventory.add(retroChairs);


    }

}

