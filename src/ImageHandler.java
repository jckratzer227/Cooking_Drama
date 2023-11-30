import javafx.scene.image.Image;
public class ImageHandler {

    public static String imagePicker(String currentPic, boolean turn, boolean playerStand, int dealerCards, int playerCards) {



        Image card = new Image(currentPic);

        if(playerStand){
            GameWindows.dealerCard1.setImage(card);
        }


        if (!turn && !playerStand){
            switch (playerCards){
                case 1: GameWindows.playerCard1.setImage(card);
                    break;
                case 2: GameWindows.playerCard2.setImage(card);
                    break;
                case 3: GameWindows.playerCard3.setImage(card);
                    break;
                case 4: GameWindows.playerCard4.setImage(card);
                    break;
                case 5: GameWindows.playerCard5.setImage(card);
                    break;
                case 6: GameWindows.playerCard6.setImage(card);
                    break;
                case 7: GameWindows.playerCard7.setImage(card);
                    break;
                default:
                    break;
            }
        }
        if (turn && !playerStand) {
            switch (dealerCards) {
                case 1: GameWindows.dealerCard1.setImage(card);
                    break;
                case 2: GameWindows.dealerCard2.setImage(card);
                    break;
                case 3: GameWindows.dealerCard3.setImage(card);
                    break;
                case 4: GameWindows.dealerCard4.setImage(card);
                    break;
                case 5: GameWindows.dealerCard5.setImage(card);
                    break;
                case 6: GameWindows.dealerCard6.setImage(card);
                    break;
                case 7: GameWindows.dealerCard7.setImage(card);
                    break;
                default:
                    break;
            }
        }
        return currentPic;
    }
}