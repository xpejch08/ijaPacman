package com.example.pacman.controller;

import com.example.pacman.view.Ghost;
import com.example.pacman.view.HUD;
import com.example.pacman.view.PacmanView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Štěpán Pejchar xpejch08
 * @author Ondřej Češka   xceska07
 * A Maze is an object that contains information about the game board for Pac-Man.
 */
public class Maze implements MazeInterface{

    static int rowsG = 0;
    static int columnG = 0;
    static int iterationRows = 0;
    static int process = 1;
    static boolean flag = false;

    public int logFilesCount;
    Maze mazeTmp = this;
    Field startField;
    MazeObject PacMan;
    Field endField;
    MazeObject Key;
    public Log thislog;
    public PacmanView pac;
    public List <MazeObject> Ghosts = new ArrayList<>();
    public List <MazeObject> Keys = new ArrayList<>();
    public List <Ghost> GhostViews = new ArrayList<>();
    public int ghostCounter = 0;
    public int keyCounter = 0;
    MazeObject tmp;
    public int diff;
    public double speed;
    public boolean endgame = false;
    public  List<Field> Fields = new ArrayList<>();
    public  List<MazeObject> Objects = new ArrayList<>();
    public static ArrayList <Field> fields = new ArrayList<>();

    /**
     * Returns the number of rows in the Maze.
     * @return The number of rows.
     */
    public int getRowsG(){
        return rowsG;
    }

    /**
     * Returns the number of columns in the Maze.
     * @return The number of columns.
     */
    public int getColumnG(){
        return rowsG;
    }

    /**
     * Finds and returns the starting position for Pac-Man.
     * @return The starting position for Pac-Man.
     */
    public Field getStart() {
        for (int row = 0; row < this.getRowsG() + 2; row++) {
            for (int col = 0; col < this.getColumnG() + 2; col++) {
                if (this.getField(row, col) != null && this.getField(row, col).isStart) {
                    startField = this.getField(row, col);
                    return startField;
                }
            }
        }
        return startField;
    }

