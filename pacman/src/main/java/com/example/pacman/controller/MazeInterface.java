package com.example.pacman.controller;

public interface MazeInterface {
    public Field getField(int row, int col);
    public void startReading(int rows, int cols);
    public boolean processLine(String line);
    public boolean stopReading();
    public boolean readSource();
}
