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
        GridPane grid = new GridPane(); //GridPane is used as ir displays children in a grid, equal height and width
        Cell gridCell;
        //Cells are added one by one row
        //First loop iterates over rows, second loop iterates over columns
        for(int row = 0; row < GameConfig.ROWS; row++){
            for(int col = 0; col < GameConfig.COLUMNS; col++){
                gridCell = createGridCell(row, col);
                grid.add(gridCell, row, col);
            }
        }
        return grid; //GridPane is returned with added cells
    }

    private Cell createGridCell(int row, int col){
        if((row + col) % 2 == 0){ //Image that represents the cell is determined by modulo function (if sum of row and column even, then Type1, otherwise Type2)
            return CellFactory.createGridCellType1();
        } else{
            return CellFactory.createGridCellType2();
        }
    }

    public void add(Cell cell, int x, int y) {
        grid.add(cell, x, y); //Cell is added, row is x-coordinate, column is y-coordinate
    }

    public void add(Text text, int x, int y) {
        grid.add(text, x, y);
        GridPane.setHalignment(text, HPos.CENTER); //Letter is added, used by GameRenderer class within drawLetters method
    }

    public void clearGrid(LinkedList<Cell> snakeBody, ArrayList<Text> letters){
        grid.getChildren().removeAll(snakeBody); //Removes all snake cells from the grid when game is reset
        grid.getChildren().removeAll(letters); //Removes all letter cells from the grid when game is reset
    }

    public void remove(Cell cell) {
        grid.getChildren().remove(cell); //Removes snake cell from the grid
    }

    public void remove(Text text){
        grid.getChildren().remove(text); //Removes letter cell from the grid
    }

    public GridPane getGrid(){
        return this.grid;
    }


}
