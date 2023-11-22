import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.awt.SystemColor.window;

public class ExitPrompt {
    static boolean answer;

    public ExitPrompt() {
    }

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Exit");
        Label label1 = new Label();
        label1.setText(message);
        label1.setTextFill(Color.DARKCYAN);
        Button yesButton = new Button("Yes");
        yesButton.setOnAction((e) -> {
            answer = true;
            window.close();
        });
        Button noButton = new Button("No");
        noButton.setOnAction((e) -> {
            answer = false;
            window.close();
        });
        VBox layout = new VBox(15.0);
        layout.getChildren().addAll(new Node[]{label1, yesButton, noButton});
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white");
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

    public static void closeProgram() {
        Boolean answer = ExitPrompt.display("Exit", "Are you sure you want to exit?");
        if (answer) {
            //window.close();
        }

    }
}
