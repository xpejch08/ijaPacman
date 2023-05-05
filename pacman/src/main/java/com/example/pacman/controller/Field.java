package com.example.pacman.controller;
/**
 * @author Štěpán Pejchar xpejch08
 * @author Ondřej Češka   xceska07
 * Represents a single field on the game board.
 */
public class Field implements FieldInterface {

    /** The row position of the field. */
    public int rows  = 0;

    /** The column position of the field. */
    public int cols = 0;

    /** The Pacman object on the field. */
    public MazeObject Pacman = null;

    /** The Ghost object on the field. */
    public MazeObject Ghost = null;

    /** The Key object on the field. */
    public MazeObject Key = null;

    /** The End object on the field. */
    public MazeObject End = null;

    /** The Maze that the field belongs to. */
    public Maze maze;

    /** Whether the field is a path or not. */
    public boolean isPath = false;

    /** Whether the field is the starting field. */
    public boolean isStart = false;

    /** Whether the field is the end of the game field. */
    public boolean isEnd = false;

    /** Whether the field has been added to the maze. */
    public boolean inMaze;

    /** Whether the field is empty. */
    public boolean isEmpty = true;

    /**
     * Constructs a new Field object with the given row and column position.
     *
     * @param row the row position of the field
     * @param col the column position of the field
     * @param mazeParam the Maze object that the field belongs to
     */
    public Field(int row, int col, Maze mazeParam) {
        this.rows = row;
        this.cols = col;
        maze = mazeParam;
    }

    /**
     * Inserts the field into the Maze at the given row and column position.
     *
     * @param row the row position to insert the field
     * @param col the column position to insert the field
     */
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

    /**
     * Gets the row position of the field.
     *
     * @return the row position of the field
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the column position of the field.
     *
     * @return the column position of the field
     */
    public int getCols() {
        return cols;
    }

    /**

     Returns the next field in the specified direction.
     @param direction the direction to move in
     @return the next field in the specified direction
     @throws UnsupportedOperationException if an invalid direction is passed
     @throws ArrayIndexOutOfBoundsException if the next field is outside the maze
     */
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
    /**
     * Sets this field as the start field.
     */
    @Override
    public void setStart() {
        isStart = true;
    }

    /**
     * Returns the object on this field, or null if there is none.
     * The priority of the objects is Pacman->Ghost->Key->End
     *
     * @return the object on this field, or null if there is none
     */
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

    /**
     * Returns whether this field can be moved on.
     *
     * @return true if this field is a path, false otherwise
     */
    @Override
    public boolean canMoveOnField() {
        return this.isPath;
    }

    /**
     * Sets this field as a path.
     *
     * @return true if the field was not already a path, false otherwise
     */
    public boolean setPath() {
        if (!this.isPath) {
            this.isPath = true;
            return true;
        }
        return false;
    }

    /**
     * Returns whether this field is empty.
     *
     * @return true if this field is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.Pacman == null && this.Ghost == null && this.Key == null && this.isEmpty;
    }

    /**
     * Inserts the given MazeObject on this field.
     * @param obj the MazeObject to insert
     * @return true if the MazeObject was successfully inserted, false otherwise
     */
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

    /**
     * Removes all MazeObjects from this field.
     */
    @Override
    public void removeAllField() {
        Pacman = null;
        Ghost = null;
        Key = null;
        End = null;
    }

    /**
     * Removes the given MazeObject from this field.
     * @param obj the MazeObject to remove
     */
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
