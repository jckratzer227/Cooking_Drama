import javafx.scene.Node;
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

import javax.sound.sampled.LineUnavailableException;

public class GameWindows {
    private static VBox cardSpace;
    public static Label playerBank = new Label();
    public static Label dealerCount = new Label();
    public static Label playerCount = new Label();
    public static Label announcer = new Label();
    public static Label playerBet = new Label();
    public static Label playerBankroll = new Label();
    public static boolean toggle = false;
    public static boolean answer;
    public static boolean stand;
    public static Stage window;
    public static String bet = "10";
    public static int betAmount;
    public static int playerCardTotal;
    public static int dealerCardTotal;
    public static String playerFunds;
    public static Image blankCard = new Image("Blackjack Card Images\\blank.png");
    //Sets the initial image file for the playing card slots on the blackjack table
    public static ImageView dealerCard1 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView dealerCard2 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView dealerCard3 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView dealerCard4 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView dealerCard5 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView dealerCard6 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView dealerCard7 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard1 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard2 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard3 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard4 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard5 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard6 = new ImageView("Blackjack Card Images\\blank.png");
    public static ImageView playerCard7 = new ImageView("Blackjack Card Images\\blank.png");

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
        int musicSelect = 0;
        window.close();
        Main.window.setScene(Main.mainMenu);
        Main.window.show();
        try {
            Main.playMusic(musicSelect);
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
        //betString
        Blackjack.betAmount = 10;
        Blackjack.gameOverTriggered = false;
        playerBank.setText("Player Funds: " + Blackjack.playerCash);
        playerBank.setTranslateX(40);
        playerBank.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        playerBank.setTextFill(Color.SNOW);

        Image coin = new Image("Menu Images and Icons\\dramaCoinSMALL.png");
        ImageView coinPic = new ImageView(coin);
        coinPic.setTranslateY(25);
        coinPic.setTranslateX(40);
        VBox arrange = new VBox(40);
        HBox betBox = new HBox(40);
        HBox playerMoney = new HBox(0);

        playerMoney.setAlignment(Pos.BASELINE_LEFT);
        playerMoney.getChildren().addAll(playerBank, coinPic);
        betBox.setAlignment(Pos.CENTER);
        arrange.setAlignment(Pos.CENTER);
        Scene bettingStage = new Scene(arrange, 1280, 800);
        window.setTitle("Cooking Drama");
        window.setResizable(false);
        Label betAmountWindow = new Label();
        betAmountWindow.setText("" + Blackjack.betAmount);
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
            if(Blackjack.betAmount > Blackjack.playerCash) {
                title.setText("Insufficient Funds: Please Select a New Bet Amount.");
            }
            if (Blackjack.betAmount <= Blackjack.playerCash) {
                Blackjack.playerCash = Blackjack.playerCash - Blackjack.betAmount;
                try {
                    Main.playMusic(2);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    roundStart();
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //------Bet Decrease Button------\\
        Button subButton = new Button("-");
        subButton.setPrefSize(45,45);
        subButton.setMaxSize(45,45);
        subButton.setMinSize(45,45);
        subButton.setOnAction((event) -> {
            title.setText("Place Your Bet:");
            if (Blackjack.betAmount == 10){
                title.setText("Minimum Bet is 10 Drama Coins!");
            }
            Blackjack.betAmount = Blackjack.betAmount - 10;
            bet = Integer.toString(Blackjack.betAmount);
            betAmountWindow.setText(bet);
            try {
                Main.playMusic(1);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Blackjack.betAmount < 10){
                Blackjack.betAmount = 10;
                bet = Integer.toString(Blackjack.betAmount);
                betAmountWindow.setText(bet);
            }
        });
        subButton.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 23));
        //------Bet Increase Button------\\
        Button addButton = new Button("+");
        addButton.setPrefSize(45,45);
        addButton.setMaxSize(45,45);
        addButton.setMinSize(45,45);
        addButton.setOnAction(event -> {
            title.setText("Place Your Bet:");
            Blackjack.betAmount = Blackjack.betAmount + 10;
            bet = Integer.toString(Blackjack.betAmount);
            betAmountWindow.setText(bet);
            try {
                Main.playMusic(1);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        addButton.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 23));


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

    public static void roundStart() throws LineUnavailableException, IOException {
        Blackjack.playerCount = 0;
        Blackjack.dealerCount = 0;
        dealerCount.setText("Dealer: ---");

        dealerCard1.setImage(blankCard);
        dealerCard2.setImage(blankCard);
        dealerCard3.setImage(blankCard);
        dealerCard4.setImage(blankCard);
        dealerCard5.setImage(blankCard);
        dealerCard6.setImage(blankCard);
        playerCard1.setImage(blankCard);
        playerCard2.setImage(blankCard);
        playerCard3.setImage(blankCard);
        playerCard4.setImage(blankCard);
        playerCard5.setImage(blankCard);
        playerCard6.setImage(blankCard);


        //Scene Background
        Image backgroundImage = new Image("Menu Images and Icons\\blackjackTable.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);

        //Coin Icon
        Image coin = new Image("Menu Images and Icons\\dramaCoinSMALL.png");
        ImageView coinPic1 = new ImageView(coin);
        coinPic1.setTranslateY(-5);
        coinPic1.setTranslateX(-40);
        ImageView coinPic2 = new ImageView(coin);
        coinPic2.setTranslateY(-5);
        coinPic2.setTranslateX(-145);

        //Scene element alignment and Containers
        cardSpace = new VBox(50);
        HBox dealerInfo = new HBox(70);
        HBox dealerCards = new HBox(15);
        HBox playerCards = new HBox(15);
        HBox playerInfo = new HBox(70);
        HBox playerChoices = new HBox(50);

        playerChoices.setAlignment(Pos.CENTER);
        cardSpace.setAlignment(Pos.TOP_CENTER);

        //dealerInfo.setTranslateY(50);
        //dealerCards.setTranslateY(50);
        //playerCards.setTranslateY(50);
        //playerChoices.setTranslateY(50);
        //playerInfo.setTranslateY(50);
        //playerChoices.setTranslateY(50);

        dealerCount = new Label();
        dealerCount.setText("Dealer: ---");
        dealerCount.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        dealerCount.setTextFill(Color.SNOW);
        dealerCount.setAlignment(Pos.TOP_LEFT);
        dealerCount.setTranslateY(60);
        dealerCount.setTranslateX(35);

        playerCount = new Label();
        playerCount.setText("Player: ---");
        playerCount.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        playerCount.setTextFill(Color.SNOW);
        playerCount.setTranslateY(10);
        playerCount.setTranslateX(35);

        announcer = new Label();
        announcer.setText("");
        announcer.setAlignment(Pos.CENTER);
        announcer.setFont(Font.font("ADLaM Display", FontWeight.BOLD, 60));
        announcer.setTextFill(Color.YELLOW);
        announcer.setTranslateY(10);
        announcer.setTranslateX(200);

        playerBet.setText("Current Bet: " + Blackjack.betAmount);
        playerBet.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        playerBet.setTextFill(Color.SNOW);
        playerBet.setTranslateY(10);
        playerBet.setTranslateX(35);

        playerBankroll.setText("Player Funds: " + Blackjack.playerCash);
        playerBankroll.setFont(Font.font("Calibri", FontWeight.LIGHT, 40));
        playerBankroll.setTextFill(Color.SNOW);
        playerBankroll.setTranslateY(10);
        playerBankroll.setTranslateX(-70);

        //Dealer's Hand

        dealerCard1.setTranslateX(40);

        dealerCard2.setTranslateX(40);

        dealerCard3.setTranslateX(40);

        dealerCard4.setTranslateX(40);

        dealerCard5.setTranslateX(40);

        dealerCard6.setTranslateX(40);

        dealerCard7.setTranslateX(40);

        //Player's Hand

        playerCard1.setTranslateX(40);
        playerCard1.setTranslateY(30);

        playerCard2.setTranslateX(40);
        playerCard2.setTranslateY(30);

        playerCard3.setTranslateX(40);
        playerCard3.setTranslateY(30);

        playerCard4.setTranslateX(40);
        playerCard4.setTranslateY(30);

        playerCard5.setTranslateX(40);
        playerCard5.setTranslateY(30);

        playerCard6.setTranslateX(40);
        playerCard6.setTranslateY(30);

        playerCard7.setTranslateX(40);
        playerCard7.setTranslateY(30);

        //Creating the Scene
        Scene bettingStage = new Scene(cardSpace, 1280, 850);
        window.setTitle("Cooking Drama");
        window.setResizable(false);

        //test

        //Buttons
        Button hit = new Button();
        hit.setPrefSize(180,100);
        hit.setMaxSize(180,100);
        hit.setMinSize(180,100);
        hit.setTranslateY(-50);
        hit.setText("HIT");
        hit.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 22));


        Button stand = new Button();
        stand.setPrefSize(180,100);
        stand.setMaxSize(180,100);
        stand.setMinSize(180,100);
        stand.setTranslateY(-50);
        stand.setText("STAND");
        stand.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 22));

