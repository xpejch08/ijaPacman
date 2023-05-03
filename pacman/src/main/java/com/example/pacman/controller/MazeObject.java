package com.example.pacman.controller;

public class MazeObject {

    public int rows;
    public int cols;
    public boolean isPacman = false;
    public boolean isGhost = false;
    public boolean isKey = false;
    public boolean isEnd =  false;
    public Maze maze;

    public MazeObject(Maze maze, int rows, int cols){
        this.rows   = rows;
        this.cols   = cols;
        this.maze = maze;
    }

    Field getField(){
        return maze.getField(rows, cols);
    }
    public boolean canMove (FieldInterface.Direction dir){
        return this.getField().nextField(dir).isPath;
    }

    public boolean move (FieldInterface.Direction dir){
        Field next = this.getField().nextField(dir);
        MazeObject tmp = next.getObject();
        if (canMove(dir)) {
            if(!next.isEmpty()) {
                if (this.isPacman) {
                    if (tmp.isGhost) {
                        // respawn
                    }
                    else if (tmp.isKey) {
                        next.removeOfField(tmp);
                    }
                    else if (tmp.isEnd) {
                        // ask if he has a key, if yes end game
                    }
                }
                //ghost movement
                return true;
            }
            else{
                // next field is empty, just move

            }
        }
        //cant move
        else{
            return false;
        }
        return false;
    }

    public boolean setPacman() {
        if (isPacman == false) {
            isPacman = true;
            return true;
        }else
            return false;
    }

    public boolean setGhost(){
        if (isGhost == false) {
            isGhost = true;
            return true;
        }else
            return false;
    }
    public boolean setKey(){
        if (isKey == false) {
            isKey = true;
            return true;
        }else
            return false;
    }
    public boolean setEnd(){
        if (isEnd == false) {
            isEnd = true;
            return true;
        }else
            return false;
    }
    public boolean removePacman(){
        if (isPacman == true) {
            isPacman = false;
            return true;
        }else
            return false;
    }
    public boolean removeGhost(){
        if (isGhost == true) {
            isGhost = false;
            return true;
        }else
            return false;
    }
    public boolean removeKey(){
        if (isKey == true) {
            isKey = false;
            return true;
        }else
            return false;
    }
    public boolean removeEnd(){
        if (isEnd == true) {
            isEnd = false;
            return true;
        }else
            return false;
    }
    public void removeAll(){
        isPacman = false;
        isGhost = false;
        isKey = false;
        isEnd = false;
    }

}
