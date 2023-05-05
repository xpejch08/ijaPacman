package com.example.pacman.view;

import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.controller.Maze;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.util.Duration;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Key {
    private MazeObject pac = null;
    public Group thisgroup;

    GraphicsContext gc;
    int CELL_SIZE = 50;

    private Timeline timeline;

    Canvas canvas = new Canvas();
    Maze maze;
    FieldInterface.Direction dir;

    public Key(MazeObject obj, Scene scene, Group group, int rows, int cols){
        pac = obj;
        thisgroup = group;
        this.maze = maze;
        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);
        canvas.setStyle("-fx-border-width: 0;");
        gc = canvas.getGraphicsContext2D();

        File inputstream = new File("src/images/key.png");
        Image image = new Image("file:src/images/key.png");

        gc.drawImage(image,50,50);
        thisgroup.getChildren().add(canvas);
        this.paint(pac);
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.35), event -> paint(pac)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void paint(MazeObject obj) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //gc.fillRect(obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
        Image image = new Image("file:src/images/key.png");

        if(obj.getField().Key != null) {
            gc.drawImage(image, obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
        }
        else{
            thisgroup.getChildren().remove(canvas);
        }
    }
    public void startKey(){
    }

}
