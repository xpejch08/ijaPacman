package com.example.pacman.controller;

public interface MazeObjectInterface{
    int rows = 0;
    int cols = 0;
    Maze maze = null;


    boolean setPacman();
    boolean setGhost();
    boolean setKey();
    boolean setEnd();
    boolean removePacman();
    boolean removeGhost();
    boolean removeKey();
    boolean removeEnd();
    void removeAll();

}
