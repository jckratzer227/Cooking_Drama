import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Blackjack {

    /*
    Testing purposes
     */

    static int[] testCards = {5,3,6,4,0};
    static int cardIndex = 0;
    /*
    end testing stuff
     */

    public static int playerCount = 0;
    public static int dealerCount = 0;
    public static int cardValue;

    ///////////////// Game States /////////////////
    public static boolean gameOverTriggered;
    public static boolean dd = false;
    public static boolean draw;
    public static boolean dealerTurn;
    public static boolean playerTurn;
    public static boolean playerIsAce;
    public static boolean dealerIsAce;
    public static boolean changedPlayerCount;
    public static boolean changedDealerCount;
    public static boolean playerStand = false;
    public static boolean changed = false;
    public static int min = 0;
    public static int max = 51;
    public static int playerCash = 500;
    public static int betAmount = 10;
    public static int aceCount;
    public static int Ace = 0;
    public static int playerAceCount;
    public static int dealerAceCount;
    public static int playerCardCounter;
    public static int dealerCardCounter;
    public static String picChoice;
    public static String faceDownCard;
    public static ArrayList<ArrayList<String>> values = new ArrayList<>();
    public static List<Integer> discard = new ArrayList<>();
    public static ArrayList<String> playerHand = new ArrayList<>();
    public static ArrayList<String> dealerHand = new ArrayList<>();
    //Below are the scenes for different stages of the round.

    public static void roundStart() throws LineUnavailableException, IOException {


        ///////////////// - Game Variables - /////////////////
        playerStand = false;
        playerCardCounter = 0;
        dealerCardCounter = 0;
        playerAceCount = 0;
        dealerAceCount = 0;
        playerIsAce = false;
        dealerIsAce = false;
        changedDealerCount = false;
        changedPlayerCount = false;
        gameOverTriggered = false;
        //////////////////////////////////////////////////////

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
            // TEST CODE START
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
            // TEST CODE END
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        draw = false;
        soundEffect();
        hit();
        hit();
        draw = true;
        hit();
        hit();
        draw = false;
        if (playerIsAce) {
            System.out.println("Congrats! You have an ace!");
        }
        //playerTurn = true;

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
    public static void main(String[] args) throws Exception {}

    public static void stand() throws LineUnavailableException, IOException {
        playerTurn = false;
        dealerTurn = true;
        dealerAI();
    }
    public static void exit(){
        System.out.println("Are you sure?");
    }
    public static void howToPlay(){
        System.out.println("Just place a bet.");
    }
    public static void dealerAI(){
        draw = true;
        if(dealerCount > playerCount){
            whoWon();
        }
        else if (playerCount < 17){
            hit();
        }
        else if(dealerCount < playerCount && playerCount <= 21 && dealerCount < 17){
            hit();
        }
        else if(playerCount > dealerCount && dealerCount >= 17){
            whoWon();
        }
        else if(playerCount == dealerCount){
            whoWon();
        }
        //else if ()
        else {
            hit();
            if (dealerCount < 17){
                hit();
                if (dealerCount < 17){
                    hit();
                    if (dealerCount < 17){
                        hit();
                    }
                }
            }
        }




    }
    public static void hit(){
        int elementPointer1 = 0;
        int elementPointer2 = 0;
        int elementPointer3 = 0;
        int elementPointer4 = 0;
        int elementPointer5 = 0;
        int elementPointer6 = 0;
        int elementPointer7 = 0;
        int elementPointer8 = 0;
        int elementPointer9 = 0;

        System.out.println("");
        System.out.println("...dealing card");
        Random random = new Random();
        /* These nested for loops ensure that the same card can't be drawn twice from the deck.
        --- Here it will redraw up to seven times for each card picked after the first card to drastically reduce  ---
        --- the chances of the same card being redrawn. A redraw is only done if the card that is initially drawn ---
        --- matches with any of those previously drawn which are tracked via the discard array                    ---
         */

        int randomCard = random.nextInt(max - min + 1) + min;
        //int randomCard = testCards[cardIndex++];

        for (int columnIndex = 0; columnIndex < discard.size(); columnIndex++){
            if (randomCard == discard.get(elementPointer1)){
                randomCard = random.nextInt(max - min + 1) + min;
                elementPointer1++;
                for (int secondIndex = 0; secondIndex < discard.size(); secondIndex++){
                    if(randomCard == discard.get(elementPointer2)){
                        randomCard = random.nextInt(max - min + 1) + min;
                        elementPointer2++;
                        for (int thirdIndex = 0; thirdIndex < discard.size(); thirdIndex++){
                            if(randomCard == discard.get(elementPointer3)){
                                randomCard = random.nextInt(max - min + 1) + min;
                                elementPointer3++;
                                for (int fourthIndex = 0; fourthIndex < discard.size(); fourthIndex++){
                                    if(randomCard == discard.get(elementPointer4)){
                                        randomCard = random.nextInt(max - min + 1) + min;
                                        elementPointer4++;
                                        for (int fifthIndex = 0; fifthIndex < discard.size(); fifthIndex++){
                                            if(randomCard == discard.get(elementPointer5)){
                                                randomCard = random.nextInt(max - min + 1) + min;
                                                elementPointer5++;
                                                for (int sixthIndex = 0; sixthIndex < discard.size(); sixthIndex++) {
                                                    if (randomCard == discard.get(elementPointer6)) {
                                                        randomCard = random.nextInt(max - min + 1) + min;
                                                        elementPointer6++;
                                                        for (int seventhIndex = 0; seventhIndex < discard.size(); seventhIndex++) {
                                                            if (randomCard == discard.get(elementPointer7)) {
                                                                randomCard = random.nextInt(max - min + 1) + min;
                                                                elementPointer7++;
                                                                for (int eighthIndex = 0; eighthIndex < discard.size(); eighthIndex++) {
                                                                    if (randomCard == discard.get(elementPointer8)) {
                                                                        randomCard = random.nextInt(max - min + 1) + min;
                                                                        elementPointer8++;
                                                                        for (int ninthIndex = 0; ninthIndex < discard.size(); ninthIndex++) {
                                                                            if (randomCard == discard.get(elementPointer9)) {
                                                                                randomCard = random.nextInt(max - min + 1) + min;
                                                                                elementPointer9++;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Card that was picked: " + randomCard);
        int cardValue = Integer.parseInt(values.get(randomCard).get(1));
        int aceCheck = Integer.parseInt(values.get(randomCard).get(2));
        picChoice = values.get(randomCard).get(3);
        System.out.println("Picture filename: " + picChoice);
        if (aceCheck == 1 && !draw){
            playerIsAce = true;
            playerAceCount++;
            changed = false;
        }
        if (aceCheck == 1 && draw) {
            dealerIsAce = true;
            dealerAceCount++;
            changed = false;
        }
        //if(playerCount > 21 && playerAceCount > 0){
            //playerIsAce = true;
        //}
       //if(dealerCount > 21 && dealerAceCount > 0){
            //dealerIsAce = true;
        //}
        // Adds to the player's count.
        if (!draw) {
            playerCount = playerCount + cardValue;
            playerHand.add(picChoice);
            playerCardCounter++;
            GameWindows.playerCardTotal = playerCount;
            GameWindows.playerCount.setText("Player Count: " + playerCount);
        }
        // Adds to the dealer's count.
        if (draw){
            dealerCount = dealerCount + cardValue;
            dealerHand.add(picChoice);
            dealerCardCounter++;
            GameWindows.dealerCardTotal = dealerCount;
        }

        if(dealerCardCounter == 1 && draw){
            faceDownCard = picChoice;
            picChoice = "Blackjack Card Images\\faceDown.png";
        }
        GameWindows.playerCount.setText("Player: " + playerCount);



        ImageHandler.imagePicker(picChoice, draw, false, dealerCardCounter, playerCardCounter);
        discard.add(randomCard);
        //Stores the path for the dealer's face down card and switches it with the face down image

        // Changes the count of an ace from 11 to 1 if the dealer has busted.

        if (dealerCount > 21 && dealerIsAce && draw){
            dealerCount = dealerCount - 10;
            dealerAceCount --;
            if (dealerAceCount == 0){
                dealerIsAce = false;
            }
        }

        // Changes the count of an ace from 11 to 1 if the player has busted

        else if (playerCount > 21 && playerIsAce && !draw) {
            playerCount = playerCount - 10;
            playerAceCount --;
            if (playerAceCount == 0){
                playerIsAce = false;
            }
        }

        //Handles logic when player got blackjack but did not stand
        if(!playerStand && dealerCardCounter >= 2){
            if(playerCount > 21) {
                gameOverTriggered = true;
                whoWon();
            }
            else if (playerCount == 21 && dealerCount < 17){
                ImageHandler.imagePicker(Blackjack.faceDownCard, Blackjack.draw, true, Blackjack.dealerCardCounter, Blackjack.playerCardCounter);
                GameWindows.dealerCount.setText("Dealer: " + dealerCount);
                GameWindows.playerCount.setText("Player: " + playerCount);
                dealerAI();
            }
            else if ((dealerCount >= 17 || dealerCount == playerCount) && playerCount == 21)
            {
                ImageHandler.imagePicker(Blackjack.faceDownCard, Blackjack.draw, true, Blackjack.dealerCardCounter, Blackjack.playerCardCounter);
                GameWindows.dealerCount.setText("Dealer: " + dealerCount);
                GameWindows.playerCount.setText("Player: " + playerCount);
                whoWon();
            }
        }

        //Handles logic when the player stands and the dealer has drawn another card
        if (playerStand && dealerCardCounter >= 2){
            GameWindows.dealerCount.setText("Dealer: " + dealerCount);
            if (dealerCount > playerCount){
                whoWon();
            }
            else if(playerCount > dealerCount && dealerCount >=17){
                whoWon();
            }
            else if (dealerCount < 17){
                dealerAI();
            }
            else {
                whoWon();
            }

        }


        //Some Test Code
        System.out.println("Player's Count: " + playerCount);
        System.out.println("Dealer's Count: " + dealerCount);
    }
    public static void delay(int duration) {
        //duration is equal to time in milliseconds (Ex. 1000 = 1 second)
        long start = System.currentTimeMillis();
        while(start >= System.currentTimeMillis() - duration); // do nothing
    }
    public static void soundEffect() throws LineUnavailableException, IOException {

        try {
            Blackjack.delay(400);
            Main.playMusic(3);
            Blackjack.delay(450);
            Main.playMusic(3);
            Blackjack.delay(450);
            Main.playMusic(3);
            Blackjack.delay(450);
            Main.playMusic(3);
            Blackjack.delay(450);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void whoWon(){
        GameWindows.playerCount.setText("Player: " + playerCount);
        if(playerCount > 21){
            GameWindows.announcer.setTranslateX(220);
            GameWindows.announcer.setText("You Busted!");
        }
        else if(dealerCount > playerCount && dealerCount <=21){
            GameWindows.announcer.setTranslateX(250);
            GameWindows.announcer.setText("You Lose!");
            playerCash = playerCash + (Blackjack.betAmount * 2);
        }
        else if(playerCount > dealerCount){
            GameWindows.announcer.setTranslateX(250);
            GameWindows.announcer.setText("You Win!");
            playerCash = playerCash + (Blackjack.betAmount * 2);
        }
        else if(dealerCount > playerCount){
            GameWindows.announcer.setTranslateX(250);
            GameWindows.announcer.setText("You Win!");
            playerCash = playerCash + (Blackjack.betAmount * 2);
        }
        else {
            GameWindows.announcer.setTranslateX(290);
            GameWindows.announcer.setText("PUSH");
            playerCash = playerCash + Blackjack.betAmount;
        }

        GameWindows.playAgain("Hi", "Play Again?");

        //GameWindows.dealerCount.setText("Dealer: " + dealerCount);
    }
}
