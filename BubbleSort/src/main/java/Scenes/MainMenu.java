package Scenes;

import Components.MenuButton;
import Components.MyLabel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Main menu class which is called in main
 */
public class MainMenu{

    /**
     * MainMenu stage preparing method
     * @param primaryStage specifies primarySTage
     * @return prepared Scene
     * @throws FileNotFoundException if file has been deleted or name of file is wrong
     */

    public static Scene returnScene(Stage primaryStage) throws FileNotFoundException {
        Pane pane = new Pane();
        Image image1 = new Image(new FileInputStream("src/main/Images/menubcg.png"));
        BackgroundImage bImg = new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(bImg));

        MyLabel label1 = new MyLabel("Bubble sort puzzle");
        label1.relocate(80,30);
        label1.setStyle("-fx-font-size: 5em; -fx-text-fill: linear-gradient(to left, violet, indigo, blue, green, orange, red); -fx-font-family:'Lucida Console';");
        Label label2 = new Label("Saved games");
        label2.relocate(300,120);

        MenuButton button1 = new MenuButton("Level 1");
        MenuButton button2 = new MenuButton("Level 2");
        MenuButton button3 = new MenuButton("Level 3");
        MenuButton button4 = new MenuButton("Level 4");
        MenuButton button5 = new MenuButton("Scoreboard");
        MenuButton button6 = new MenuButton("Load saved game");

        button1.relocate(100,120);
        button2.relocate(100,200);
        button3.relocate(100,280);
        button4.relocate(100,360);
        button5.relocate(100,440);
        button6.relocate(100,520);

        button1.setOnAction(e ->
                {
                    try {
                        primaryStage.setScene(Level.returnScene(primaryStage,"level1"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        button2.setOnAction(e ->
                {
                    try {
                        primaryStage.setScene(Level.returnScene(primaryStage,"level2"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        button3.setOnAction(e ->
                {
                    try {
                        primaryStage.setScene(Level.returnScene(primaryStage,"level3"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        button4.setOnAction(e ->
                {
                    try {
                        primaryStage.setScene(Level.returnScene(primaryStage,"level4"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );

        button5.setOnAction(e ->
        {
            try {
                primaryStage.setScene(ScoreBoard.returnScene(primaryStage));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        );

        button6.setOnAction(e ->
        {
            try {
                primaryStage.setScene(LoadGame.returnScene(primaryStage));
            } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
            }
        }
        );

        pane.getChildren().addAll(label1,  button1,button2,button3,button4,button5,button6);
        return new Scene(pane, 800, 600);
    }
}