        //
        Button doubleDown = new Button();
        doubleDown.setPrefSize(180,100);
        doubleDown.setMaxSize(180,100);
        doubleDown.setMinSize(180,100);
        doubleDown.setTranslateY(-50);
        doubleDown.setText("DOUBLE DOWN");
        doubleDown.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 22));

        //Created Surrender button, set its dimensions, font, fontSize, ETC
        Button surrender = new Button();
        surrender.setPrefSize(180,100);
        surrender.setMaxSize(180,100);
        surrender.setMinSize(180,100);
        surrender.setTranslateY(-50);
        surrender.setText("SURRENDER");
        surrender.setFont(Font.font("Algerian", FontWeight.EXTRA_BOLD, 22));

        if(Blackjack.playerCash < Blackjack.betAmount){
            doubleDown.setDisable(true);
        }
        if(Blackjack.playerCash >= Blackjack.betAmount){
            doubleDown.setDisable(false);
        }
        //Player Hit Option (Draw A Card)
        hit.setOnAction(event -> {
            try {
                Main.playMusic(3);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Blackjack.hit();
            playerCount.setText("Player: " + playerCardTotal);
            if(Blackjack.playerCount >= 21){
                hit.setDisable(true);
                stand.setDisable(true);
                doubleDown.setDisable(true);
                surrender.setDisable(true);
            }
            playerCount.setText("Player: " + Blackjack.playerCount);
        });
        //Player Stand Option In Game Window
        stand.setOnAction(event -> {
            Blackjack.playerStand = true;

            dealerCount.setText("Dealer: " + Integer.toString(Blackjack.dealerCount));
            hit.setOpacity(0.2);
            stand.setOpacity(0.2);
            doubleDown.setOpacity(0.2);
            surrender.setOpacity(0.2);
            try {
                Main.playMusic(4);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageHandler.imagePicker(Blackjack.faceDownCard, Blackjack.draw, true, Blackjack.dealerCardCounter, Blackjack.playerCardCounter);
            hit.setDisable(true);
            stand.setDisable(true);
            doubleDown.setDisable(true);
            surrender.setDisable(true);
            Blackjack.dealerAI();
            dealerCount.setText("Dealer: " + Blackjack.dealerCount);
        });

        doubleDown.setOnAction(event -> {
            Blackjack.dd = true;
            try {
                Main.playMusic(3);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            hit.setOpacity(0.2);
            stand.setOpacity(0.2);
            doubleDown.setOpacity(0.2);
            surrender.setOpacity(0.2);
            hit.setDisable(true);
            stand.setDisable(true);
            doubleDown.setDisable(true);
            surrender.setDisable(true);
            doubleDownLogic();
            ImageHandler.imagePicker(Blackjack.faceDownCard, Blackjack.draw, true, Blackjack.dealerCardCounter, Blackjack.playerCardCounter);
            Blackjack.hit();
            playerCount.setText("Player: " + Blackjack.playerCount);
            Blackjack.playerStand = true;
            if(Blackjack.playerCount < 21 && Blackjack.dealerCount < Blackjack.playerCount){
                Blackjack.playerStand = false;
                Blackjack.dealerAI();

            }
            if(Blackjack.playerCount <= 21 && Blackjack.dealerCount > Blackjack.playerCount && Blackjack.dealerCount > 21){
                Blackjack.whoWon();
                Blackjack.playerStand = false;
            }
            if (Blackjack.playerCount <= 21 && Blackjack.dealerCount <= 21){
                Blackjack.whoWon();
            }

            dealerCount.setText("Dealer: " + Blackjack.dealerCount);
        });

        surrender.setOnAction(event -> {
            hit.setOpacity(0.2);
            stand.setOpacity(0.2);
            doubleDown.setOpacity(0.2);
            surrender.setOpacity(0.2);
            hit.setDisable(true);
            stand.setDisable(true);
            doubleDown.setDisable(true);
            surrender.setDisable(true);
            announcer.setText("You Surrendered");
            surrenderLogic();
        });

        //Putting all Scene Elements into Proper Alignment
        cardSpace.setBackground(backgroundImg);
        dealerInfo.getChildren().addAll(dealerCount, announcer);
        dealerCards.getChildren().addAll(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7);
        playerCards.getChildren().addAll(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6, playerCard7);
        playerInfo.getChildren().addAll(playerCount, playerBet, coinPic1, playerBankroll, coinPic2);
        playerChoices.getChildren().addAll(hit, stand, doubleDown, surrender);
        cardSpace.getChildren().addAll(dealerInfo, dealerCards, playerCards, playerInfo, playerChoices);

        //Opening the Scene
        window.setScene(bettingStage);
        window.show();

        Blackjack.roundStart();
        //playerBankroll.setText("Player Funds: " + Blackjack.playerCash);
        playerCardTotal = Blackjack.playerCount;
        dealerCardTotal = Blackjack.playerCount;
        //playerCount.setText("Player: " + playerCardTotal);

    }

    private static void handleExitButton() {
        boolean exit = ExitPrompt.display("Hi", "Are you sure you want to exit?");
        if (exit){
            window.close();
        }
    }
    public static void BlackjackMainMenu(){
        //StatUpdater statUpdater = new StatUpdater();
        //Thread statThread = new Thread(statUpdater);
        //statThread.start();
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
        title.setTranslateY(-40);

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
/*
    public static void countUpdater(boolean playerOrDealer){
        if(!playerOrDealer){
            playerCount.setText("Player: " + Blackjack.playerCount);
        }
        if(playerOrDealer){
            dealerCount.setText("Dealer: " + Blackjack.dealerCount);
        }
    }
*/
    private static void doubleDownLogic(){
        Blackjack.playerCash = Blackjack.playerCash - Blackjack.betAmount;
        playerBankroll.setText("Player Funds:  " + Blackjack.playerCash);
        Blackjack.betAmount = Blackjack.betAmount * 2;
        playerBet.setText("Current Bet: " + Blackjack.betAmount);
        dealerCount.setText("Dealer: " + Blackjack.dealerCount);
    }

    private static void surrenderLogic(){
        Blackjack.playerCash = (int) (Blackjack.playerCash + (Blackjack.betAmount * 0.5));
        playAgain("Hi", "Play Again?");
    }

    public static boolean playAgain(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Exit");
        Label label1 = new Label();
        label1.setText(message);
        label1.setTextFill(Color.DARKRED);
        label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        Button yesButton = new Button("Yes");
        yesButton.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        yesButton.setPrefSize(60,45);
        yesButton.setMaxSize(60,45);
        yesButton.setMinSize(60,45);
        yesButton.setOnAction((e) -> {
            window.close();
            betting();
        });
        Button noButton = new Button("No");
        noButton.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        noButton.setPrefSize(60,45);
        noButton.setMaxSize(60,45);
        noButton.setMinSize(60,45);
        noButton.setOnAction((e) -> {
            window.close();
            GameWindows.window.close();
            BlackjackMainMenu();
        });
        VBox layout = new VBox(35);
        HBox button = new HBox(40);
        button.setAlignment(Pos.CENTER);
        button.getChildren().addAll(yesButton, noButton);
        layout.getChildren().addAll(new Node[]{label1, button});
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white");
        Scene scene = new Scene(layout, 300, 225);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

}
