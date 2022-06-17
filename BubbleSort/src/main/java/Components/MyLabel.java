package Components;

import javafx.scene.control.Label;

/**
 * class MyLabel extending class Label
 * This class modifies styles of Label
 */

public class MyLabel extends Label {

    /**
     * constructor of Label extending class MyLabel
     * @param s specifies text into Label
     */

    public MyLabel(String s){
        this.setText(s);
        this.setStyle("-fx-font-size: 3em; -fx-font-family:'Lucida Console'; -fx-text-fill: #000000;");
    }
}
