package com.example.pacman.controller;

public interface MazeInterface {
    Field getField(int row, int col);
    void startReading(int rows, int cols);
    boolean processLine(String line);
    boolean stopReading();
    boolean readSource();
}
