package com.example.pacman.controller;

public interface FieldInterface {


    public static enum Direction {
        L,
        U,
        R,
        D;
    }



    public MazeObject getObject();
    public boolean setPath();
    public boolean canMoveOnField();
    public boolean isEmpty();
    public boolean insertOnField(MazeObject maze);
    public void removeAllField();
    public void removeOfField(MazeObject obj);
    public Field nextField(Direction dir);
    public void setStart();




}