package com.example.pacman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.example.pacman.controller.Maze;
import com.example.pacman.controller.MazeObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

public class HUD extends Text {

    private MazeObject pac;


    public HUD(Scene scene, Group group, MazeObject pac) {
        this.pac = pac;
        this.setText("LIVES: "+pac.lives);
        this.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        this.setX(15);
        this.setY(25);
        group.getChildren().add(this);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> updateLives()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void updateLives(){
        this.setText("LIVES: "+pac.lives);
    }


}