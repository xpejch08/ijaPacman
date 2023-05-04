package com.example.pacman;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.view.Ghost;
import com.example.pacman.view.HUD;
import com.example.pacman.view.PacmanView;
import com.example.pacman.controller.Maze;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import com.example.pacman.view.Grid;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.Node;




public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        PacmanView pacman = null;
        Ghost ghost1 = null;
        Ghost ghost2 = null;
        Ghost ghost3 = null;
        Ghost ghost4 = null;

        int NUM_ROWS;
        int NUM_COLS;
        Group groupObject = new Group();
        //Group newgroup = new Group();
        Maze maze = new Maze();
        maze.readSource();
        Grid grid = new Grid(maze);

        double height = grid.getY();
        double width = grid.getX();
        Scene scene = new Scene(groupObject, width , height);

        // add the grid to the scene

        stage.setTitle("Hello!");
        stage.setScene(scene);
        NUM_ROWS = maze.getRowsG()+2;
        NUM_COLS = maze.getColumnG()+2;
        MazeObject tmp = null;

        pacman = new PacmanView(maze.getPacMan(NUM_ROWS, NUM_COLS), scene, groupObject, NUM_ROWS, NUM_COLS);
        maze.getGhosts(NUM_ROWS, NUM_COLS);
        for (int j =0; j<maze.ghostCounter; j++){
            Ghost i = new Ghost(maze.Ghosts.get(j), scene, groupObject, NUM_ROWS, NUM_COLS);
        }

        Circle sceneCircle = new Circle(100, 60, 40, Color.DARKORANGE);

        HUD hud = new HUD(scene,groupObject,maze.getPacMan(NUM_ROWS, NUM_COLS) );



        groupObject.getChildren().add(grid);
        grid.toBack();





        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
