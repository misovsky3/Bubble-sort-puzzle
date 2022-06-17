package Scenes;

import Components.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is Level loads input level from file
 */

public class Level {
    private static final List<Color> colors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN, Color.VIOLET, Color.ORANGE, Color.MAGENTA, Color.DARKGREEN, Color.GRAY, Color.BLACK, Color.PINK));
    private static int to_int(String s){
        return Integer.parseInt(s);
    }


    /**
     * Level stage preparing method
     * @param primaryStage specifies primaryStage
     * @param level specifies which level or saved configuration player wants to play
     * @return prepared Scene
     * @throws FileNotFoundException if file has been deleted or name of file is wrong
     */
    public static Scene returnScene(Stage primaryStage, String level) throws FileNotFoundException {
        Pane pane = new Pane();
        Stack<Ball> buffer = new Stack<>();
        Moves moves = new Moves();
        Stack<Container> lastContainer = new Stack<>();

        Image image1 = new Image(new FileInputStream("src/main/Images/levelbcg.png"));
        BackgroundImage bImg = new BackgroundImage(image1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(bImg));
        pane.setPrefSize(800,600);
        MenuButton return_to_menu = new MenuButton("Return to Menu");

        MyLabel text = new MyLabel("You made " + "0" + " moves");


        return_to_menu.relocate(40,30);
        text.relocate(380,30);
        List<Container> containers = new ArrayList<>();
        try {
            File myObj = new File("src/main/Files/"+ level);
            Scanner myReader = new Scanner(myObj);
            String[] array;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                array = data.split(",");


                Container container = new Container(to_int(array[2]),buffer, lastContainer, moves);
                container.relocate(to_int(array[0]),to_int(array[1]));
                if(array.length == 4) {
                    for (int i = 0; i < array[3].length(); i++) {
                        Ball ball = new Ball(24, colors.get(to_int(String.valueOf(array[3].charAt(i)))));
                        container.addBall(ball);
                        pane.getChildren().add(ball);
                    }
                }
                pane.getChildren().add(container);
                containers.add(container);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        pane.setOnMouseClicked(e -> {
            boolean done = true;
            text.setText(String.valueOf(moves));
            for (Container container : containers) {
                if (!container.isDone()) {
                    done = false;
                    break;
                }
            }
            if (done){
                try {
                    primaryStage.setScene(Success.returnScene(primaryStage, moves, level));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        );

        return_to_menu.setOnAction(e ->
                {
                    try{
                        if(!buffer.isEmpty()) lastContainer.peek().addBall(buffer.pop());
                        StringBuffer stringBuffer = new StringBuffer();
                        File myObj = new File("src/main/Files/"+ level);
                        Scanner myReader = new Scanner(myObj);
                        String[] array;
                        int pointer = 0;
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            array = data.split(",");
                            stringBuffer.append(array[0]).append(",").append(array[1]).append(",").append(array[2]).append(",");
                            Container container = containers.get(pointer);
                            for (Ball ball:container.getBalls()){
                                stringBuffer.append(colors.indexOf(ball.getColor()));
                            }
                            stringBuffer.append("\n");
                            pointer++;
                        }
                        myReader.close();
                        primaryStage.setScene(Save.returnScene(primaryStage, String.valueOf(stringBuffer)));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
        );


        pane.getChildren().addAll(return_to_menu,text);
        Scene scene = new Scene(pane, 800, 600);
        for (Container container: containers) {
            container.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
            container.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
        }
        return scene;
    }
}
