/**************************************************
 * Displays players recipes
 * Relies upon recipe-list-view.fxml
 * Creator: Derek Sweet
 * Modification date: 11-28-2023
 *************************************************/
package com.example.cookingdrama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RecipeListController {

    @FXML
    private Text recipeListText;

    /*************************************************************************
     * Cycles through players recipes and prints them upon opening the window
     *************************************************************************/
    @FXML
    void initialize(){
        for(Recipe n : Player.getRecipes()){
            recipeListText.setText(recipeListText.getText() + n + "\n");
        }
    }
    @FXML
    void closeButtonHandler(ActionEvent event) {
        Stage stage = (Stage)recipeListText.getScene().getWindow();
        stage.close();
    }

}
