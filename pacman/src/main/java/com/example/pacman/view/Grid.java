package com.example.pacman.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import com.example.pacman.controller.Maze;

public class Grid extends GridPane {

    //todo dynamic gridsize through constructor
    private static final int CELL_SIZE = 50;
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 5;

    public Grid(Maze maze) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if(maze.getField(row, col) != null && maze.getField(row, col).isStart){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.YELLOW);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null
                        && maze.getField(row, col).getObject() != null
                        && maze.getField(row, col).getObject().isGhost){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GREEN);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && !maze.getField(row, col).isStart) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && !maze.getField(row, col).isPath && !maze.getField(row, col).isStart){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.RED);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
            }
        }
    }

    public int getY(){
        return CELL_SIZE * NUM_ROWS + 10;
    }
    public int getX(){
        return CELL_SIZE * NUM_COLS + 10;
    }
}
