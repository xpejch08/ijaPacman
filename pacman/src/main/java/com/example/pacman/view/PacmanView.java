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
import javafx.scene.shape.ArcType;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PacmanView extends Node {

    private MazeObject pac = null;
    public Group thisgroup;

    GraphicsContext gc;
    int CELL_SIZE = 50;

    Canvas canvas = new Canvas();

    FieldInterface.Direction dir;
    private Timeline timeline;

    public PacmanView(MazeObject obj, Scene scene, Group group, int rows, int cols) {
        pac = obj;
        thisgroup = group;
        scene.setOnKeyPressed(this::handleKeyPress);

        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);
        canvas.setStyle("-fx-border-width: 0;");

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        thisgroup.getChildren().add(canvas);
        this.paint(obj);

        // Initialize the timeline with a 0.5 second duration and a function to move the Pacman
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.35), event -> movePacman()));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    public void paint(MazeObject obj) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Image image = new Image("file:src/images/pacman.png");
        gc.drawImage(image,obj.cols * CELL_SIZE + 10, obj.rows * CELL_SIZE + 3, CELL_SIZE, CELL_SIZE);
    }

    public Canvas getNode() {
        return canvas;
    }

    private void handleKeyPress(KeyEvent event) {
        this.pac.canloose = true;
        if (event.getCode() == KeyCode.LEFT) {
            dir = FieldInterface.Direction.L;
            System.out.print("LEFT");
        } else if (event.getCode() == KeyCode.RIGHT) {
            dir = FieldInterface.Direction.R;
            System.out.print("RIGHT");
        } else if (event.getCode() == KeyCode.UP) {
            dir = FieldInterface.Direction.U;
            System.out.print("UP");
        } else if (event.getCode() == KeyCode.DOWN) {
            dir =  FieldInterface.Direction.D;
            System.out.print("DOWN");
        }
    }

    private void movePacman() {
        // Move the Pacman and update the view
        if(dir != null) {
            if (pac.move(dir)) {
                paint(pac);
            }
        }
    }
    public void startTimeline(){
        timeline.play();
    }
}
