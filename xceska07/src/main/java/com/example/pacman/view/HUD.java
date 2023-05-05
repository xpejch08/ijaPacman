package com.example.pacman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import com.example.pacman.controller.MazeObject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

import static javafx.scene.paint.Color.BLACK;
/**
 * @author Ondřej Češka
 * Creates the HUD (Heads Up Display) of the game.
 * Displays the number of lives, steps and the speed of the game.
 */
public class HUD {

    private final MazeObject pac; //pacman object in the game
    Text lives = new Text(); //text displaying the number of lives
    Text steps = new Text(); //text displaying the number of steps
    Text diff = new Text(); //text displaying the speed
    /**
     * @param scene The scene to which the HUD is added to, used to get width.
     * @param group Grop to which the HUD is added to and displayed.
     * @param pac The Pacman object. Used to get the number of lives, steps (and speed).
     */
    public HUD(Scene scene, Group group, MazeObject pac) {
        this.pac = pac;
        //basic background
        Rectangle r = new Rectangle();

        r.setWidth(scene.getWidth());
        r.setHeight(30);
        r.setStroke(BLACK);
        r.setFill(Color.DARKGRAY);
        group.getChildren().add(r);
        //lives
        lives.setText("LIVES: "+pac.lives);
        lives.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        lives.setX(10);
        lives.setY(20);
        group.getChildren().add(lives);

        //steps
        steps.setText("STEPS: "+pac.steps);
        steps.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        steps.setX(130);
        steps.setY(20);
        group.getChildren().add(steps);

        //speed
        diff.setText("SPEED: "+pac.maze.diff);
        diff.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        diff.setX(267);
        diff.setY(20);
        group.getChildren().add(diff);
        //update the values every 0.5 seconds
        Timeline timelineDiff = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            updateDiff();
            updateLives();
            updateSteps();
        }));
        timelineDiff.setCycleCount(Timeline.INDEFINITE);
        timelineDiff.play();
    }
    /**
     * Updates the number of lives displayed.
     */
    public void updateLives(){
        lives.setText("LIVES: "+pac.lives);
    }
    /**
     * Updates the number of steps displayed.
     */
    public void updateSteps(){
        steps.setText("STEPS: "+pac.steps);
    }
    /**
     * Updates the speed displayed.
     */
    public void updateDiff(){
        diff.setText("SPEED: "+pac.maze.diff);
    }


}