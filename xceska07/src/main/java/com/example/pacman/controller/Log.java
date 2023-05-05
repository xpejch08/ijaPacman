package com.example.pacman.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.pacman.controller.MazeObjectInterface.maze;

/**
 * @author Štěpán Pejchar xpejch08
 * @author Ondřej Češka   xceska07
 *
 * A class for creating and managing log files for a Pac-Man game.
 */
public class Log {

    /**
     * The timeline for creating log files.
     */
    public Timeline timeline;

    /**
     * The maze to log.
     */
    private final Maze m_maze;

    /**
     * The number of rows in the maze.
     */
    private final int m_rows;

    /**
     * The number of columns in the maze.
     */
    private final int m_cols;

    /**
     * The current iteration of the log.
     */
    public static int logIteration = 1;

    /**
     * Creates a new Log object with the specified maze, number of rows, and number of columns.
     *
     * @param maze the maze to log
     * @param rows the number of rows in the maze
     * @param cols the number of columns in the maze
     */
    public Log(Maze maze, int rows, int cols){
        m_maze = maze;
        m_rows = rows;
        m_cols = cols;
    }

    /**
     * Clears all log files from the "log" folder.
     */
    public void clearLogFolder() {
        File logFolder = new File("data/log");
        if (logFolder.exists() && logFolder.isDirectory()) {
            File[] logFiles = logFolder.listFiles();
            for (File logFile : logFiles) {
                if (logFile.isFile()) {
                    logFile.delete();
                }
            }
        }
    }

    /**
     * Starts the timeline for creating log files.
     */
    public void startTimeline(){
        if(m_maze.diff == 1) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(0.35), event -> createLog()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

        }else {
            timeline = new Timeline(new KeyFrame(Duration.seconds(m_maze.speed), event -> createLog()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    /**
     * Creates a new log file and writes the current state of the maze to it.
     */
    public void createLog() {

        try {
            File logFolder = new File("data/log");
            if (!logFolder.exists()) {
                logFolder.mkdir();
            }
            //change the name of the file based on the logIteration like 1.txt 2.txt and so on
            File logFile = new File("data/log/" + logIteration + ".txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileWriter writer = new FileWriter(logFile);
            //write "m_rows m_col" on first line
            writer.write(m_rows + " " + m_cols + System.getProperty("line.separator"));
            for (int rows = 1; rows <= m_rows; rows++) {
                for (int cols = 1; cols <= m_cols; cols++) {
                    Field field = m_maze.getField(rows, cols);
                    if (field.Ghost != null) {
                        writer.write("G");
                    } else if (field.Pacman != null) {
                        writer.write("S");
                    } else if (field.isEnd) {
                        writer.write("T");
                    } else if (field.Key != null) {
                        writer.write("K");
                    } else if (field.isPath) {
                        writer.write(".");
                    } else {
                        writer.write("X");
                    }
                }
                writer.write(System.getProperty("line.separator"));
            }
            writer.write(System.getProperty("line.separator"));
            logIteration++;
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**

     Stops the timeline associated with this Log instance.
     The timeline is used for creating logs periodically based on the maze movement speed.
     Once called, the timeline stops and no more logs will be created.
     */
    public void stopTime(){
        this.timeline.stop();
    }

}
