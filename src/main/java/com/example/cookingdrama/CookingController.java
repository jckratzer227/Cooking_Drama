/************************************************
 * Controls main gameplay aspect of cooking simulator
 * Relies on cooking-view.fxml, FoodIcons folder, Backgrounds folder
 * Icons retrieved from https://www.flaticon.com
 * Creator: Derek Sweet
 * Modification date: 11-30-2023
 *************************************************/
package com.example.cookingdrama;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CookingController {
    private int time;
    private int temp;
    private ArrayList<String> selectedIngredients = new ArrayList<>();
    private static boolean correctDishMade;

    @FXML
    private BorderPane borderPane;
    @FXML
    private Text tempDisplayText;
    @FXML
    private Slider tempSlider;
    @FXML
    private Text timeDisplayText;
    @FXML
    private Slider timeSlider;
    @FXML
    private Text recipeToMakeText;
    @FXML
    private ImageView butterImage;
    @FXML
    private ImageView cheeseImage;
    @FXML
    private ImageView chickenImage;
    @FXML
    private ImageView eggsImage;
    @FXML
    private ImageView pastaImage;
    @FXML
    private ImageView potatoesImage;
    @FXML
    private ImageView sauceImage;
    @FXML
    private ImageView seafoodImage;
    @FXML
    private ImageView seasoningImage;

    /*********************************************************************
     * Establishes visual aspects and creates listeners for the sliders
     *********************************************************************/
    @FXML
    void initialize(){
        //Set listener and behavior for time slider
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                time = (int)timeSlider.getValue();
                timeDisplayText.setText((time) + " min.");
            }
        });
        //Set listener and behavior for temperature slider
        tempSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                temp = (int)tempSlider.getValue();
                tempDisplayText.setText((temp) + " °F");    //NO ONE LIKES CELSIUS
            }
        });
        //Display which recipe needs to be made
        recipeToMakeText.setText(Customer.getOrder().getName());
        //Set background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("Backgrounds/cooking_background2.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);
        borderPane.setBackground(backgroundImg);
    }

    /**********************************************************************
     * Helper method adds ingredient if it is not in selectedIngredients
     * Removes ingredient if it is in list
     * Toggles opacity of associated ImageView
     **********************************************************************/
    @FXML
    void toggleIngredient(String ingredient, ImageView icon){
        if(!selectedIngredients.contains(ingredient)){
            selectedIngredients.add(ingredient);
            icon.setOpacity(1);
        }
        else{
            selectedIngredients.remove(ingredient);
            icon.setOpacity(0.5);
        }
        //For testing
        System.out.println(selectedIngredients);
    }

    /************************************************************
     * Listeners for ImageViews
     * Calls toggleIngredient method
     ***********************************************************/
    @FXML
    void butterClickedHandler(MouseEvent event) {
        toggleIngredient("Butter", butterImage);
    }

    @FXML
    void cheeseClickedHandler(MouseEvent event) {
        toggleIngredient("Cheese", cheeseImage);
    }

    @FXML
    void chickenClickedHandler(MouseEvent event) {
        toggleIngredient("Chicken", chickenImage);
    }

    @FXML
    void eggsClickedHandler(MouseEvent event) {
        toggleIngredient("Eggs", eggsImage);
    }

    @FXML
    void pastaClickedHandler(MouseEvent event) {
        toggleIngredient("Pasta", pastaImage);
    }

    @FXML
    void potatoesClickedHandler(MouseEvent event) {
        toggleIngredient("Potatoes", potatoesImage);
    }

    @FXML
    void sauceClickedHandler(MouseEvent event) {
        toggleIngredient("Sauce", sauceImage);
    }

    @FXML
    void seafoodClickedHandler(MouseEvent event) {
        toggleIngredient("Seafood", seafoodImage);
    }

    @FXML
    void seasoningClickedHandler(MouseEvent event) {
        toggleIngredient("Seasoning", seasoningImage);
    }

    /*************************************************************************
     * Launches separate window with recipe list
     * Relies on recipe-list-view.fxml and RecipeListController
     ************************************************************************/
    @FXML
    void viewRecipesHandler(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("recipe-list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 720);
        stage.setTitle("Recipe List");
        stage.setScene(scene);
        stage.show();
    }

    /**********************************************************************
     * Checks if order is correct
     * If it is, adds a happy customer
     * If not, adds unhappy customer
     * Moves on to next screen
     **********************************************************************/
    @FXML
    void continueButtonHandler(ActionEvent event) {
        Recipe createdRecipe = new Recipe("Recipe", 0, selectedIngredients, time, temp);
        if(Customer.getOrder().checkCorrectRecipe(createdRecipe)){
            DailyResults.addHappyCustomers();
            //Made methods in DailyResults static
            correctDishMade = true;
        }
        else{
            DailyResults.addUnhappyCustomers();
            correctDishMade = false;
        }
        System.out.println(DailyResults.getHappyCustomers());
        System.out.println(DailyResults.getUnhappyCustomers());
        try {
            Stage newStage = (Stage)tempSlider.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("meal-results-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 480);
            newStage.setTitle("Results");
            newStage.setScene(scene);
            newStage.show();
        }catch(IOException e){
            System.out.println("FXML Error!");
        }
    }

    /*****************************************************
     * Method to be called by MealResults screen
     * Returns true if correct meal made, false if not
     *****************************************************/
    public static boolean checkCorrectDish(){
        return correctDishMade;
    }
}

