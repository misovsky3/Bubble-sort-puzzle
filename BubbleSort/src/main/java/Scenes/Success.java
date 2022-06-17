package Scenes;

import Components.MenuButton;
import Components.Moves;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.Objects;

/**
 * Scene where player can see that he has made level successfully
 * Name is supposed to be written and saved with number of moves.
 */

public class Success {
    /**
     * Succes scene preparing method
     * @param primaryStage specifies primaryStage
     * @param moves reference to moves
     * @param level specifies which level was successfully solved
     * @return prepared Scene
     * @throws FileNotFoundException  if file has been deleted or name of file is wrong
     */

    public static Scene returnScene(Stage primaryStage, Moves moves, String level) throws FileNotFoundException {
    Pane pane = new Pane();

    Image image1 = new Image(new FileInputStream("src/main/Images/success.png"));
    BackgroundImage bImg = new BackgroundImage(image1,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    pane.setBackground(new Background(bImg));

    Label text = new Label("You made level with " + moves.getMoves() + " moves.\nplease enter your name:");
    text.relocate(120,200);

    MenuButton button1 = new MenuButton("Save and Return to Main Menu");
    button1.relocate(155,400);

    TextField textField = new TextField();
    textField.setMinWidth(300);
    textField.relocate(200,300);

    textField.setStyle("-fx-font-size: 2em; -fx-font-family:'Lucida Console';  ");
    text.setStyle("-fx-font-size: 3em; -fx-font-family:'Lucida Console'; -fx-text-fill: #FFFFFF;");
    pane.getChildren().addAll(button1,text,textField);
    button1.setOnAction(e ->
            {
                try {
                    File file = new File("src/main/Files/"+level+"score");
                    FileWriter fr = new FileWriter(file, true);
                    String name = textField.getText();
                    if (Objects.equals(name, "")){
                        name = "Unnamed";
                    }
                    fr.write( moves.getMoves() + ":" + name + "\n");
                    fr.close();
                    primaryStage.setScene(MainMenu.returnScene(primaryStage));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    );



    return new Scene(pane, 800, 600);
}
}
