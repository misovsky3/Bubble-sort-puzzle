package Components;

import javafx.scene.control.ComboBox;
import java.util.List;


/**
 * class MyChoiceBox extending class ComboBox
 * This component is for choosing options.
 */

public class MyChoiceBox extends ComboBox {

    /**
     * overriden constructor
     * @param s specifies choices of choicebox
     */

    public MyChoiceBox(String[] s){
        this.setPromptText("Please select Level");
        this.getItems().addAll(s);
        this.setStyle("-fx-font-size: 2em; -fx-font-family:'Lucida Console';");

    }

    /**
     * overriden constructor
     * @param s specifies choices of choicebox
     * @param prompt specifies prompt text of choicebox
     */

    public MyChoiceBox(List<String> s, String prompt){
        this.setPromptText(prompt);
        this.getItems().addAll(s);
        this.setStyle("-fx-font-size: 2em; -fx-font-family:'Lucida Console';");

    }

}
