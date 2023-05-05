/**

 The com.example.pacman package contains classes for the Pacman game.
 <p>
 This package contains the main class of the application, HelloApplication,
 which sets up the game by creating a maze, menu, and various game objects such as Pacman, Ghosts, Keys, and a HUD.
 It also contains several other classes that help with the implementation of the game,
 such as MazeObject, Log, Maze, Menu, etc.
 </p>
 <p>
 The Pacman game is a classic arcade game where the player controls Pacman and tries to collect all keys and reach end while avoiding ghosts.
 The game is implemented using the JavaFX framework.
 </p>
 @author Ondřej Češka, Štěpán Pejchar
 @version 1.0
 */

package com.example.pacman;

import com.example.pacman.controller.Log;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.view.*;
import com.example.pacman.controller.Maze;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Group;



/**
 * @author Ondřej Češka, Štěpán Pejchar
 * The main class of the application.
 */
public class HelloApplication extends Application {
    /**
     Initializes and starts the application. Sets up the game maze, menu, and stage.
     Creates a new PacmanView and Ghost objects and adds them to the scene.
     Shows Menu screen and waits for user input.
     Also initializes the HUD and Grid objects and adds them to the groupObject.
     @param stage the primary stage for the application
     @throws IOException if an I/O error occurs while reading the maze source
     */
    @Override
    public void start(Stage stage) throws IOException {
        Maze maze = new Maze(); //creates a new maze
        Menu menu = new Menu(maze); //creates a new menu
        Stage primaryStage = new Stage(); //primary stage

        PacmanView pacman; //PacmanView object
        Key key; //Key object

        int NUM_ROWS; //number of rows in the maze
        int NUM_COLS; //number of columns in the maze
        Group groupObject = new Group(); //group to which the game objects are added to

        maze.readSource(); //reads the maze source
        Log log = new Log(maze, maze.getRowsG(), maze.getColumnG()); //creates a new log
        Grid grid = new Grid(maze); //creates a new grid

        double height = grid.getY(); //gets the height of the grid
        double width = grid.getX();     //gets the width of the grid
        Scene scene = new Scene(groupObject, width, height); //creates a new scene with the grid size
        menu.titlescreen(primaryStage, scene, grid); //creates the title screen

        NUM_ROWS = maze.getRowsG() + 2; //gets the number of rows in the maze
        NUM_COLS = maze.getColumnG() + 2; //gets the number of columns in the maze
        MazeObject tmp = null; //temporary MazeObject

        pacman = new PacmanView(maze.getPacMan(NUM_ROWS, NUM_COLS), scene, groupObject, NUM_ROWS, NUM_COLS); //creates a new PacmanView object
        maze.pac = pacman; //sets the PacmanView object to the maze pac variable
        maze.getGhosts(NUM_ROWS, NUM_COLS); //gets the ghosts from the maze
        for (int j = 0; j < maze.ghostCounter; j++) { //creates a new Ghost object for each ghost in the maze
            Ghost i = new Ghost(maze, maze.Ghosts.get(j), groupObject, NUM_ROWS, NUM_COLS);
            maze.GhostViews.add(i);
        }
        maze.getKeys(NUM_ROWS, NUM_COLS); //gets the keys from the maze
        for (int j = 0; j < maze.keyCounter; j++) { //creates a new Key object for each key in the maze
            key = new Key(maze.Keys.get(j),groupObject, NUM_ROWS, NUM_COLS);
        }

        HUD hud = new HUD(scene, groupObject, maze.getPacMan(NUM_ROWS, NUM_COLS)); //creates a new HUD object
        groupObject.getChildren().add(grid); //adds the grid to the groupObject
        grid.toBack(); //puts the grid to the back so objects are visible

    }

    public static void main(String[] args) {
        launch();
    }
}
