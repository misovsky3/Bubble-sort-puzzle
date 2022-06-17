package Scenes;

import Components.MenuButton;
import Components.MyChoiceBox;
import Components.MyLabel;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Load game Scene where player can choose saved game
 */

public class LoadGame {
    /**
     * Load stage preparing method
     * @param primaryStage specifies primaryStage
     * @return prepared Scene
     * @throws FileNotFoundException if file has been deleted or name of file is wrong
     */

    public static Scene returnScene(Stage primaryStage) throws FileNotFoundException {
        Pane pane = new Pane();

        Image image1 = new Image(new FileInputStream("src/main/Images/lights.png"));
        BackgroundImage bImg = new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(bImg));



        MenuButton button1 = new MenuButton("Return to Main Menu");
        MenuButton button2 = new MenuButton("Submit");

        button1.relocate(40,30);
        button2.relocate(180,330);


        List<String> results = new ArrayList<>();
        File[] files = new File("src/main/Files/SavedLevels/").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }

        MyLabel myLabel = new MyLabel("Please select saved game \nand submit");
        myLabel.relocate(150,170);

        MyChoiceBox myChoiceBox = new MyChoiceBox(results, "Choose saved game");
        myChoiceBox.relocate(150,270);



        button1.setOnAction(e ->
                {
                    try {
                        primaryStage.setScene(MainMenu.returnScene(primaryStage));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );


        button2.setOnAction(e -> {
            String chosen = (String) myChoiceBox.getValue();
            System.out.println(chosen);
            if (!Objects.equals(chosen, null)){
                {
                    try {
                        primaryStage.setScene(Level.returnScene(primaryStage,"SavedLevels/"+chosen));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        });

        pane.getChildren().addAll(button1,button2, myChoiceBox, myLabel);
        return new Scene(pane, 800, 600);





    }
}
