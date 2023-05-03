package com.example.pacman.view;


import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;


public class PacmanView {

    private MazeObject pac = null;

    public PacmanView(MazeObject obj, Scene scene){
        pac = obj;
        scene.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.pac.move(FieldInterface.Direction.L);
            System.out.print("GfG1");
        } else if (event.getCode() == KeyCode.RIGHT) {
            this.pac.move(FieldInterface.Direction.R);
        } else if (event.getCode() == KeyCode.UP) {
            this.pac.move(FieldInterface.Direction.U);
        } else if (event.getCode() == KeyCode.DOWN) {
            this.pac.move(FieldInterface.Direction.D);
        }
    }

}
