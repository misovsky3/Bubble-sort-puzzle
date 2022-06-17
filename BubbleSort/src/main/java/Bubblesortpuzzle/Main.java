package Bubblesortpuzzle;


import Scenes.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Main class
 */

public class Main extends Application {

    /**
     * main void in class Main
     * @param args specifies what will be started
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starting application, setting primaryStage
     * @param  primaryStage stage of the application
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bubble sort puzzle");
        primaryStage.setScene(MainMenu.returnScene(primaryStage));
        primaryStage.show();
    }
}



