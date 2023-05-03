package com.example.pacman;
import com.example.pacman.controller.MazeObject;
import com.example.pacman.view.Ghost;
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
        int ghostCounter = 0;

        for (int row =0; row<NUM_ROWS; row++){
            for(int col = 0; col<NUM_COLS; col++ ){
                tmp = maze.getField(row,col).getObject();
                if(tmp != null) {
                    if (tmp.isPacman) {
                        pacman = new PacmanView(tmp, scene, groupObject, NUM_ROWS, NUM_COLS);

                    }
                    if(tmp.isGhost){
                        if(ghostCounter == 0) {
                            ghostCounter++;
                            ghost1 = new Ghost(tmp, scene, groupObject, NUM_ROWS, NUM_COLS);
                        }
                        else if(ghostCounter == 1) {
                            ghostCounter++;
                            ghost2 = new Ghost(tmp, scene, groupObject, NUM_ROWS, NUM_COLS);
                        }
                        else if(ghostCounter == 2) {
                            ghostCounter++;
                            ghost3 = new Ghost(tmp, scene, groupObject, NUM_ROWS, NUM_COLS);
                        }
                        else if(ghostCounter == 3) {
                            ghostCounter++;
                            ghost4 = new Ghost(tmp, scene, groupObject, NUM_ROWS, NUM_COLS);
                        }
                    }
                }
            }

        }
        Circle sceneCircle = new Circle(100, 60, 40, Color.DARKORANGE);




        groupObject.getChildren().add(grid);
        grid.toBack();




        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
