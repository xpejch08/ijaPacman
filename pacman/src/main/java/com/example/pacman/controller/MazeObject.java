package com.example.pacman.controller;



public class MazeObject {

    public int rows;
    public int cols;
    public int lives = 3;


    public int steps = 0;
    public boolean isPacman = false;
    public boolean isGhost = false;
    public boolean isKey = false;
    public boolean isEnd =  false;
    public boolean hasKey = false;
    public Maze maze;
    public boolean canloose = false;

    public MazeObject(Maze maze, int rows, int cols){
        this.rows   = rows;
        this.cols   = cols;
        this.maze = maze;
    }

    public Field getField(){
        return maze.getField(rows, cols);
    }
    public boolean canMove (FieldInterface.Direction dir){
        return this.getField().nextField(dir).isPath;
    }

    public boolean move (FieldInterface.Direction dir){
        Field next = this.getField().nextField(dir);
        MazeObject tmp = next.getObject();
        if(maze.endgame){
            return false;
        }
        if (canMove(dir)) {
            if(!next.isEmpty()) {
                if (this.isPacman) {

                    if (tmp != null && tmp.isGhost) {
                        this.respawn();

                    }

                    else if (tmp != null && tmp.isKey) {
                        Field fieldSwap = next;
                        maze.keyCounter--;
                        next.removeOfField(tmp);
                        this.getField().removeOfField(this);
                        this.rows = fieldSwap.rows;
                        this.cols = fieldSwap.cols;
                        this.hasKey = true;
                        System.out.println("HASKEY");
                    }
                    else if (next.isEnd) {
                        if(this.hasKey && maze.keyCounter == 0){
                            System.out.println("ENDEDGAME");
                            next.removeOfField(tmp);
                            this.getField().removeOfField(this);
                            this.rows = next.rows;
                            this.cols = next.cols;
                        }
                        // ask if he has a key, if yes end game
                    }
                    else{
                        return false;
                    }
                }
                if(this.isGhost){
                    if(next.getObject() == null){
                        return false;
                    }
                    if(next.getObject().isPacman){
                        next.getObject().respawn();

                    }else{
                        return false;
                    }
                }
                //ghost movement
                return true;
            }
                this.steps++;
                next.insertOnField(this);
                this.getField().removeOfField(this);
                this.getField().isEmpty = true;
                // next field is empty, just move
                if(dir == FieldInterface.Direction.L){
                    this.cols--;
                }
                else if(dir == FieldInterface.Direction.R){
                    this.cols++;
                }
                else if(dir == FieldInterface.Direction.U){
                    this.rows--;
                }
                if(dir == FieldInterface.Direction.D){
                    this.rows++;
                }

                return true;
        }else{
            return false;
        }
    }
    public void respawn(){
        Field fieldSwap = maze.getStart();
        this.getField().removeOfField(this);
        this.rows = fieldSwap.rows;
        this.cols = fieldSwap.cols;
        this.lifeDown();
    }

    public boolean setPacman() {
        if (!isPacman) {
            isPacman = true;
            return true;
        }else
            return false;
    }

    public void lifeDown(){
            lives--;
    }

    public int getLives(){
        return lives;
    }
    public boolean setGhost(){
        if (!isGhost) {
            isGhost = true;
            return true;
        }else
            return false;
    }
    public boolean setKey(){
        if (!isKey) {
            isKey = true;
            return true;
        }else
            return false;
    }
    public boolean setEnd(){
        if (!isEnd) {
            isEnd = true;
            return true;
        }else
            return false;
    }
    public boolean removePacman(){
        if (isPacman) {
            isPacman = false;
            return true;
        }else
            return false;
    }
    public boolean removeGhost(){
        if (isGhost) {
            isGhost = false;
            return true;
        }else
            return false;
    }
    public boolean removeKey(){
        if (isKey) {
            isKey = false;
            return true;
        }else
            return false;
    }
    public boolean removeEnd(){
        if (isEnd) {
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
