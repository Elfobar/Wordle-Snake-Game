package com.example.demo.UI;


import com.example.demo.GameCore.Cell;
import com.example.demo.GameCore.CellFactory;
import com.example.demo.GameCore.GameConfig;
import com.example.demo.GameCore.Obstacle;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grid {

    private GridPane grid;

    public Grid(){
        this.grid = initializeGrid();

    }

    public GridPane initializeGrid(){
        GridPane grid = new GridPane();
        Cell gridCell;
        for(int row = 0; row < GameConfig.ROWS; row++){
            for(int col = 0; col < GameConfig.COLUMNS; col++){
                gridCell = createGridCell(row, col);
                grid.add(gridCell, row, col);
            }
        }
        return grid;
    }

    private Cell createGridCell(int row, int col){
        if((row + col) % 2 == 0){
            return CellFactory.createGridCellType1();
        } else{
            return CellFactory.createGridCellType2();
        }
    }

    public void add(Cell cell, int x, int y) {
        grid.add(cell, x, y);
    }

    public void add(Text text, int x, int y) {
        grid.add(text, x, y);
        GridPane.setHalignment(text, HPos.CENTER);
    }

    public void clearGrid(LinkedList<Cell> snakeBody, ArrayList<Text> letters){
        grid.getChildren().removeAll(snakeBody);
        grid.getChildren().removeAll(letters);
    }

//    public void add(Obstacle obstacle, int x, int y) {
//        grid.add(obstacle, x, y);
//    }

    public void addObstacle(Obstacle obstacle, int x, int y) {
        grid.add(obstacle, x, y);
    }

    public void remove(Cell cell) {
        grid.getChildren().remove(cell);
    }

    public void remove(Text text){
        grid.getChildren().remove(text);
    }

    public GridPane getGrid(){
        return this.grid;
    }


}
