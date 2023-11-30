/**************************************************
 * Displays customer order before cooking segment
 * Relies upon customer-order-view.fxml, Backgrounds folder
 * Must be size 720p x 480p
 * Creator: Derek Sweet
 * Modification date: 11-30-2023
 *************************************************/

package com.example.cookingdrama;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerOrderController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Text customerOrderText;

    /**********************************************************
     * Set customer order upon initialization of the screen
     **********************************************************/
    @FXML
    void initialize(){
        //For testing
        if(Player.getRecipes().size() == 0)
            generateRecipes();

        //Generate random recipe from what player has
        Recipe order = Player.getRecipes().get((int)(Math.random() * (Player.getRecipes().size())));
        Customer.setOrder(order);
        /*
        Made the following changes to Customer class:
            Changed RecipeOrder to require a Recipe instead of a String
            Changed methods to match change
            Made RecipeOrder a static variable
            Made methods static
         */
        customerOrderText.setText(Customer.getOrder().getName());
        //Set background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("Backgrounds/customer_order_background.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);
        borderPane.setBackground(backgroundImg);
    }

    /******************************************************************************
     * Method used strictly for testing
     * This will be unnecessary in practice, as the player will already have recipes,
     *    and the recipes will be initialized in another screen
     ******************************************************************************/
    void generateRecipes(){
        Recipe recipe;
        ArrayList<String> recipe1Ingredients = new ArrayList<>();
        ArrayList<String> recipe2Ingredients = new ArrayList<>();
        ArrayList<String> recipe3Ingredients = new ArrayList<>();

        recipe1Ingredients.add("Seafood");
        recipe1Ingredients.add("Chicken");
        recipe1Ingredients.add("Butter");
        recipe = new Recipe("Seafood and Chicken Paella", 0, recipe1Ingredients, 60, 350);
        Player.addRecipe(recipe);

        recipe2Ingredients.add("Pasta");
        recipe2Ingredients.add("Eggs");
        recipe2Ingredients.add("Cheese");
        recipe = new Recipe("Pasta Carbonara", 0, recipe2Ingredients, 30, 160);
        Player.addRecipe(recipe);


        recipe3Ingredients.add("Potatoes");
        recipe3Ingredients.add("Seasoning");
        recipe3Ingredients.add("Butter");
        recipe = new Recipe("Rosemary Mashed Potatoes", 0, recipe3Ingredients, 35, 210);
        Player.addRecipe(recipe);
    }

    /****************************************************************
     * Continue to cooking gameplay section on button pressed
     * May need to modify FXMLLoader to point to correct Main class
     ***************************************************************/
    @FXML
    void continueButtonHandler(ActionEvent event) {
        try {
            Stage newStage = (Stage)customerOrderText.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cooking-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 480);
            newStage.setTitle("Time to Cook!");
            newStage.setScene(scene);
            newStage.show();
        }catch(IOException e){
            System.out.println("FXML Error!");
        }
    }
}