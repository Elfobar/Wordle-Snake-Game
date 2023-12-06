package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
    private Coordinate coordinate;

    public Cell(double size, Color color, int ROW, int COLUMN){
        super(size, size, color);
        this.coordinate = new Coordinate(ROW, COLUMN);

    }

    public Coordinate getCoordinate(){
        return this.coordinate;
    }

}