package com.example.pacman.controller;

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

    Maze mazeTmp = this;


    public  List<Field> Fields = new ArrayList<>();
    public  List<MazeObject> Objects = new ArrayList<>();

    public static ArrayList <Field> fields = new ArrayList<>();

    public int getRowsG(){
        return rowsG;
    }
    public int getColumnG(){
        return rowsG;
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
        process++;
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
                                flag = true;
                                return false;
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

}
