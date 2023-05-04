package com.example.pacman.view;

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

import static javafx.scene.paint.Color.BLACK;

public class Menu {
    private MazeObject pac;
    Text lives = new Text();
    Text steps = new Text();

    Text diff = new Text();
    Rectangle r;
    Group menuGroup;
    Scene menuscene;
    Maze maze;

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


    public void titlescreen(Stage primaryStage, Scene gamescreen){
        VBox layout = new VBox();
        VBox layout2 = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        Scene scene2 = new Scene(layout2, 300, 300);

        Label label1 = new Label("This is the First Scene");
        Label label2 = new Label("This is the Second Scene");

        Button buttonmap1 = new Button("Forward");
        buttonmap1.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
        Button buttonmap2 = new Button("Forward");
        buttonmap2.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });
        Button buttonmap3 = new Button("Forward");
        buttonmap3.setOnAction(e -> {
            primaryStage.setScene(scene2);

        });

        Button buttonspeed1 = new Button("1");
        buttonspeed1.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.startGhosts();
            maze.pac.startTimeline();

        });
        Button buttonspeed2 = new Button("2");
        buttonspeed2.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.startGhosts();
            maze.pac.startTimeline();

        });
        Button buttonspeed3 = new Button("3");
        buttonspeed3.setOnAction(e -> {
            primaryStage.setScene(gamescreen);
            maze.startGhosts();
            maze.pac.startTimeline();

        });


        layout.getChildren().addAll(label1, buttonmap1,buttonmap2,buttonmap3);
        layout2.getChildren().addAll(label2, buttonspeed1, buttonspeed2, buttonspeed3);

        primaryStage.setTitle("CodersLegacy");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
