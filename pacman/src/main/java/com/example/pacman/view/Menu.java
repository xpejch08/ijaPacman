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

    public Menu(Maze maze) {

        this.menuGroup = new Group();
        this.menuscene = new Scene(menuGroup, 400, 400);

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

        Button button = new Button("Forward");
        button.setOnAction(e -> primaryStage.setScene(gamescreen));

        Button button2 = new Button("Backwards");
        button2.setOnAction(e -> primaryStage.setScene(scene));

        TextField text = new TextField();
        text.setMaxWidth(100);

        layout.getChildren().addAll(label1, button);
        layout2.getChildren().addAll(label2, button2, text);

        primaryStage.setTitle("CodersLegacy");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
