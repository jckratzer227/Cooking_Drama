/**********************************************************************
 * Displays screens for Shop section
 * Relies on shop.fxml, dishesforpurchase.fxml, upgradeshop.fxml
 * Creator: Kaycee Rigor
 * Modification date: 12-2-2023
 **********************************************************************/

package com.example.shopclass;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.shopclass.Player.cookingCoins;
import static com.example.shopclass.Player.getCookingCoins;
import static com.example.shopclass.ShopClassLogic.buyItem;
import static com.example.shopclass.ShopClassLogic.findItem;


public class HelloController {

    @FXML
    public static Text FloorplanConfirmationText;
    @FXML
    public static Text MenuConfirmationText;
    @FXML
    private static Text cookingCoinsText;
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
        if (cookingCoinsText != null) {
            cookingCoinsText.setText(String.valueOf(Player.getCookingCoins()));
        }
    }

    /*******************************************************************
     * Shop Main Menu -> Recipe Shop
     ******************************************************************/
    @FXML // goes to available menu items for sale
    void RecipeShopMenu (MouseEvent mouseEvent) throws IOException {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dishesforpurchase.fxml"));

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

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("upgradeshop.fxml"));

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
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("shop.fxml"));

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

        try {
            Stage newStage = (Stage) GoBackHandler.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customer-order-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
            newStage.setScene(scene);
            newStage.show();
            newStage.setTitle("Cooking Drama");

        } catch (IOException e) {
            System.out.println("something went wrong w fxml file");
        }
    }
    /*******************************************************************
     * Below deals with purchasing of items, with a click on the image
     * the sprite's message should confirm whether or not the item has been successfully bought or not
     ******************************************************************/
    @FXML
    void BuyRetroChair(MouseEvent event) {
        Item retrocChairs = findItem("Fancy Retro Dining Chairs");
        if (retrocChairs != null) {
            buyItem(retrocChairs);
        }

    }

    @FXML
    void BuyWhiteTable(MouseEvent event) {
        Item whiteTable = findItem("White Tables");
        if (whiteTable != null) {
            buyItem(whiteTable);
        }
    }

    @FXML
    void BuyChickenParm(MouseEvent event) {
        Item chickenParm = findItem("Chicken Parm");
        if (chickenParm != null) {
            buyItem(chickenParm);
        }
    }

    @FXML
    void BuyMashedPotatoes(MouseEvent event) {
        Item mashedPotatoes = findItem("Rosemary Mashed Potatoes");
        if (mashedPotatoes != null) {
            buyItem(mashedPotatoes);
        }
    }

    @FXML
    void BuyPaella(MouseEvent event) {
        Item paella = findItem("Seafood and Chicken Paella");
        if (paella != null) {
            buyItem(paella);
        }
    }

    @FXML
    void BuyRangoons(MouseEvent event) {
        Item crabRangoons = findItem("Crab Rangoons");
        if (crabRangoons != null) {
            buyItem(crabRangoons);
        }
    }

}

