package com.example.pacman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.example.pacman.controller.Maze;
import com.example.pacman.controller.MazeObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

import static javafx.scene.paint.Color.BLACK;

public class HUD {

    private MazeObject pac;
    Text lives = new Text();
    Text steps = new Text();

    Text diff = new Text();

    public HUD(Scene scene, Group group, MazeObject pac) {
        this.pac = pac;

        Rectangle r = new Rectangle();

        r.setWidth(600);
        r.setHeight(45);
        r.setStroke(BLACK);
        r.setFill(Color.DARKGRAY);
        group.getChildren().add(r);

        lives.setText("LIVES: "+pac.lives);
        lives.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        lives.setX(10);
        lives.setY(30);
        group.getChildren().add(lives);
        Timeline timelineLives = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> updateLives()));
        timelineLives.setCycleCount(Timeline.INDEFINITE);
        timelineLives.play();

        steps.setText("STEPS: "+pac.steps);
        steps.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        steps.setX(170);
        steps.setY(30);
        group.getChildren().add(steps);
        Timeline timelineSteps = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> updateSteps()));
        timelineSteps.setCycleCount(Timeline.INDEFINITE);
        timelineSteps.play();

        diff.setText("DIFFICULTY: "+pac.maze.diff);
        diff.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        diff.setX(350);
        diff.setY(30);
        group.getChildren().add(diff);



    }

    public void updateLives(){
        lives.setText("LIVES: "+pac.lives);
    }
    public void updateSteps(){
        steps.setText("STEPS: "+pac.steps);
    }


}