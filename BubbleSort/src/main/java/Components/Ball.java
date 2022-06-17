package Components;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;


/**
 * class Ball extending class canvas
 * this class is for making instances of balls in container
 */


public class Ball extends Canvas {



        private final int size;
        private final Color color;

    /**
     * Constructor of class Ball
     * @param  size specifies size of ball
     * @param  color specifies color of ball
     */

        public Ball(int size, Color color){
            this.size = size;
            this.color = color;
            this.setWidth(size);
            this.setHeight(size);
            GraphicsContext graphicsContext = this.getGraphicsContext2D();
            paint(graphicsContext);
            this.setEffect(new Lighting());

        }

    /**
     * Getter of parameter color
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Painting ball into GraphicsContext
     * @param graphicsContext
     */
    private void paint(GraphicsContext graphicsContext){
            graphicsContext.setFill(color);
            graphicsContext.fillOval(0,0,size,size);
        }
}


