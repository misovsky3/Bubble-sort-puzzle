package Components;

import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

import java.util.Stack;

/**
 * class Container extending canvas
 * this class is for making instances of containers for balls
 */

public class Container extends Canvas {
    private final int capacity;
    private int pointer;

    /**
     * moves is reference to shared class moves, which counts moves of playes
     */
    public Moves moves;
    private final Stack<Ball> balls;
    private final Stack<Ball> buffer;
    private final Stack<Container> lastContainer;
    private boolean done;

    /**
     * Constructor of Container extending class Canvas
     * @param capacity specifies capacity of container
     * @param buffer reference to shared buffer
     * @param lastContainer reference to container which was lastly used
     * @param moves reference to move counter
     */
    public Container(int capacity, Stack<Ball> buffer, Stack<Container> lastContainer, Moves moves) {
        this.balls = new Stack<>();
        this.capacity = capacity;
        this.pointer = 0;
        int width = 50;
        int height = (20 + capacity * 30);
        this.setWidth(width);
        this.setHeight(height);
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        paint(graphicsContext);
        this.setScaleX(1.1);
        this.setScaleY(1.1);
        this.done = true;
        this.buffer = buffer;
        this.lastContainer = lastContainer;
        this.moves = moves;

        this.setOnMouseClicked(e -> {
            if (!moves.isActive())
                moveTopBall();
        });

        Reflection on = new Reflection();
        on.setFraction(0.3);
        Reflection off = new Reflection();
        off.setFraction(0);
        this.setOnMouseEntered(e -> this.setEffect(on));
        this.setOnMouseExited(e -> this.setEffect(off));


    }

    /**
     * getter
     * @return Stack of balls in container
     */
    public Stack<Ball> getBalls() {
        return balls;
    }
    private void moveTopBall(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(200));

        if (!buffer.isEmpty()){ //lopta poklada
            if (lastContainer.peek() == this){
                this.addBall(buffer.pop());
                lastContainer.pop();

            }
            else if ((balls.size() == 0) || (pointer < capacity) && (balls.peek().getColor() == buffer.peek().getColor()) ){
                this.addBall(buffer.pop());
                lastContainer.pop();
                moves.inc();
            }
            else{
                System.out.println("Nepovoleny tah");
            }

        }
        else {
            if (pointer > 0) {
                moves.setActiveTrue();
                lastContainer.push(this);
                Ball liftedBall = balls.pop();
                translateTransition.setNode(liftedBall);
                translateTransition.setByY(translateTransition.getByY() - (capacity - pointer + 1.2) * 30 - 10);
                translateTransition.play();
                buffer.push(liftedBall);
                pointer--;
                if (pointer == 0)
                    done = true;
                translateTransition.setOnFinished(e -> moves.setActiveFalse());
            }
            else{
                System.out.println("Prazdny kontajner");
            }
        }
    }


    /**
     * getter
     * @return if container is full and is containing balls of same color
     */
    public boolean isDone() {
        return done;
    }

    private void paint(GraphicsContext graphicsContext){
        graphicsContext.setLineWidth(4);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeArc(10,capacity*30 - 15,30,30,180,180, ArcType.OPEN);
        graphicsContext.strokePolyline(new double[]{0,10, 10}, new double[]{10, 10, 10+capacity*30 -14},3);
        graphicsContext.strokePolyline(new double[]{ 40,  40, 50}, new double[]{10+capacity*30 - 14, 10, 10},3);
    }

    /**
     * getter
     * @return X position in canvas of ball
     */

    public double getBallPositionX() {
        return this.getLayoutX() + 13;
    }

    /**
     * getter
     * @param index specifies where to put ball in container
     * @return Y position in canvas of ball
     */

    public double getBallPositionY(int index) {
        return this.getLayoutY() + 18 + (capacity - 1 - index) * 30;
    }

    /**
     * adding ball into container
     * @param ball which is supposed to be dropped into container
     */

    public void addBall(Ball ball){

        moves.setActiveTrue();
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(200));
        translateTransition.setNode(ball);
        translateTransition.setToX(getBallPositionX());
        translateTransition.setToY(translateTransition.getByY() + getBallPositionY(pointer));
        translateTransition.play();
        balls.push(ball);
        pointer++;
        if (pointer == capacity ) {
            done = true;
            for (Ball ball1: balls) {
                if (balls.peek().getColor() != ball1.getColor()){
                    done = false;
                }
            }
        }
        else {
            done = false;
        }
        translateTransition.setOnFinished(e -> moves.setActiveFalse());
    }




}
