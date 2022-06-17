/**
 * package of whole project
 */
module com.example.bubblesortpuzzle {
    requires javafx.controls;
    requires javafx.fxml;


    opens Bubblesortpuzzle to javafx.fxml;
    exports Bubblesortpuzzle;
}