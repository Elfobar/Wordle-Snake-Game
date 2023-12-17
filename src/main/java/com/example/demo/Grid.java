package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Objects;

public class Grid {
    private GridPane grid;
    private final String tile1 = "/images/Tile1.png";
    private final String tile2 = "/images/Tile2.png";
    private final Image tile1Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tile1)));
    private final Image tile2Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tile2)));

    public Grid(){
        initializeGrid();
    }

    public void initializeGrid(){
        Cell cell = null;
        grid = new GridPane();
        for(int row = 0; row < SnakeGame.ROWS; row++){
            for(int col = 0; col < SnakeGame.COLUMNS; col++){
                if((row + col) % 2 == 0){
                    cell = createCell(Color.DARKORANGE, row, col);
                    cell.setFill(new ImagePattern(tile1Img));
                } else{
                    cell = createCell(Color.IVORY, row, col);
                    cell.setFill(new ImagePattern(tile2Img));
                }
                grid.add(cell, row, col);
            }
        }
    }

    public Cell createCell(Color color, int row, int col){
        Cell cell = new Cell(SnakeGame.CELL_SIZE, color, row, col);
        return cell;
    }

    public GridPane getGrid(){
        return this.grid;
    }
}
