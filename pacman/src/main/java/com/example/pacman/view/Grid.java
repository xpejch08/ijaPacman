package com.example.pacman.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends GridPane {

    private static final int CELL_SIZE = 50;
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;

    public Grid() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                cell.setStroke(Color.BLACK);
                add(cell, col, row);
            }
        }
    }
}
