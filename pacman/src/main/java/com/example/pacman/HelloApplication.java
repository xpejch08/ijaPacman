package com.example.pacman;

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
        // create a new instance of the Grid class
        Grid grid = new Grid();
        double height = grid.getY();
        double width = grid.getX();

        Maze maze = new Maze();
        maze.readSource();


        Scene scene = new Scene(fxmlLoader.load(), width , height);

        // add the grid to the scene
        scene.setRoot(grid);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
