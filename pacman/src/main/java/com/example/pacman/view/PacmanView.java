package com.example.pacman.view;

import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static javafx.scene.paint.Color.BLACK;

/**
 * @Author Ondřej Češka, Štěpán Pejchar
 * Represents the graphical view of the Pacman game.
 */
public class PacmanView extends Node {
    private MazeObject pac = null;
    public Group thisgroup;
    Scene thisscene;
    GraphicsContext gc;
    int CELL_SIZE = 50;
    int mazeendrows;
    int mazeendcols;
    Canvas canvas = new Canvas();
    Image image;
    FieldInterface.Direction dir;
    private final Timeline timeline;

    /**
     * @param obj MazeObject, that this viewmodel represents
     * @param scene Scene, that this viewmodel is shown on.
     * @param group Group, that this viewmodel is added to and then displayed.
     * @param rows Number of rows in the maze
     * @param cols Number of columns in the maze
     */
    public PacmanView(MazeObject obj, Scene scene, Group group, int rows, int cols) {
        pac = obj;
        thisgroup = group;
        thisscene = scene;

        scene.setOnKeyPressed(this::handleKeyPress);
        mazeendrows = this.pac.maze.getEnd().rows;
        mazeendcols = this.pac.maze.getEnd().cols;
        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);
        canvas.setStyle("-fx-border-width: 0;");

        image = new Image("file:lib/images/pacman.png");

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        thisgroup.getChildren().add(canvas);
        this.paint(obj);

        // Initialize the timeline with a 0.5 second duration and a function to move the Pacman
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.35), event -> {
            movePacman();
            this.endGame();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }
    /**
     * Paints the Pacman on the canvas on the correct position.
     * @param obj MazeObject, that this viewmodel represents, to get the position from.
     */
    public void paint(MazeObject obj) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(image,obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
    }
    /**
     * Handles the keypresses and sets the direction of the Pacman.
     * @param event KeyEvent, that triggered this function.
     */
    private void handleKeyPress(KeyEvent event) {
        this.pac.canloose = true;

        if (event.getCode() == KeyCode.LEFT) {
            dir = FieldInterface.Direction.L;
        } else if (event.getCode() == KeyCode.RIGHT) {
            dir = FieldInterface.Direction.R;
        } else if (event.getCode() == KeyCode.UP) {
            dir = FieldInterface.Direction.U;
        } else if (event.getCode() == KeyCode.DOWN) {
            dir =  FieldInterface.Direction.D;
        }
    }
    /**
     * Moves the Pacman in the direction set by the handleKeyPress function and updates the view.
     */
    private void movePacman() {
        // Move the Pacman and update the view
        if(dir != null) {
            if (pac.move(dir)) {
                paint(pac);

            }
        }
    }
    /**
     * Starts the timeline.
     */
    public void startTimeline(){
        timeline.play();
    }
    /**
     * Stops the timeline and shows game over screen when needed.
     */
    public void endGame(){
        if(this.pac.rows == this.mazeendrows && this.pac.cols == this.mazeendcols || this.pac.getLives() == 0){
            Text t = new Text();
            if(this.pac.getLives() == 0){
                t.setText("GAME OVER - you died!");
            }else{
                t.setText("GAME OVER - you won!");
            }
            System.out.println("KONEC");
            this.timeline.stop();
            this.pac.maze.stopgame();
            this.pac.maze.thislog.stopTime();
            Rectangle r = new Rectangle();
            r.setX(thisscene.getWidth()/2-200);
            r.setY(thisscene.getHeight()/2-150);
            r.setWidth(400);
            r.setHeight(300);
            r.setStroke(BLACK);
            r.setFill(Color.DARKGRAY);

            t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            t.setX(thisscene.getWidth()/2-195);
            t.setY(thisscene.getHeight()/2);

            Text t2 = new Text();
            t2.setText("Restart the game to view this attempt or play again.");
            t2.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
            t2.setX(thisscene.getWidth()/2-140);
            t2.setY(thisscene.getHeight()/2+50);

            thisgroup.getChildren().add(r);
            thisgroup.getChildren().add(t);
            thisgroup.getChildren().add(t2);
            t.toFront();
            t2.toFront();
        }
    }
}
