import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.concurrent.atomic.AtomicInteger;

import javax.sound.sampled.LineUnavailableException;

public class GameWindows {
    public static boolean answer;
    public static Scene bjMenu;
    public static Stage window;
    public static String bet = "10";

    public static int betAmount = 10;

    public static String playerFunds;

    private static Button createButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(160); // Set a fixed width
        button.setMinHeight(40); // Set a fixed height
        button.setFont(Font.font("Amasis MT Pro Black", FontWeight.MEDIUM, 20));
        return button;
    }

    public static void rulesMenu(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Instructions");
        Label title = new Label();
        title.setText("How to Play:");
        title.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        title.setTextFill(Color.DARKRED);
        Text description = new Text();
        String descriptionText = "The object of the game is to get to close to 21 as you can without going over.\nEvery card counts as its individual value except for face cards which count as 10, and aces which count as 1 or 11.\n All cards in your hand add together for your card total.\nIf you go over 21, you automatically lose or bust.\nEven if the dealer goes over and you bust as well, you still lose your bet.\nThe player has four choices: hit, stand, double down, or surrender.\nWhen hitting, a card is added to your hand from the deck.\nYou can hit as many times as you would like without going over.\nIf you stand, you cannot draw any more cards and the dealer will reveal their face down card.\nIf the dealer's total is lower than yours, they must hit to try and beat your hand.\nRegardless of the circumstances, dealers must stand on a 17.\n If you choose to surrender, you will get back half of your initial bet upon forfeiting your hand.\nIf you double down, you will be allowed to draw one additional card, but no more.\nYour bet will also be doubled if you choose this option.\nIf your card total is higher than the dealer's at the end of the round, you win and vice versa.\nIf you have the same card total as the dealer at the end of the round, you draw and get back your initial bet.\nIf you win, you will double your money.";
        description.setText(descriptionText);
        description.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 16));
        Button closeButton = new Button("Close");
        closeButton.setOnAction((e) -> {
            answer = true;
            window.close();
        });
        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, description, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white");
        Scene rulesScreen = new Scene(layout, 800, 600);
        rulesScreen.setFill(Color.GREEN);
        window.setScene(rulesScreen);
        window.showAndWait();
    }
    private static void handleRulesButton() {
        rulesMenu();
    }
    private static void handleMainMenuButton() throws IOException {
        window.close();
        Main.window.setScene(Main.mainMenu);
        Main.window.show();
        try {
            Main.playMusic();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handlePlayButton() {
        betting();
    }

    public static void betting() {
        playerFunds = "      Player Funds: " + Blackjack.playerCash;
        Main.toggle = 1;
        boolean looper = true;
        Label playerCash = new Label();
        //playerCash.setLayoutX(20);
        //playerCash.setLayoutY(20);
        playerCash.setText(playerFunds);
        playerCash.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        playerCash.setTextFill(Color.SNOW);
        String betString = Integer.toString(Blackjack.betAmount, 10);

        VBox arrange = new VBox(40);
        HBox betBox = new HBox(40);
        HBox playerMoney = new HBox(40);
        playerMoney.setAlignment(Pos.BASELINE_LEFT);
        playerMoney.getChildren().addAll(playerCash);
        betBox.setAlignment(Pos.CENTER);
        arrange.setAlignment(Pos.CENTER);
        Scene bettingStage = new Scene(arrange, 1280, 800);
        window.setTitle("Cooking Drama");
        window.setResizable(false);
        Label betAmountWindow = new Label();
        betAmountWindow.setText(betString);
        betAmountWindow.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        betAmountWindow.setTextFill(Color.WHITESMOKE);
        Label title = new Label();
        window.setOnCloseRequest((e) -> {
            Main.closeProgram();
        });
        // Load the background image
        Image backgroundImage = new Image("Menu Images and Icons\\blackjackTable.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);


        Image chipIcon = new Image("Menu Images and Icons\\pokerChip.png");
        ImageView imageView = new ImageView(chipIcon);
        //------Place Bet Button------\\
        Button placeBet = new Button();
        placeBet.setText("Place Bet");
        placeBet.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 28));
        placeBet.setOnAction(event -> {
            if (betAmount <= Blackjack.playerCash) {
                Blackjack.betAmount = betAmount;
                Blackjack.playerCash = Blackjack.playerCash - Blackjack.betAmount;
                roundStart();
            }
            else {
                title.setText("Insufficient Funds: Please Select a New Bet Amount.");
            }
        });
        //------Bet Decrease Button------\\
        Button subButton = new Button("-");
        subButton.setOnAction((event) -> {
            if (betAmount == 10){
                title.setText("Minimum Bet is 10 Drama Coins!");
            }
            betAmount = betAmount - 10;
            bet = Integer.toString(betAmount);
            betAmountWindow.setText(bet);
            try {
                Main.playMusic();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (betAmount < 10){
                //title.setText("Warning, Minimum Bet is 10 Drama Coins!");
                //title.setText("Place Your Bet:");
                betAmount = 10;
                bet = Integer.toString(betAmount);
                betAmountWindow.setText(bet);
            }
        });
        subButton.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 28));
        //------Bet Increase Button------\\
        Button addButton = new Button("+");
        addButton.setOnAction(event -> {
            title.setText("Place Your Bet:");
            betAmount = betAmount + 10;
            bet = Integer.toString(betAmount);
            betAmountWindow.setText(bet);
            try {
                Main.playMusic();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        addButton.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 28));


        title.setText("Place Your Bet:");
        title.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        title.setTextFill(Color.SNOW);

        betBox.getChildren().addAll(subButton, betAmountWindow, addButton);
        // Create a VBox to hold the content
        arrange.setAlignment(Pos.CENTER);
        arrange.setBackground(backgroundImg);
        arrange.getChildren().addAll(title, betBox, imageView, placeBet, playerMoney);
        window.setScene(bettingStage);
        window.show();
    }

    public static void roundStart(){

    }

    private static void handleExitButton() {
        boolean exit = ExitPrompt.display("Hi", "Are you sure you want to exit?");
        if (exit){
            window.close();
        }
    }
    public static void BlackjackMainMenu(){
        Main.clip.stop();
        Main.clipSetter=1;
        window = new Stage();
        window.setTitle("Cooking Drama");
        window.setResizable(false);
        window.setOnCloseRequest((e) -> {
            Main.closeProgram();
        });

        // Load the background image
        Image backgroundImage = new Image("Menu Images and Icons\\blackjackTable.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);

        Image menuIcon = new Image("Menu Images and Icons\\suitPhoto.png");
        ImageView imageView = new ImageView(menuIcon);
        // Create a VBox to hold the content
        VBox vbox = new VBox(35);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(backgroundImg);

        // Create a Text object for the title
        Text title = new Text("Blackjack");
        title.setFill(Color.BLACK);
        title.setFont(Font.font("Old English Text MT", FontWeight.EXTRA_BOLD, 100));

        // Add a black stroke to the title text
        //title.setEffect(new StrokeBorder(Color.BLACK, 2.5, 5, 2.5));
        //title.setStrokeType(StrokeType.OUTSIDE);

        // Create buttons for Play, Options, Credits, and Exit
        Button playButton = createButton("Play");
        Button rulesButton = createButton("Rules");
        Button mainMenuButton = createButton("Main Menu");
        Button exitButton = createButton("Exit");

        playButton.setOnAction(e -> handlePlayButton());
        rulesButton.setOnAction(e -> handleRulesButton());
        mainMenuButton.setOnAction(e -> {
            try {
                handleMainMenuButton();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        exitButton.setOnAction(e -> handleExitButton());
        // Add title
        vbox.getChildren().addAll(imageView, title, playButton, rulesButton, mainMenuButton, exitButton);

        // Create the JavaFX scene and set it on the stage
        Scene mainMenu = new Scene(vbox, 1280, 800);
        window.setScene(mainMenu);

        // Set the application icon
        window.getIcons().add(new Image("file:C:\\Users\\jacob\\IdeaProjects\\Cooking Drama\\src\\CookingDramaMainMenuIcon.jpg"));
        window.show();

    }




}
