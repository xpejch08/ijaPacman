package com.example.pacman.view;

import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.controller.Maze;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.util.Duration;
import java.util.Random;


/**
 * @author Ondřej Češka, Štěpán Pejchar
 *
 * This class is used to create a ghost viewmodel and move it around the maze.
 */
public class Ghost extends Node {

    private MazeObject pac; // instance of MazeObject class that this viewmodel represents
    public Group thisgroup; // Group that this viewmodel is added to and then displayed
    GraphicsContext gc; // GraphicsContext object used for rendering on Canvas
    int CELL_SIZE = 50; // size of each cell in the maze
    int mazeendrows; // max row index of the maze
    int mazeendcols; // max column index of the maze
    Canvas canvas = new Canvas(); // canvas object used for rendering the ghost
    Maze maze; // the maze that the ghost is in
    private Timeline timeline; // timeline used for moving the ghost
    Image image; // image used for rendering the ghost on canvas
    /**
     * @param obj MazeObject, that this viewmodel represents
     * @param group Group, that this viewmodel is added to and then displayed
     * @param rows Number of rows in the maze
     * @param cols Number of columns in the maze
     */
    public Ghost(Maze maze,MazeObject obj, Group group, int rows, int cols){
        pac = obj;
        thisgroup = group;
        this.maze = maze;
        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);
        canvas.setStyle("-fx-border-width: 0;");
        mazeendrows = maze.getEnd().rows;
        mazeendcols = maze.getEnd().cols;
        gc = canvas.getGraphicsContext2D();
        image = new Image("file:lib/images/ghost.png");
        thisgroup.getChildren().add(canvas);
        this.paint();
    }
    /**
     * Paints the ghost viewmodel on the canvas at correct position.
     */
    public void paint() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //gc.fillRect(obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
        gc.drawImage(image,this.pac.cols * CELL_SIZE + 10, this.pac.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
    }
    /**
     * Randomly moves the ghost viewmodel around the maze and moves the actual ghost in BE .
     */
    private void moveGhost() {
        Random rand = new Random();
        int dirNum = rand.nextInt(4); // generate random number between 0 and 3
        FieldInterface.Direction dir = null;
        switch (dirNum) {
            case 0:
                dir = FieldInterface.Direction.L;
                break;
            case 1:
                dir = FieldInterface.Direction.R;
                break;
            case 2:
                dir = FieldInterface.Direction.U;
                break;
            case 3:
                dir = FieldInterface.Direction.D;
                break;
        }
        // Move the Pacman and update the view
        if (pac.move(dir)) {
            paint();
        }
    }
    /**
     * Starts the timeline, that moves the ghost viewmodel. The KeyFrame is set to the specified speed.
     */
    public void startTimeline(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(maze.speed), event ->{
            moveGhost();
            this.endGame();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     *  Stops the timeline, that moves the ghost viewmodel when the game is over.
     */
    public void endGame(){
        if(this.pac.rows == this.mazeendrows && this.pac.cols == this.mazeendcols){
            System.out.println("KONEC");
            timeline.stop();

        }
    }
}
