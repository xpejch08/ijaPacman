package com.example.pacman.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static com.example.pacman.controller.MazeObjectInterface.maze;

public class Log {

    private Maze m_maze;
    private int m_rows;
    private int m_cols;

    public static int logIteration = 1;

    public Log(Maze maze, int rows, int cols){
            m_maze = maze;
            m_rows = rows;
            m_cols = cols;
    }

    public void createLog() {
        try {
            File logFolder = new File("log");
            if (!logFolder.exists()) {
                logFolder.mkdir();
            }
            //change the name of the file based on the logIteration like 1.txt 2.txt and so on
            File logFile = new File("log/" + logIteration + ".txt");
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
            logIteration++;
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