    /**
     * Finds and returns the position of Pac-Man.
     * @param NUM_ROWS The number of rows in the Maze.
     * @param NUM_COLS The number of columns in the Maze.
     * @return The position of Pac-Man.
     */
    public MazeObject getPacMan(int NUM_ROWS, int NUM_COLS) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tmp = this.getField(row, col).getObject();
                if (tmp != null) {
                    if (tmp.isPacman) {
                        PacMan = tmp;
                        return PacMan;
                    }
                }
            }
        }
        return PacMan;
    }

    /**
     * Finds and returns the position of the key.
     * @param NUM_ROWS The number of rows in the Maze.
     * @param NUM_COLS The number of columns in the Maze.
     * @return The position of the key.
     */
    public MazeObject getKeys(int NUM_ROWS, int NUM_COLS) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tmp = this.getField(row, col).getObject();
                if (tmp != null) {
                    if (tmp.isKey) {
                        Key = tmp;
                        Keys.add(Key);
                    }
                }
            }
        }
        return Key;
    }
    /**
     * Finds and returns the position of the key.
     * @return the Field object of the end point
     */
    public Field getEnd() {
        Field endField = null;
        for (int row = 0; row < this.getRowsG() + 2; row++) {
            for (int col = 0; col < this.getColumnG() + 2; col++) {
                if (this.getField(row, col) != null && this.getField(row, col).isEnd) {
                    endField = this.getField(row, col);
                    return endField;
                }
            }
        }
        return endField;
    }

    /**
     * Populates the Ghosts array list with up to four ghost objects found in the maze.
     *
     * @param NUM_ROWS the number of rows in the maze
     * @param NUM_COLS the number of columns in the maze
     */
    public void getGhosts(int NUM_ROWS, int NUM_COLS) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                MazeObject tmp = this.getField(row, col).getObject();
                if (tmp != null) {
                    if (tmp.isGhost) {
                        if (ghostCounter == 0) {
                            ghostCounter++;
                            Ghosts.add(tmp);

                        } else if (ghostCounter == 1) {
                            ghostCounter++;
                            Ghosts.add(tmp);

                        } else if (ghostCounter == 2) {
                            ghostCounter++;
                            Ghosts.add(tmp);

                        } else if (ghostCounter == 3) {
                            ghostCounter++;
                            Ghosts.add(tmp);

                        }
                    }
                }
            }
        }
    }

    /**
     * Starts the reading process for the maze, generating Field objects for the walls surrounding the maze.
     *
     * @param rows the number of rows in the maze
     * @param cols the number of columns in the maze
     */
    @Override
    public void startReading(int rows, int cols){
        mazeTmp.Fields.clear();
        for (int colsLoop = 0; colsLoop < cols+2; colsLoop++){

            Field wall = new Field(0, colsLoop, mazeTmp);
            wall.insertInMaze (0, colsLoop);
        }
        for (int colsLoop = 0; colsLoop < cols+2; colsLoop++){

            Field wall = new Field(rows+1, colsLoop, mazeTmp);
            wall.insertInMaze (rows+1, colsLoop);
        }
        for (int rowsLoop = 1; rowsLoop < rows+1; rowsLoop++){
            Field wall = new Field(rowsLoop, 0, mazeTmp);
            wall.insertInMaze (rowsLoop, 0);
        }
        for (int rowsLoop = 1; rowsLoop < rows+1; rowsLoop++){
            Field wall = new Field(rowsLoop, cols+1, mazeTmp);
            wall.insertInMaze (rowsLoop, cols+1);
        }

        /**
         *   0 1 2 3 4
         * 0 w w w w w
         * 1 w p p p W
         * 2 w p p p w
         * 3 w p p p w
         * 4 w p p p W
         * 5 W w w w w
         */
        rowsG = rows;
        columnG = cols;
    }

    /**
     * This method processes a single line of the maze by creating Field objects and
     * MazeObject objects based on the characters in the line.
     *
     * @param line The current line being processed.
     * @return A boolean value indicating whether to continue reading lines.
     */
    @Override
    public boolean processLine(String line){
        iterationRows++;
        if(iterationRows > rowsG){
            flag = true;
            return false;
        }
        else {
            //loop checking which symbol it takes
            for (int stringIndex = 0; stringIndex<line.length(); stringIndex++) {
                if (line.charAt(stringIndex) != 'G') {
                    if (line.charAt(stringIndex) != 'X') {
                        if (line.charAt(stringIndex) != '.') {
                            if (line.charAt(stringIndex) != 'S') {
                                if (line.charAt(stringIndex) != 'T') {
                                    if (line.charAt(stringIndex) != 'K') {
                                        flag = true;
                                        return false;
                                    }
                                    else{
                                        // setting Key
                                        Field field = new Field(iterationRows, stringIndex + 1, mazeTmp);
                                        MazeObject key = new MazeObject(mazeTmp, iterationRows, stringIndex + 1);
                                        key.setKey();
                                        keyCounter++;
                                        field.setPath();
                                        field.Key = key;
                                        field.insertInMaze(iterationRows, stringIndex + 1);
                                    }
                                }
                                else{
                                    // setting Target
                                    Field field = new Field(iterationRows, stringIndex + 1, mazeTmp);
                                    MazeObject target = new MazeObject(mazeTmp, iterationRows, stringIndex+1);
                                    target.setEnd();
                                    field.End = target;
                                    field.isEnd = true;
                                    field.isEmpty = false;
                                    field.setPath();
                                    field.insertInMaze(iterationRows, stringIndex + 1);
                                }
                            }
                            else {
                                //setting start
                                Field field = new Field(iterationRows, stringIndex + 1, mazeTmp);
                                MazeObject pacman = new MazeObject(mazeTmp, iterationRows, stringIndex+1);
                                pacman.setPacman();
                                field.setPath();
                                field.setStart();
                                field.Pacman = pacman;
                                field.insertInMaze(iterationRows, stringIndex + 1);
                            }
                        } else {
                            //setting path
                            Field Path = new Field(iterationRows, stringIndex + 1, mazeTmp);
                            Path.setPath();
                            Path.insertInMaze(iterationRows, stringIndex + 1);
                        }
                    } else {
                        //setting wall
                        Field Wall = new Field(iterationRows, stringIndex + 1, mazeTmp);
                        Wall.insertInMaze(iterationRows, stringIndex + 1);
                    }
                }else {
                    //setting ghost
                    Field Ghost = new Field(iterationRows, stringIndex + 1, mazeTmp);
                    MazeObject ghostObject = new MazeObject(mazeTmp, iterationRows, stringIndex+1);
                    ghostObject.setGhost();
                    Ghost.setPath();
                    Ghost.Ghost = ghostObject;
                    Ghost.insertInMaze(iterationRows, stringIndex + 1);
                }
            }
        }
        return true;
    }

    /**
     * This method determines when to stop reading lines based on whether the number
     * of lines processed exceeds the number of rows specified in the maze.
     *
     * @return A boolean value indicating whether to continue reading lines.
     */
    @Override
    public boolean stopReading(){
        if(iterationRows < rowsG){
            iterationRows = 0;
            flag = true;
            return false;
        }
        iterationRows = 0;
        return true;
    }

    /**
     * Reads the maze source file and initializes the maze according to the data.
     *
     * @return True if reading and initialization is successful, False otherwise.
     */
    @Override
    public boolean readSource() {
        boolean firstLineFlag = false;
        String maze = "maze1.txt";
        try {
            File mazeSource = new File("../pacman/src/mazes/maze1.txt");
            Scanner reader = new Scanner(mazeSource);
            // Read the file line by line
            while (reader.hasNextLine()) {
                if(!firstLineFlag){
                    firstLineFlag = true;
                    String data = reader.nextLine();
                    String[] parts = data.split(" ");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    this.startReading(x, y);
                }
                // Parse the rest of the file to fill in the maze with Fields
                String data = reader.nextLine();
                this.processLine(data);
                System.out.println(data);
            }
            this.stopReading();
            reader.close();
            return true;
        } catch (FileNotFoundException e) {
            // Catch and print the exception if file not found
            System.out.println("Cant open file\n");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reads the log file with the given log number and initializes the maze according to the data.
     *
     * @param log The number of the log file to read
     * @return True if reading and initialization is successful, False otherwise.
     */
    public boolean readSourceLog(int log) {
        boolean firstLineFlag = false;
        try {
            File logFolder = new File("../pacman/data/log");
            if (logFolder.exists() && logFolder.isDirectory()) {
                File[] logFiles = logFolder.listFiles();
                logFilesCount = logFiles.length;
            }
            File mazeSource = new File("../pacman/data/log/" + log + ".txt");
            Scanner reader = new Scanner(mazeSource);
            // Read the file line by line
            while (reader.hasNextLine()) {
                if(!firstLineFlag){
                    firstLineFlag = true;
                    String data = reader.nextLine();
                    String[] parts = data.split(" ");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    this.startReading(x, y);
                }
                // Parse the rest of the file to fill in the maze with Fields
                String data = reader.nextLine();
                this.processLine(data);
                System.out.println(data);
            }
            this.stopReading();
            reader.close();
            return true;
        } catch (FileNotFoundException e) {
            // Catch and print the exception if file not found
            System.out.println("Cant open file\n");
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Returns the Field object at the specified row and column position
     *
     * @param row the row index of the desired Field object
     * @param col the column index of the desired Field object
     * @return the Field object at the specified position, or null if not found
     */
    @Override
    public Field getField(int row, int col) {
        for (Field field : mazeTmp.Fields) {
            if (field != null) {
                if ((field.getRows() == row) && (field.getCols() == col)) {
                    return field;
                }
            }
        }
        return null;
    }

    /**
     * Returns the current Maze object
     *
     * @return the current Maze object
     */
    public Maze createMaze(){
        return this;
    }

    /**
     * Adds the specified Field object to the Maze object's Fields list
     *
     * @param field the Field object to add to the Fields list
     */
    public void addField(Field field) {
        Fields.add(field);
    }

    /**
     * Starts the timeline animation for each GhostView object in the Maze object's GhostViews list
     */
    public void startGhosts(){
        for (int j =0; j<this.ghostCounter; j++){
            this.GhostViews.get(j).startTimeline();
        }
    }

    /**
     * Sets the speed of the game animation based on the specified difficulty level
     *
     * @param diff the difficulty level of the game (1 = easy, 2 = medium, 3 = hard)
     */
    public void setSpeed(int diff){
        this.diff = diff;

        if (diff == 1){
            speed = 0.5;
        }
        if (diff == 2){
            speed = 0.35;
        }
        if (diff == 3){
            speed = 0.2;
        }
    }

    /**
     * Sets the log file for the Maze object to the specified Log object
     *
     * @param log the Log object to set as the current log file
     */
    public void setLog(Log log){
        thislog = log;
    }

    /**
     * Stops the game by setting the endgame flag to true
     */
    public void stopgame(){
        this.endgame = true;
    }
}
