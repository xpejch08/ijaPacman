package com.example.pacman.controller;

public interface FieldInterface {


    enum Direction {
        L,
        U,
        R,
        D
    }



    MazeObject getObject();
    boolean setPath();
    boolean canMoveOnField();
    boolean isEmpty();
    boolean insertOnField(MazeObject maze);
    void removeAllField();
    void removeOfField(MazeObject obj);
    Field nextField(Direction dir);
    void setStart();




}