/**********************************************************************
 * Displays results of cooking segment, adds appropriate amount of coins,
 *  and loops back to hub screen
 * Relies on daily-results-view.fxml, Backgrounds folder
 * Creator: Derek Sweet
 * Modification date: 11-30-2023
 **********************************************************************/
package com.example.cookingdrama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class DailyResultsController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Text coinsEarnedText;

    @FXML
    private Text happyCustomersText;

    @FXML
    private Text totalCoinsText;

    @FXML
    private Text unhappyCustomersText;

    /*******************************************************************
     * Sets text and updates user's coins on initialization
     ******************************************************************/
    @FXML
    void initialize(){
        //For testing
        generateFloorplan();

        coinsEarnedText.setText(coinsEarnedText.getText() + calculateCoinsEarned());
        Player.changeCookingCoins(calculateCoinsEarned());
        happyCustomersText.setText(happyCustomersText.getText() + DailyResults.getHappyCustomers());
        unhappyCustomersText.setText(unhappyCustomersText.getText() + DailyResults.getUnhappyCustomers());
        totalCoinsText.setText(totalCoinsText.getText() + Player.getCookingCoins());

        //Set background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("Backgrounds/daily_results_background.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);
        borderPane.setBackground(backgroundImg);
    }

    /************************************************************
     * Helper method to calculate how many coins to give the user
     *************************************************************/
    int calculateCoinsEarned(){
        return DailyResults.getHappyCustomers()*20 + Player.getFloorplan().getCapacity()*10;
    }

    /**************************************************
     * Resets daily results for next cycle
     * Fill in rest of method to send user back to main hub
     ***************************************************/
    @FXML
    void continueButtonHandler(ActionEvent event) {
        DailyResults.reset();
        //Load hub screen

    }

    /**********************************************************
     * Method used strictly for testing
     * In application, Player will already have a floorplan
     ********************************************************/
    void generateFloorplan(){
        Floorplan floorplan = new Floorplan("Test", 0, 30, null);
        Player.setFloorplan(floorplan);
    }
}
