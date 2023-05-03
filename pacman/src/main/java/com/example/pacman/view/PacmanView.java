package com.example.pacman.view;


import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import static javafx.scene.input.KeyCode.*;

    public class PacmanView implements KeyListener {

    private MazeObject pac = null;
    public PacmanView(MazeObject obj){
        pac = obj;
        addKeyListener(this);
    }

        @Override
        public void keyPressed(java.awt.event.KeyEvent evt) {


        int key = evt.getKeyCode();  // Keyboard code for the pressed key.

        if (key == VK_LEFT) {

            this.pac.move(FieldInterface.Direction.L);
            System.out.print("GfG1");
        }
        else if (key == VK_RIGHT) {
            this.pac.move(FieldInterface.Direction.R);
        }
        else if (key == VK_UP) {
            this.pac.move(FieldInterface.Direction.U);
        }
        else if (key == VK_DOWN) {
            this.pac.move(FieldInterface.Direction.D);
        }

    }  // end keyPressed()

        @Override
        public void keyTyped(java.awt.event.KeyEvent e) {

        }





        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {

        }

    }
