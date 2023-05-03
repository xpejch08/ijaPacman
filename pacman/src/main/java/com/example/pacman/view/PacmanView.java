package com.example.pacman.view;


import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
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


public class PacmanView {

    private MazeObject pac = null;
    public Group thisgroup;

    GraphicsContext gc;
    int CELL_SIZE = 50;

    Canvas canvas = new Canvas();

    public PacmanView(MazeObject obj, Scene scene, Group group, int rows, int cols) {
        //super();
        pac = obj;
        thisgroup = group;
        scene.setOnKeyPressed(this::handleKeyPress);

        canvas.setHeight(rows * CELL_SIZE);
        canvas.setWidth(cols * CELL_SIZE);

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        thisgroup.getChildren().add(canvas);
        this.paint(obj);

    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.pac.move(FieldInterface.Direction.L);
            this.paint( this.pac);
            System.out.print("LEFT");
        } else if (event.getCode() == KeyCode.RIGHT) {
            this.pac.move(FieldInterface.Direction.R);
            this.paint(this.pac);
            System.out.print("RIGHT");
        } else if (event.getCode() == KeyCode.UP) {
            this.pac.move(FieldInterface.Direction.U);
            this.paint(this.pac);
            System.out.print("UP");
        } else if (event.getCode() == KeyCode.DOWN) {
            this.pac.move(FieldInterface.Direction.D);
            this.paint(this.pac);
            System.out.print("DOWN");
        }
    }

    private void moveFX(FieldInterface.Direction dir){

    }


}
