/**************************************************
 * Shows whether user made the correct recipe  before
 *  either looping or moving on to daily results screen
 * Relies upon meal-results-view.fxml, Nasty_Patty.png, Smiley_Face.png, Backgrounds folder
 * Creator: Derek Sweet
 * Modification date: 11-30-2023
 *************************************************/
package com.example.cookingdrama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class MealResultsController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Text resultText;
    @FXML
    private ImageView resultImageView;

    /**********************************************************************
     * Retrieves whether correct recipe was made from Cooking screen
     * Changes Text and ImageView depending on if correct recipe was made
     *********************************************************************/
    @FXML
    void initialize(){
        Image resultImage;
        if(CookingController.checkCorrectDish()){
            resultText.setText("The customer left happy!");
            resultImage = new Image(getClass().getResourceAsStream("Smiley_Face.png"));
        }
        else{
            resultText.setText("The customer left unhappy");
            resultImage = new Image(getClass().getResourceAsStream("Nasty_Patty.png"));
        }
        resultImageView.setImage(resultImage);

        //Set background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("Backgrounds/daily_results_background.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);
        borderPane.setBackground(backgroundImg);
    }

    /************************************************************************************
     * If total customers (happy + unhappy) equals 5, will send to daily results screen
     * Otherwise, loops back to customer order
     ************************************************************************************/
    @FXML
    void continueButtonHandler(ActionEvent event) {

        try {
            Stage newStage = (Stage)resultText.getScene().getWindow();
            FXMLLoader fxmlLoader;
            if(DailyResults.getHappyCustomers() + DailyResults.getUnhappyCustomers() == 5){
                fxmlLoader = new FXMLLoader(Main.class.getResource("daily-results-view.fxml"));
                newStage.setTitle("Daily Results");
            }
            else{
                fxmlLoader = new FXMLLoader(Main.class.getResource("customer-order-view.fxml"));
                newStage.setTitle("Customer Order");
            }
            Scene scene = new Scene(fxmlLoader.load(), 720, 480);
            newStage.setScene(scene);
            newStage.show();
        }catch(IOException e){
            System.out.println("FXML Error!");
        }
    }

}
