package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{

    private CellType cellType;
    private int ROW;
    private int COLUMN;


    public Cell(double size, CellType cellType, Color color, int ROW, int COLUMN){
        super(size, size, color);
        this.cellType = cellType;
        this.ROW = ROW;
        this.COLUMN = COLUMN;
    }

    public CellType getCellType(){
        return this.cellType;
    }

    public void setCellType(CellType cellType){
        this.cellType = cellType;
    }

    public Cell getCell(){
        return this;
    }

    public int getRow(){
        return this.ROW;
    }

    public int getColumn(){
        return this.COLUMN;
    }

    public void setRow(int row){
        this.ROW = row;
    }

    public void setColumn(int column){
        this.COLUMN = column;
    }
}