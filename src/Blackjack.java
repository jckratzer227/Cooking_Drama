import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Blackjack extends Application {
    public static int playerCount = 0;
    public static int dealerCount = 0;
    public static int cardValue;
    public static boolean bust = false;
    public static boolean win = false;
    public static boolean lose = false;
    public static boolean draw = false;
    public static boolean dealerTurn;
    public static boolean playerTurn;
    public static boolean playerIsAce;
    public static boolean dealerIsAce;
    public static boolean playerStand = false;
    public static boolean roundOver = false;
    public static boolean playAgain = false;
    public static boolean changeMode = false;
    /*
    gameState keeps track of what part of the Blackjack round you are currently in to switch between phases (stages):
    ( Initial Betting, Player play, Dealer play, Game Over, Play Again? / Round Reset )
     */
    private char gameState = 0;
    public static int min = 0;
    public static int max = 51;
    public static int playerCash = 100;
    public static int betAmount = 10;
    public static int aceCount;
    public static int Ace = 0;
    public static int counter = 0;
    public static int elementPointer1 = 0;
    public static int elementPointer2 = 0;
    public static ArrayList<ArrayList<String>> values = new ArrayList<>();
    public static List<Integer> discard = new ArrayList<>();
    public static ArrayList<String> playerHand = new ArrayList<>();
    public static ArrayList<String> dealerHand = new ArrayList<>();
    //Below are the scenes for different stages of the round.
    public static Stage stage;
    static Scene betting;
    static Scene playerPlay;
    static Scene dealerPlay;
    static Scene endOfRound;
    public static Scene gameModeMenu;
    public static void display(){
        Main.window.setScene(gameModeMenu);
        VBox menu = new VBox(20);
        gameModeMenu = new Scene(menu, 1280,720);
        Scene gameModeMenu = new Scene(menu, 1280, 720);
        Main.window.setTitle("Blackjack Main Menu");
        Main.window.show();
    }
    public class playerOptions{

    }
    public class bettingScene {

    }

    public class endingScreen{

    }
    @Override
    //This method will swap between stages of each round of Blackjack.
    public void start(Stage blackJack) throws Exception {
        if (playerTurn){
            FXMLLoader fxmlLoader = new FXMLLoader(Blackjack.class.getResource("Placeholder.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene playerPlay = new Scene(root1);
            stage.setScene(playerPlay);
        }
        if (playerStand){
            FXMLLoader fxmlLoader = new FXMLLoader(Blackjack.class.getResource("Placeholder1.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene dealerPlay = new Scene(root1);
            stage.setScene(dealerPlay);
        }
        if (roundOver){
            FXMLLoader fxmlLoader = new FXMLLoader(Blackjack.class.getResource("Placeholder2.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene endOfRound = new Scene(root1);
            stage.setScene(endOfRound);
        }
        if (playAgain){
            FXMLLoader fxmlLoader = new FXMLLoader(Blackjack.class.getResource("Placeholder3.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene betting = new Scene(root1);
            stage.setScene(betting);
        }
        if (changeMode){
            stage.setScene(gameModeMenu);
        }
    }

    public static void playerChoice(){
        //Insert buttons and scene layout here
    }
    public static void endOfRoundMenu(){
        //Insert buttons and scene layout here
    }
    public static void gameModeMenu(){
        //Insert buttons and scene layout here
    }
    public static void roundStart(){
        /*Button hit = new Button("Hit");
        hit.setOnAction((e) -> {
            hit();
        });
        Button stand = new Button("Stand");
        stand.setOnAction((e) -> {
            playerStand = true;
            stand();
        });
        Button doubleDown = new Button("Double Down");
        doubleDown.setOnAction((e) -> {
            doubleDown();
        });
        Button surrender = new Button("Surrender");
        surrender.setOnAction((e) -> {
            surrender();
        });

         */
        VBox gmLayout = new VBox(20);
        File file = new File("C:\\Users\\jacob\\IdeaProjects\\Cooking Drama\\src\\cardInfoDatabase.csv");
        try {
            Scanner scanner = new Scanner(file);
            //Read line
            while (scanner.hasNextLine()) {
                String line = String.valueOf(scanner.nextLine());
                ArrayList<String> rowValues = new ArrayList<>();
                rowValues.addAll(List.of(line.split(",")));
                values.add(rowValues);
                //values = rowValues;
            }
            for (int rowIndex = 0; rowIndex < values.size(); rowIndex++) {
                for (int columnIndex = 0; columnIndex < values.get(rowIndex).size(); columnIndex++) {
                    System.out.print(values.get(rowIndex).get(columnIndex) + "\t\t\t");
                    //Ace = Integer.parseInt(values.get(rowIndex).get(2));
                    if (rowIndex > 0 && columnIndex == 6) {
                        cardValue = Integer.parseInt(values.get(rowIndex).get(1));
                        Ace = Integer.parseInt(values.get(rowIndex).get(2));
                        if (Ace == 1) {
                            //playerCount = playerCount - (aceCount * 10);
                            aceCount++;
                            System.out.println(aceCount);
                        }
                        if (cardValue < 10) {
                            System.out.print("low card");
                        }
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hit();
        hit();
        if (playerIsAce) {
            System.out.println("Congrats! You have an ace!");
        }
        draw = true;
        while (counter < 2){
            hit();
            counter++;
        }
        draw = false;
        playerTurn = true;
        System.out.println("");
        System.out.println("Player's Card Count: " + playerCount);
        System.out.print("Player's Hand: ");
        for (int index1 = 0; index1 < playerHand.size(); index1++){
            System.out.print(playerHand.get(index1) + ", ");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Dealer's Card Count: " + dealerCount);
        System.out.print("Dealer's Hand: ");
        for (int index2 = 0; index2 < playerHand.size(); index2++){
            System.out.print(dealerHand.get(index2) + ", ");
        }
        System.out.println("");
        System.out.println("");
        System.out.print("Discards: ");
        for (int columnIndex = 0; columnIndex < discard.size(); columnIndex++){
            System.out.print(discard.get(columnIndex) + ", ");
        }
    }
    public static void main(String[] args) throws Exception {
        Button playButton = new Button("Play");
        playButton.setOnAction((e) -> {
            takeBet();
        });

        Button howToPlay = new Button("How to Play");
        howToPlay.setOnAction((e) -> {
            howToPlay();
        });
        Button exit = new Button("EXIT");
        exit.setOnAction((e) -> {
            exit();
        });
        takeBet();
        roundStart();
    }
    public static void takeBet(){
        Button upBet = new Button("+");
        upBet.setOnAction((e) -> {
            betAmount = betAmount + 10;
            System.out.println("Bet Amount: " + betAmount);
        });
        Button downBet = new Button("-");
        downBet.setOnAction((e) -> {
            betAmount = betAmount - 10;
            System.out.println("Bet Amount: " + betAmount);
        });
        Button placeBet = new Button("Place Bet");
        placeBet.setOnAction((e) -> {
            playerCash = playerCash - betAmount;
            playerTurn = true;
            roundStart();
        });
        Button exit = new Button("Exit");
        exit.setOnAction((e) -> {
            betAmount = betAmount - 10;
        });


        System.out.println("Place your bet!");
        if (betAmount > playerCash){
            System.out.println("Bet Denied! Player has insufficient funds.");
        }
        else{
            playerCash = playerCash - betAmount;
        }
    }
    public static void modeMenuHandler(){
        // for interactions with mode menu
        System.out.println("What do you want to do next?");
        Button shop = new Button("Go to Shop");
        shop.setOnAction((e) -> {
            //
        });
        Button kitchen = new Button("Return to Kitchen");
        kitchen.setOnAction((e) -> {

        });
        Button mainMenu = new Button("Return to Main Menu");
        mainMenu.setOnAction((e) -> {

        });
    }
    public static void gameOver(){
        //stage.setScene(endOfRound);
        // insert fxml file here
        if (playerCount < 22 && playerCount > dealerCount){
            win = true;
        }
        if (dealerCount < 22 && dealerCount > playerCount){
            lose = true;
        }
        Button yes = new Button("Yes");
        yes.setOnAction((e) -> {
            playAgain = true;
            takeBet();
        });
        Button no = new Button("No");
        no.setOnAction((e) -> {
            changeMode = true;
            modeMenuHandler();
        });


        if (win){
            System.out.println("You Win!");
            playerCash = playerCash + (betAmount * 2);
            System.out.println("Play Again?");
            win = false;
        }
        if (lose){
            System.out.println("You Lose!");
            System.out.println("Play Again?");
            lose = false;
        }
        else{
            System.out.println("Draw.");
            playerCash = playerCash + betAmount;
        }
        System.out.println("Play Again?");
    }
    public static void stand(){
        playerTurn = false;
        dealerTurn = true;
        dealerAI();
    }
    public static void doubleDown(){
        playerCash = playerCash - betAmount;
        betAmount = betAmount * 2;
        hit();
        gameOver();
    }
    public static void surrender(){
        playerCash = playerCash + (betAmount/2);
        gameOver();

    }
    public static void exit(){
        System.out.println("Are you sure?");
    }
    public static void howToPlay(){
        System.out.println("Just place a bet.");
    }
    public static void dealerAI(){
        System.out.println("PLACEHOLDER");
        if (dealerCount < 17){
            draw = true;
            hit();
            if (dealerCount < 17){
                hit();
                if (dealerCount < 17) {
                    hit();
                    if (dealerCount < 17) {
                        hit();
                    }
                }
            }
        }
        draw = false;
        gameOver();
    }
    public static void hit() {
        System.out.println("");
        System.out.println("...dealing card");
        Random random = new Random();
        /* These nested for loops ensure that the same card can't be drawn twice from the deck.
        --- Here it will draw up to three times for each card picked after the first card to drastically reduce ---
        --- the chances of the same card that has already been drawn being redrawn. A redraw is only done if    ---
        --- the card that is initially drawn matches with any of those passed into the discard integer array    ---
        --- list which is added to with each subsequent draw.                                                   ---
         */
        int randomCard = random.nextInt(max - min + 1) + min;
        for (int columnIndex = 0; columnIndex < discard.size(); columnIndex++){
            if (randomCard == discard.get(elementPointer1)){
                randomCard = random.nextInt(max - min + 1) + min;
                elementPointer1++;
                for (int secondIndex = 0; secondIndex < discard.size(); secondIndex++){
                    if(randomCard == discard.get(elementPointer2)){
                        randomCard = random.nextInt(max - min + 1) + min;
                        elementPointer2++;
                    }
                }
            }
        }
        System.out.println("Card that was picked: " + randomCard);
        int cardValue = Integer.parseInt(values.get(randomCard).get(1));
        String picChoice = values.get(randomCard).get(3);
        System.out.println("Picture filename: " + picChoice);
        if (draw){
            dealerCount = dealerCount + cardValue;
            dealerHand.add(picChoice);
        }
        if (!draw) {
            playerCount = playerCount + cardValue;
            playerHand.add(picChoice);
        }
        discard.add(randomCard);
        if (playerCount > 21 || dealerCount > 21){
            bust = true;
        }
        int Ace = Integer.parseInt(values.get(randomCard).get(2));
        if (Ace == 1 && draw) {
            dealerIsAce = true;
        }
        if (Ace == 1 && !draw){
            playerIsAce = true;
        }
        aceCount++;
        if (bust) {
            if (playerIsAce || dealerIsAce) {
                if (draw) {
                    dealerCount = dealerCount - (aceCount * 10);
                } else {
                    playerCount = playerCount - (aceCount * 10);
                }
            }
        }
        if (playerCount < 21 && dealerCount < 21){
            bust = false;
        }
        if (playerCount > 21 || dealerCount > 21){
            roundOver = true;
            gameOver();
        }
    }
}