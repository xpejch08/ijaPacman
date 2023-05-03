package com.example.pacman.view;

import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.util.Duration;

import java.util.Random;

public class Ghost extends Node {

    private MazeObject pac = null;
    public Group thisgroup;

    GraphicsContext gc;
    int CELL_SIZE = 50;

    Canvas canvas = new Canvas();

    FieldInterface.Direction dir;
    private Timeline timeline;

    public Ghost(MazeObject obj, Scene scene, Group group, int rows, int cols) {
        pac = obj;
        thisgroup = group;

        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);
        canvas.setStyle("-fx-border-width: 0;");

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        thisgroup.getChildren().add(canvas);
        this.paint(obj);

        // Initialize the timeline with a 0.5 second duration and a function to move the Pacman
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> moveGhost()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void paint(MazeObject obj) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillRect(obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
    }

    public Canvas getNode() {
        return canvas;
    }


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
            paint(pac);
        }
    }
}
