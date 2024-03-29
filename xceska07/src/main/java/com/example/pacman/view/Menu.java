package com.example.pacman.view;

import com.example.pacman.controller.Log;
import com.example.pacman.controller.Maze;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;
/**
 * @author Ondřej Češka
 * Represents the menu of the game.
 * Consists of the title screen, choose map screen, choose speed screen, then shows gamescreen.
 * */
public class Menu {
    //initial log number
    private int iterations = 1;
    Grid grid; //grid, used to get the size of the grid
    Group menuGroup; //group to which the menu is added to
    Scene menuscene; //scene on which the menu is displayed
    Maze maze; //maze object, used to get the difficulty
    /**
     * @param maze The maze object, used to get the difficulty.
     * */
    public Menu(Maze maze) {
        this.menuGroup = new Group();
        this.menuscene = new Scene(menuGroup, 400, 400);
        this.maze = maze;
    }

    /** Creates the title screen.
     * @param primaryStage The stage on which everything is displayed in the application.
     * @param gamescreen Used to switch to the gamescreen.
     * @param grid The grid object, used to get the size of the grid.
     *
     * */
    public void titlescreen(Stage primaryStage, Scene gamescreen, Grid grid){
        this.grid = grid;

//Create a group of login
        Group logGroup = new Group();
//Create layouts for the different screens
        VBox layout0 = new VBox();
        VBox layout = new VBox();
        VBox layout2 = new VBox();
// Set the alignment of the layout nodes to the center of the screen
        layout0.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
// Create scenes with the specified layout and dimensions
        Scene scene0 = new Scene(layout0, 300, 300);
        Scene scene = new Scene(layout, 300, 300);
        Scene scene2 = new Scene(layout2, 300, 300);
// Create a scene for logging
        Scene logScene = new Scene(logGroup,grid.getY(), grid.getX());
// Create a rectangle to be used as the background for the logging HUD
        Rectangle r = new Rectangle();
        r.setWidth(logScene.getWidth());
        r.setHeight(30);
        r.setStroke(BLACK);
        r.setFill(Color.DARKGRAY);
// Create texts to display the different entities in the game
        Text ghostcol = new Text();
        ghostcol.setText("GHOST");
        ghostcol.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        ghostcol.setFill(PURPLE);
        ghostcol.setX(10);
        ghostcol.setY(20);
        Text paccol = new Text();
        paccol.setText("PACMAN");
        paccol.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        paccol.setFill(YELLOW);
        paccol.setX(100);
        paccol.setY(20);
        Text keycol = new Text();
        keycol.setText("KEY");
        keycol.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        keycol.setFill(BLUE);
        keycol.setX(220);
        keycol.setY(20);
        Text endcol = new Text();
        endcol.setText("END");
        endcol.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        endcol.setFill(RED);
        endcol.setX(280);
        endcol.setY(20);

// Add the texts and rectangle to a group for the logging HUD
        Group loghudGroup = new Group(r, ghostcol, paccol, keycol, endcol);

// Create labels for screens in the game
        Label label0 = new Label("Welcome!\nWould you like to play");
        Label label01 = new Label("or watch a replay of your last attempt?");
        Label label1 = new Label("Choose map:");
        Label label2 = new Label("Choose difficulty (ghost speed):");

//WELCOME SCREEN BUTTONS
        Button buttonplay = new Button("PLAY");
        buttonplay.setOnAction(e -> {
            primaryStage.setScene(scene);
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
            log.clearLogFolder();
        });
        Button buttonwatch = new Button(">>");
        buttonwatch.setOnAction(e -> {
            primaryStage.setScene(logScene);
            maze.readSourceLog(1);
            Grid gridLog = new Grid(maze);
            gridLog.GridLogging(maze);
            logGroup.getChildren().clear();
            logGroup.getChildren().add(gridLog);
            grid.toFront();
            logGroup.getChildren().add(loghudGroup);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
                iterations++;
                maze.readSourceLog(iterations);
                Grid iter = new Grid(maze);
                iter.GridLogging(maze);
                logGroup.getChildren().clear();
                logGroup.getChildren().add(iter);
                grid.toFront();
                logGroup.getChildren().add(loghudGroup);

            }));
            timeline.setCycleCount(maze.logFilesCount - 1);
            timeline.play();
        });

        Button buttonwatch2 = new Button("<<");
        buttonwatch2.setOnAction(e -> {
            primaryStage.setScene(logScene);
            //just to get logcount
            maze.readSourceLog(1);
            maze.readSourceLog(maze.logFilesCount);
            Grid gridLog = new Grid(maze);
            gridLog.GridLogging(maze);
            logGroup.getChildren().clear();
            logGroup.getChildren().add(gridLog);
            grid.toFront();
            logGroup.getChildren().add(loghudGroup);
            iterations = maze.logFilesCount - 1;
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
                    iterations--;
                    maze.readSourceLog(iterations);
                    Grid iter = new Grid(maze);
                    iter.GridLogging(maze);
                    logGroup.getChildren().clear();
                    logGroup.getChildren().add(iter);
                    grid.toFront();
                    logGroup.getChildren().add(loghudGroup);
            }));
            timeline.setCycleCount(maze.logFilesCount - 2);
            timeline.play();
        });


//FIRST SCREEN BUTTONS
        Button buttonmap1 = new Button("Labyrinth");
        buttonmap1.setMinWidth(120);
        buttonmap1.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
        Button buttonmap2 = new Button("Playground");
        buttonmap2.setMinWidth(120);
        buttonmap2.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
        Button buttonmap3 = new Button("Island");
        buttonmap3.setMinWidth(120);
        buttonmap3.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
//SECOND SCREEN
        Button buttonspeed1 = new Button("1-SLOW");
        buttonspeed1.setMinWidth(70);
        buttonspeed1.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.setSpeed(1);

            maze.startGhosts();
            maze.pac.startTimeline();
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
            maze.setLog(log);
            log.startTimeline();

        });
        Button buttonspeed2 = new Button("2-FAST");
        buttonspeed2.setMinWidth(70);
        buttonspeed2.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.setSpeed(2);
            maze.startGhosts();
            maze.pac.startTimeline();
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
            maze.setLog(log);
            log.startTimeline();

        });
        Button buttonspeed3 = new Button("3-ŠKRR");
        buttonspeed3.setMinWidth(70);
        buttonspeed3.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.setSpeed(3);
            maze.startGhosts();
            maze.pac.startTimeline();
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
            maze.setLog(log);
            log.startTimeline();

        });

        layout0.getChildren().addAll(label0, buttonplay,label01, buttonwatch,buttonwatch2);
        layout.getChildren().addAll(label1, buttonmap1,buttonmap2,buttonmap3);
        layout2.getChildren().addAll(label2, buttonspeed1, buttonspeed2, buttonspeed3);

        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene0);
        primaryStage.show();

    }

}
