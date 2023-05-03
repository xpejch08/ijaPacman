package com.example.pacman.controller;

public interface MazeObjectInterface{
    public int rows = 0;
    public int cols = 0;
    public Maze maze = null;


    public boolean setPacman();
    public boolean setGhost();
    public boolean setKey();
    public boolean setEnd();
    public boolean removePacman();
    public boolean removeGhost();
    public boolean removeKey();
    public boolean removeEnd();
    public void removeAll();

}
