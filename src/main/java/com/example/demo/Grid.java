package com.example.demo;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Grid {
    private final int CELL_SIZE;
    private final int ROWS;
    private final int COLUMNS;
    private GridPane grid;

    public Grid(int rows, int columns, int cellSize){
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.CELL_SIZE = cellSize;
        initializeGrid();
    }

    public void initializeGrid(){
        Cell cell = null;
        grid = new GridPane();
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                if((row + col) % 2 == 0){
                    cell = createCell(Color.DARKORANGE, row, col);
                } else{
                    cell = createCell(Color.IVORY, row, col);
                }
                grid.add(cell, row, col);
            }
        }
    }

    public Cell createCell(Color color, int row, int col){
        Cell cell = new Cell(CELL_SIZE, CellType.EMPTY, color, row, col);
        return cell;
    }

    public GridPane getGrid(){
        return this.grid;
    }
}
