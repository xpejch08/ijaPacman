package com.example.pacman;

import com.example.pacman.controller.Log;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.view.*;
import com.example.pacman.controller.Maze;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.shape.Circle;



public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Maze maze = new Maze();
        Menu menu = new Menu(maze);
        Stage primaryStage = new Stage();

        PacmanView pacman = null;
        Key key = null;
        Ghost ghost1 = null;
        Ghost ghost2 = null;
        Ghost ghost3 = null;
        Ghost ghost4 = null;

        int NUM_ROWS;
        int NUM_COLS;
        Group groupObject = new Group();


        maze.readSource();
        Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
        Grid grid = new Grid(maze);

        double height = grid.getY();
        double width = grid.getX();
        Scene scene = new Scene(groupObject, width, height);
        menu.titlescreen(primaryStage, scene, grid, groupObject);

        NUM_ROWS = maze.getRowsG() + 2;
        NUM_COLS = maze.getColumnG() + 2;
        MazeObject tmp = null;

        pacman = new PacmanView(maze.getPacMan(NUM_ROWS, NUM_COLS), scene, groupObject, NUM_ROWS, NUM_COLS);
        maze.pac = pacman;
        maze.getGhosts(NUM_ROWS, NUM_COLS);
        for (int j = 0; j < maze.ghostCounter; j++) {
            Ghost i = new Ghost(maze, maze.Ghosts.get(j), groupObject, NUM_ROWS, NUM_COLS);
            maze.GhostViews.add(i);
        }
        maze.getKeys(NUM_ROWS, NUM_COLS);
        for (int j = 0; j < maze.keyCounter; j++) {
            key = new Key(maze.Keys.get(j),groupObject, NUM_ROWS, NUM_COLS);
        }


        Circle sceneCircle = new Circle(100, 60, 40, Color.DARKORANGE);

        HUD hud = new HUD(scene, groupObject, maze.getPacMan(NUM_ROWS, NUM_COLS));
        groupObject.getChildren().add(grid);
        grid.toBack();
        //stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
