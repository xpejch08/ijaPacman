package com.example.pacman.controller;

import com.example.pacman.view.Ghost;
import com.example.pacman.view.HUD;
import com.example.pacman.view.PacmanView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    MazeObject Key;

    public PacmanView pac;
    public List <MazeObject> Ghosts = new ArrayList<>();

    public List <Ghost> GhostViews = new ArrayList<>();
    public int ghostCounter = 0;
    MazeObject tmp;
    public int diff;
    public double speed;

    public  List<Field> Fields = new ArrayList<>();
    public  List<MazeObject> Objects = new ArrayList<>();

    public static ArrayList <Field> fields = new ArrayList<>();

    public int getRowsG(){
        return rowsG;
    }
    public int getColumnG(){
        return rowsG;
    }

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
    public MazeObject getKey(int NUM_ROWS, int NUM_COLS) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tmp = this.getField(row, col).getObject();
                if (tmp != null) {
                    if (tmp.isKey) {
                        Key = tmp;
                        return Key;
                    }
                }
            }
        }
        return PacMan;
    }
    public void getGhosts(int NUM_ROWS, int NUM_COLS) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                tmp = this.getField(row, col).getObject();
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

    @Override
    public boolean processLine(String line){
        iterationRows++;
        if(iterationRows > rowsG){
            flag = true;
            return false;
        }
        else {
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
                                        Field field = new Field(iterationRows, stringIndex + 1, mazeTmp);
                                        MazeObject key = new MazeObject(mazeTmp, iterationRows, stringIndex + 1);
                                        key.setKey();
                                        field.setPath();
                                        field.Key = key;
                                        field.insertInMaze(iterationRows, stringIndex + 1);
                                    }
                                }
                                else{
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
                            //field generate
                            else {
                                Field field = new Field(iterationRows, stringIndex + 1, mazeTmp);
                                MazeObject pacman = new MazeObject(mazeTmp, iterationRows, stringIndex+1);
                                pacman.setPacman();
                                field.setPath();
                                field.setStart();
                                field.Pacman = pacman;
                                field.insertInMaze(iterationRows, stringIndex + 1);
                                //pacman.insertInObjectArray(iterationRows, stringIndex+1);
                                //PathField Path = new PathField(iterationRows, stringIndex + 1);
                                //Path.insertInMaze(iterationRows, stringIndex + 1);
                            }
                        } else {
                            Field Path = new Field(iterationRows, stringIndex + 1, mazeTmp);
                            Path.setPath();
                            Path.insertInMaze(iterationRows, stringIndex + 1);
                        }
                    } else {
                        Field Wall = new Field(iterationRows, stringIndex + 1, mazeTmp);
                        Wall.insertInMaze(iterationRows, stringIndex + 1);
                    }
                }else {

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

    @Override
    public boolean readSource() {
        boolean firstLineFlag = false;
        String maze = "maze1.txt";
        try{
            File mazeSource = new File("../pacman/src/mazes/maze1.txt");
            //for(String fileNames : mazeSource.list()) System.out.println(fileNames);
            Scanner reader = new Scanner(mazeSource);
            while (reader.hasNextLine()){
                if(!firstLineFlag){
                    firstLineFlag = true;
                    String data = reader.nextLine();
                    String[] parts = data.split(" ");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    this.startReading(x, y);
                }
                String data = reader.nextLine();
                this.processLine(data);
                System.out.println(data);
            }
            this.stopReading();
            reader.close();
            return true;
        }catch (FileNotFoundException e){
            System.out.println("Cant open file\n");
            e.printStackTrace();
            return false;
        }
    }

    public boolean readSourceLog(int log) {
        boolean firstLineFlag = false;
        try{
            File logFolder = new File("../pacman/log");
            if (logFolder.exists() && logFolder.isDirectory()) {
                File[] logFiles = logFolder.listFiles();
                logFilesCount = logFiles.length;
            }
            File mazeSource = new File("../pacman/log/" + log + ".txt");
            //for(String fileNames : mazeSource.list()) System.out.println(fileNames);
            Scanner reader = new Scanner(mazeSource);
            while (reader.hasNextLine()){
                if(!firstLineFlag){
                    firstLineFlag = true;
                    String data = reader.nextLine();
                    String[] parts = data.split(" ");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    this.startReading(x, y);
                }
                String data = reader.nextLine();
                this.processLine(data);
                System.out.println(data);
            }
            this.stopReading();
            reader.close();
            return true;
        }catch (FileNotFoundException e){
            System.out.println("Cant open file\n");
            e.printStackTrace();
            return false;
        }
    }


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

    public Maze createMaze(){
        return this;
    }
    public void addField(Field field) {
        Fields.add(field);
    }
    public void startGhosts(){

        for (int j =0; j<this.ghostCounter; j++){
            this.GhostViews.get(j).startTimeline();
        }
    }
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
}
