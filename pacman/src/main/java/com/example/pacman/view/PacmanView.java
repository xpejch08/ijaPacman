package com.example.pacman.view;


import com.example.pacman.controller.FieldInterface;
import com.example.pacman.controller.MazeObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.*;

public class PacmanView {

    private MazeObject pac = null;
    public PacmanView(MazeObject obj){
        pac = obj;
    }
    public void keyPressed(KeyEvent evt) {
        // Called when the user has pressed a key, which can be
        // a special key such as an arrow key.  If the key pressed
        // was one of the arrow keys, move the square (but make sure
        // that it doesn't move off the edge, allowing for a
        // 3-pixel border all around the applet).  SQUARE_SIZE is
        // a named constant that specifies the size of the square.

        KeyCode key = evt.getCode();  // Keyboard code for the pressed key.

        if (key == LEFT) {
            this.pac.move(FieldInterface.Direction.L);
            System.out.println("GfG1");
        }
        else if (key == RIGHT) {
            this.pac.move(FieldInterface.Direction.R);
        }
        else if (key == UP) {
            this.pac.move(FieldInterface.Direction.U);
        }
        else if (key == DOWN) {
            this.pac.move(FieldInterface.Direction.D);
        }

    }  // end keyPressed()
}
