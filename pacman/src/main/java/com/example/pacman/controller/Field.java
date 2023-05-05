package com.example.pacman.controller;

public class Field implements FieldInterface {
    public int rows  = 0;
    public int cols = 0;
    public MazeObject Pacman = null;
    public MazeObject Ghost = null;
    public MazeObject Key = null;
    public MazeObject End = null;

    public Maze maze;

    public boolean isPath = false;
    public boolean isStart = false;

    public boolean isEnd = false;
    public boolean inMaze;
    public int type = 0;
    public boolean isEmpty = true;
    public int changedModel = 0;

    /**
     * Pathifield setter
     * @param row
     * @param col
     */
    public Field(int row, int col, Maze mazeParam) {
        this.rows = row;
        this.cols = col;
        maze = mazeParam;
    }

    public void insertInMaze(int row, int col){
        if(isStart){
            isEmpty = false;
        }
        inMaze = true;
        int index = 0;
        for (Field field : maze.Fields) {
            if ((this.getRows() == row) && (this.getCols() == col)) {

                maze.Fields.add(this);
                return;
            }
            index++;
        }
        maze.Fields.add(this);
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    @Override
    public Field nextField(Direction direction) {
        int row = this.rows;
        int col = this.cols;
        switch (direction) {
            case L:
                col--;
                break;
            case U:
                row--;
                break;
            case R:
                col++;
                break;
            case D:
                row++;
                break;
            default:
                throw new UnsupportedOperationException("Invalid direction" + direction);
        }
        for (Field field : maze.Fields) {
            if (field.rows == row && field.cols == col) {
                return field;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override public void setStart(){
        isStart = true;
    }

    @Override
    public MazeObject getObject() {
        if (this.Pacman != null) {
            return this.Pacman;
        }
        if (this.Ghost != null) {
            return this.Ghost;
        }
        if (this.Key != null) {
            return this.Key;
        }
        if (this.End != null) {
            return this.End;
        }
        return null;
    }

    @Override
    public boolean canMoveOnField() {
        return this.isPath;
    }

    public boolean setPath(){
        if (!this.isPath){
            this.isPath = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.Pacman == null && this.Ghost == null && this.Key == null && this.isEmpty;
    }

    @Override
    public boolean insertOnField(MazeObject obj) {
        if (obj.isEnd){
            this.End = obj;
            return true;
        }
        if (obj.isKey){
            this.Key = obj;
            return true;
        }
        if (obj.isGhost){
            this.Ghost = obj;
            return true;
        }
        if (obj.isPacman){
            this.Pacman = obj;
            return true;
        }
        return false;
    }

    @Override
    public void removeAllField() {
        Pacman = null;
        Ghost = null;
        Key = null;
        End = null;
    }

    public void removeOfField(MazeObject obj){
        if (obj.isPacman){
            Pacman = null;
            obj.getField().isPath = true;
        }
        if (obj.isGhost){
            Ghost = null;
            obj.getField().isPath = true;
        }
        if (obj.isKey){
            Key = null;
            obj.getField().isPath = true;
            obj.getField().isEmpty = true;
        }
        if (obj.isEnd){
            End = null;
            obj.getField().isPath = true;
        }

    }
}
