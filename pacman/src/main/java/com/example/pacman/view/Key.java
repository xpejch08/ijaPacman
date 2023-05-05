package com.example.pacman.view;

import com.example.pacman.controller.MazeObject;
import com.example.pacman.controller.Maze;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.util.Duration;
/**
 * @author Štěpán Pejchar
 * Represents a key viewmodel and moves it around the maze.
 */
public class Key {
    private MazeObject pac;
    public Group thisgroup;
    GraphicsContext gc;
    int CELL_SIZE = 50;
    private final Timeline timeline;
    Canvas canvas = new Canvas();
    Maze maze;
    Image image;
    /**
     * @param obj MazeObject, that this viewmodel represents
     * @param group Group, that this viewmodel is added to and then displayed
     * @param rows Number of rows in the maze
     * @param cols Number of columns in the maze
     */
    public Key(MazeObject obj, Group group, int rows, int cols){
        pac = obj;
        thisgroup = group;
        this.maze = maze;
        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);
        canvas.setStyle("-fx-border-width: 0;");
        gc = canvas.getGraphicsContext2D();

        image = new Image("file:lib/images/key.png");

        gc.drawImage(image,50,50);
        thisgroup.getChildren().add(canvas);
        this.paint(pac);
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.35), event -> paint(pac)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**
     * Paints the key viewmodel on the canvas at correct position. If there is no Key on the field, removes the viewmodel.
     * @param obj MazeObject, that this viewmodel represents
     */
    public void paint(MazeObject obj) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(obj.getField().Key != null) {
            gc.drawImage(image, obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
        }
        else{
            thisgroup.getChildren().remove(canvas);
        }
    }

}
