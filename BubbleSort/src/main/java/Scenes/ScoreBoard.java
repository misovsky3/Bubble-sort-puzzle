package Scenes;

import Components.MenuButton;
import Components.MyChoiceBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is scene for loading scoreboards,
 * if player selects Level, the scores are shown
 */

public class ScoreBoard {

    /**
     * ScoreBoard scene preparing method
     * @param primaryStage specifies primaryStage
     * @return prepared Scene
     * @throws FileNotFoundException if file has been deleted or name of file is wrong
     */

    public static Scene returnScene(Stage primaryStage) throws FileNotFoundException {
        StringProperty textRecu = new SimpleStringProperty();
        TextArea consoleTextArea = new TextArea();
        Pane pane = new Pane();

        Image image1 = new Image(new FileInputStream("src/main/Images/scorebcg.png"));
        BackgroundImage bImg = new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(bImg));

        consoleTextArea.setStyle("-fx-font-size: 2em; -fx-font-family:'Lucida Console';");

        MenuButton button1 = new MenuButton("Return to Main Menu");
        String[] levels = new String[]{"Level 1", "Level 2", "Level 3","Level 4"};
        MyChoiceBox myChoiceBox = new MyChoiceBox(levels);

        myChoiceBox.setOnAction(e -> {
            List<String> players = new ArrayList<>();
            try {
                textRecu.setValue("");

                File myObj = new File("src/main/Files/" +
                        myChoiceBox.getValue().toString().replace(" ","").toLowerCase(Locale.ROOT) + "score");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    players.add(myReader.nextLine());

                }
                myReader.close();
                Collections.sort(players);
                int i = 0;
                String temp;
                String[] scorePlayer;
                for (String player: players) {
                    scorePlayer = player.split(":");
                    try {
                        temp = textRecu.getValueSafe() + ++i + "  " + scorePlayer[1] + "  " + scorePlayer[0] + "\n";
                    }
                    catch (IndexOutOfBoundsException ex){
                        temp = "";
                    }

                    textRecu.setValue(temp);
                }

            }


            catch (FileNotFoundException err) {
                System.out.println("An error occurred.");
                err.printStackTrace();
            }


            consoleTextArea.textProperty().bind(textRecu);
        });

        button1.setOnAction(e ->
        {
            try {
                primaryStage.setScene(MainMenu.returnScene(primaryStage));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        );

        textRecu.addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> {
            consoleTextArea.selectPositionCaret(consoleTextArea.getLength());
            consoleTextArea.deselect();
        });

        button1.relocate(40,30);
        myChoiceBox.relocate(100,100);
        consoleTextArea.relocate(100,180);


        pane.getChildren().addAll(button1,myChoiceBox,consoleTextArea);

        return new Scene(pane, 800, 600);
    }
}
