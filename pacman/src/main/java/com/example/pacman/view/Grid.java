package com.example.pacman.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import com.example.pacman.controller.Maze;
/**
 * @author Stěpán Pejchar
 * This class is responsible for the grid in the game.
 * It is used to display the maze and the objects in it.
 * Generates a grid with the size of the maze.
 */
public class Grid extends GridPane {

    private static final int CELL_SIZE = 50;
    public int NUM_ROWS;
    public int NUM_COLS;
    /**
     * @param maze Maze, that is used to generate the grid
     * Generates a grid with the size of the maze.
     */
    public Grid(Maze maze) {
        NUM_ROWS = maze.getRowsG() + 2;
        NUM_COLS = maze.getColumnG() + 2;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if(maze.getField(row, col) != null && maze.getField(row, col).isStart){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null
                        && maze.getField(row, col).getObject() != null
                        && maze.getField(row, col).getObject().isGhost){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && maze.getField(row, col).isEnd) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.ORANGERED);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && maze.getField(row, col).Key != null) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && !maze.getField(row, col).isStart) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && !maze.getField(row, col).isPath && !maze.getField(row, col).isStart){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GREY);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
            }
        }
    }
    /**
     * @param maze Maze, that is used to generate the grid
     * Generates a grid with the size of the maze for LOGGING.
     */
    public void GridLogging(Maze maze) {
        NUM_ROWS = maze.getRowsG() + 2;
        NUM_COLS = maze.getColumnG() + 2;
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
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.PURPLE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && maze.getField(row, col).isEnd) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.ORANGERED);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && maze.getField(row, col).Key != null) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.BLUE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && maze.getField(row, col).isPath && !maze.getField(row, col).isStart) {
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
                else if(maze.getField(row, col) != null && !maze.getField(row, col).isPath && !maze.getField(row, col).isStart){
                    Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.GREY);
                    cell.setStroke(Color.BLACK);
                    add(cell, col, row);
                }
            }
        }
    }
    /** @return int, the size of the grid in pixels */
    public int getY(){
        return CELL_SIZE * NUM_ROWS + 10;
    }
    /** @return int, the size of the grid in pixels */
    public int getX(){
        return CELL_SIZE * NUM_COLS + 10;
    }
}
