package com.example.pacman;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.view.PacmanView;
import com.example.pacman.controller.Maze;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import com.example.pacman.view.Grid;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));



        Maze maze = new Maze();
        maze.readSource();
        Grid grid = new Grid(maze);

        double height = grid.getY();
        double width = grid.getX();
        Scene scene = new Scene(fxmlLoader.load(), width , height);

        // add the grid to the scene
        scene.setRoot(grid);

        stage.setTitle("Hello!");
        stage.setScene(scene);

        MazeObject mypac = new MazeObject(maze, 1, 1);
        PacmanView pacman = new PacmanView(mypac, scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
