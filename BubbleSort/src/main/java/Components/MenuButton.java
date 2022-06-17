package Components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * class MenuButton extending class Button
 * There are settings of scaling and style
 */
public class MenuButton extends Button {
    /**
     * Constructor of MenuButton extending class Button
     * @param s label of MenuButton
     */
    public MenuButton(String s){
        this.setText(s);
        this.setScaleX(1.2);
        this.setScaleY(1.2);
        Random rand = new Random();
        this.setBackground(new Background(new BackgroundFill(Color.rgb(rand.nextInt(200),rand.nextInt(200),rand.nextInt(200)), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle(" -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-font-family:'Lucida Console'");
        this.setOnMouseEntered(mouseEvent -> this.setStyle(" -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-underline: true;; -fx-font-family:'Lucida Console'"));
        this.setOnMouseExited(mouseEvent -> this.setStyle(" -fx-font-size: 2em; -fx-text-fill: #FFFFFF; -fx-underline: false;; -fx-font-family:'Lucida Console'"));
    }
}
