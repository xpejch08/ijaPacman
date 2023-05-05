package com.example.pacman.controller;


/**
 * @author Štěpán Pejchar xpejch08
 * @author Ondřej Češka   xceska07
 *
 * A maze object defining all the objects on the field: pacman, ghost, key, end
 */
public class MazeObject {

    public int rows; // The row coordinate of the object in the maze.
    public int cols; // The column coordinate of the object in the maze.
    public int lives = 3; // The number of lives the object has.
    public int steps = 0; // The number of steps the object has taken.
    public boolean isPacman = false; // Whether the object is a Pacman.
    public boolean isGhost = false; // Whether the object is a ghost.
    public boolean isKey = false; // Whether the object is a key.
    public boolean isEnd = false; // Whether the object is an end.
    public boolean hasKey = false; // Whether the object has a key.

    public Maze maze; // The maze the object is in.


    /**
     * Creates a MazeObject with the specified coordinates in the specified maze.
     * @param maze the maze the object is in
     * @param rows the row coordinate of the object in the maze
     * @param cols the column coordinate of the object in the maze
     */
    public MazeObject(Maze maze, int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.maze = maze;
    }

    /**
     * Returns the field the object is currently on.
     * @return the field the object is currently on
     */
    public Field getField(){
        return maze.getField(rows, cols);
    }

    /**
     * Returns whether the object can move in the specified direction.
     * @param dir the direction the object wants to move
     * @return true if the object can move in the specified direction, false otherwise
     */
    public boolean canMove(FieldInterface.Direction dir){
        return this.getField().nextField(dir).isPath;
    }

    /**
     * Moves the object in the specified direction.
     * @param dir the direction the object wants to move
     * @return true if the object was moved successfully, false otherwise
     */
    public boolean move (FieldInterface.Direction dir){
        Field next = this.getField().nextField(dir);
        MazeObject tmp = next.getObject();
        // if the game can end no one moves anymore
        if(maze.endgame){
            return false;
        }
        //asking if the object can move in the specified direction
        if (canMove(dir)) {
            //checking if next field is empty or not
            if(!next.isEmpty()) {
                //checking for pacman
                if (this.isPacman) {

                    //pacman and ghost -> pacman will respawn to the start
                    if (tmp != null && tmp.isGhost) {
                        this.respawn();

                    }

                    //next field is a key, the pacman jumps on the field and the key object deletes
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
                    //next field is the end, if the pacman has all keys the game can end, else he can't move onto the field
                    else if (next.isEnd) {
                        //has keys
                        if(this.hasKey && maze.keyCounter == 0){
                            System.out.println("ENDEDGAME");
                            next.removeOfField(tmp);
                            this.getField().removeOfField(this);
                            this.rows = next.rows;
                            this.cols = next.cols;
                        }
                    }
                    //no keys
                    else{
                        return false;
                    }
                }
                //the object moving is the ghost
                if(this.isGhost){
                    if(next.getObject() == null){
                        return false;
                    }
                    //if next object is pacman we respawn pacman
                    if(next.getObject().isPacman){
                        next.getObject().respawn();

                    }else{
                        return false;
                    }
                }
                //ghost movement
                return true;
            }
                // if next field is empty we just move the object
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
    /**
     * Respawns the object to the start of the maze.
     */
    public void respawn(){
        Field fieldSwap = maze.getStart();
        this.getField().removeOfField(this);
        this.rows = fieldSwap.rows;
        this.cols = fieldSwap.cols;
        this.lifeDown();
    }
    /**
     * Sets the object to a Pacman.
     * @return true if the object was set to a Pacman successfully, false otherwise
     */
    public boolean setPacman() {
        if (!isPacman) {
            isPacman = true;
            return true;
        }else
            return false;
    }
    /**
     * Decreases the number of lives the object has.
     */
    public void lifeDown(){
            lives--;
    }
    /**
     * Returns the number of lives the object has.
     * @return the number of lives the object has
     */
    public int getLives(){
        return lives;
    }

    /**
     * Sets the object to a ghost.
     * @return true if the object was set to a ghost successfully, false otherwise
     */
    public boolean setGhost(){
        if (!isGhost) {
            isGhost = true;
            return true;
        }else
            return false;
    }
    /**
     * Sets the object to a key.
     * @return true if the object was set to a key successfully, false otherwise
     */
    public boolean setKey(){
        if (!isKey) {
            isKey = true;
            return true;
        }else
            return false;
    }
    /**
     * Sets the object to an end.
     * @return true if the object was set to an end successfully, false otherwise
     */
    public boolean setEnd(){
        if (!isEnd) {
            isEnd = true;
            return true;
        }else
            return false;
    }

}
