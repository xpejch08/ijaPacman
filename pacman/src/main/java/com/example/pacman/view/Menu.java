package com.example.pacman.view;

import com.example.pacman.controller.Log;
import com.example.pacman.controller.Maze;
import com.example.pacman.controller.MazeObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;

public class Menu {
    private MazeObject pac;

    private int iterations = 1;
    Text lives = new Text();
    Text steps = new Text();
    Grid grid;
    Text diff = new Text();
    Rectangle r;
    Group menuGroup;
    Scene menuscene;
    Maze maze;
    Group groupObject;

    public Menu(Maze maze) {

        this.menuGroup = new Group();
        this.menuscene = new Scene(menuGroup, 400, 400);
        this.maze = maze;

        Rectangle r = new Rectangle();
        r.setWidth(600);
        r.setHeight(45);
        r.setStroke(BLACK);
        r.setFill(Color.DARKGRAY);


    }


    public void titlescreen(Stage primaryStage, Scene gamescreen, Grid grid,  Group grup){
        this.grid = grid;
        this.groupObject = grup;
        Group logGroup = new Group();
        VBox layout0 = new VBox();
        VBox layout = new VBox();
        VBox layout2 = new VBox();

        layout0.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);


        Scene scene0 = new Scene(layout0, 300, 300);
        Scene scene = new Scene(layout, 300, 300);
        Scene scene2 = new Scene(layout2, 300, 300);
        Scene logScene = new Scene(logGroup,grid.getY(), grid.getX());

        //TEXTS for logging hud
        Rectangle r = new Rectangle();
        r.setWidth(logScene.getWidth());
        r.setHeight(30);
        r.setStroke(BLACK);
        r.setFill(Color.DARKGRAY);
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
        Group loghudGroup = new Group(r,ghostcol,paccol,keycol,endcol);
        Label label0 = new Label("Welcome!\nWould you like to play");
        Label label01 = new Label("or watch a replay?");
        Label label1 = new Label("Choose the map:");
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
        buttonmap1.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
        Button buttonmap2 = new Button("Playground");
        buttonmap2.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
        Button buttonmap3 = new Button("Island");
        buttonmap3.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
//SECOND SCREEN
        Button buttonspeed1 = new Button("Slow and steady");
        buttonspeed1.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.setSpeed(1);

            maze.startGhosts();
            maze.pac.startTimeline();
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
            log.startTimeline();

        });
        Button buttonspeed2 = new Button("Normal");
        buttonspeed2.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.setSpeed(2);
            maze.startGhosts();
            maze.pac.startTimeline();
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
            log.startTimeline();

        });
        Button buttonspeed3 = new Button("BRRRRR");
        buttonspeed3.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.setSpeed(3);
            maze.startGhosts();
            maze.pac.startTimeline();
            Log log = new Log(maze, maze.getRowsG(), maze.getColumnG());
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
