import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main extends Application {

    public static Stage window;
    public static Scene mainMenu;
    public static Clip clip;

    public static int toggle;

    public static int clipSetter=0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        window = mainStage;
        window.setTitle("Cooking Drama");
        window.setResizable(false);
        window.setOnCloseRequest((e) -> {
            closeProgram();
        });

        // Load the background image
        Image backgroundImage = new Image("file:C:\\Users\\jacob\\IdeaProjects\\Cooking Drama\\src\\testfile.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundImg = new Background(background);

        // Create a VBox to hold the content
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(backgroundImg);

        // Create a Text object for the title
        Text title = new Text("Cooking Drama");
        title.setFill(Color.DARKCYAN);
        title.setFont(Font.font("Brush Script MT", FontWeight.BOLD, 80));

        // Add a black stroke to the title text
        //title.setEffect(new StrokeBorder(Color.BLACK, 2.5, 5, 2.5));
        //title.setStrokeType(StrokeType.OUTSIDE);

        // Create buttons for Play, Options, Credits, and Exit
        Button playButton = createButton("Play");
        Button optionsButton = createButton("Options");
        Button creditsButton = createButton("Credits");
        Button exitButton = createButton("Exit");

        // Set event handlers for the buttons
        playButton.setOnAction(e -> handlePlayButton());
        optionsButton.setOnAction(e -> handleOptionsButton());
        creditsButton.setOnAction(e -> handleCreditsButton());
        exitButton.setOnAction(e -> handleExitButton());


        // Add title
        vbox.getChildren().addAll(title, playButton, optionsButton, creditsButton, exitButton);

        // Create the JavaFX scene and set it on the stage
        mainMenu = new Scene(vbox, 750, 620);
        window.setScene(mainMenu);

        // Set the application icon
        window.getIcons().add(new Image("file:C:\\Users\\jacob\\IdeaProjects\\Cooking Drama\\src\\CookingDramaMainMenuIcon.jpg"));
        window.show();
        toggle = 0;
        playMusic();
    }
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font(18)); // Smaller font size
        button.setMinWidth(150); // Set a fixed width
        button.setMinHeight(40); // Set a fixed height
        return button;
    }

    private void handlePlayButton(){
        window.close();
        clip.stop();
        GameWindows.BlackjackMainMenu();
    }

    private void handleOptionsButton() {
        // Implement the Options button action here
    }

    private void handleCreditsButton() {
        // Implement the Credits button action here
    }

    private void handleExitButton() {
        closeProgram();
    }
    public static void playMusic() throws LineUnavailableException, IOException {
        File mainMenuTheme = new File("C:\\Users\\jacob\\IdeaProjects\\Cooking Drama\\src\\Music and Sound Effects\\Jorge Hernandez - Chopsticks.wav");
        File chipStackingSound = new File("C:\\Users\\jacob\\IdeaProjects\\Cooking Drama\\src\\Music and Sound Effects\\chipStack.wav");
        AudioInputStream audioStream = null;

        if (toggle == 0){
            clipSetter = 0;
        }
        else if(toggle == 1){
            clipSetter = 1;
        }

        switch (clipSetter){
            case 0:
                try {
                    audioStream = AudioSystem.getAudioInputStream(mainMenuTheme);
                } catch (UnsupportedAudioFileException var3) {
                    var3.printStackTrace();
                } catch (IOException var4) {
                    var4.printStackTrace();
                }
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(-1);
                clip.start();
                break;
            case 1:
                clip.stop();
                try {
                    audioStream = AudioSystem.getAudioInputStream(chipStackingSound);
                } catch (UnsupportedAudioFileException var3) {
                    var3.printStackTrace();
                } catch (IOException var4) {
                    var4.printStackTrace();
                }
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                break;
        }

    }
    public static void closeProgram() {
        Boolean answer = ExitPrompt.display("Exit", "Are you sure you want to exit?");
        if (answer) {
            window.close();
        }
    }
}