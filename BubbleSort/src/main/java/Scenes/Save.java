package Scenes;

import Components.MenuButton;
import Components.MyLabel;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.Objects;

/**
 * This class is scene for saving level,
 * when player wants to save level
 */

public class Save {

    /**
     * Save stage preparing method
     * @param primaryStage specifies primaryStage
     * @param level specifies which level configuration we would like to save
     * @return prepared Scene
     * @throws FileNotFoundException if file has been deleted or name of file is wrong
     */
    public static Scene returnScene(Stage primaryStage, String level) throws FileNotFoundException {

    Pane pane = new Pane();

    Image image1 = new Image(new FileInputStream("src/main/Images/save.png"));
    BackgroundImage bImg = new BackgroundImage(image1,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    pane.setBackground(new Background(bImg));
    MyLabel text = new MyLabel("Do you want to save \n an unfinished level?");
    text.relocate(150,200);
    MenuButton button1 = new MenuButton("NO");
    MenuButton button2 = new MenuButton("YES");
    MenuButton button3 = new MenuButton("Submit");
    button1.relocate(200,300);
    button2.relocate(350,300);
    button3.relocate(300,450);
    TextField textField = new TextField();
    textField.setMinWidth(300);
    textField.relocate(200,360);
    textField.setStyle("-fx-font-size: 2em; -fx-font-family:'Lucida Console';  ");
    button3.setVisible(false);
    textField.setVisible(false);

    button1.setOnAction(e -> {
        try {
            primaryStage.setScene(MainMenu.returnScene(primaryStage));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    });

    button2.setOnAction(e -> {
        text.setText("Enter name of your \n backup and submit");
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(true);
        textField.setVisible(true);

    });

    button3.setOnAction(e ->
            {
                try {
                    String name = textField.getText();
                    if (Objects.equals(name, "")){
                        name = "Unnamed";
                    }
                    File file = new File("src/main/Files/SavedLevels/" + name  );
                    FileWriter fr = new FileWriter(file);
                    fr.write(level);
                    fr.close();
                    primaryStage.setScene(MainMenu.returnScene(primaryStage));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    );

    pane.getChildren().addAll(button1,text,button2, textField, button3);

    return new Scene(pane, 800, 600);
}
}
