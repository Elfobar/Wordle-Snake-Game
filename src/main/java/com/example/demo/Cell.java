package com.example.demo;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends Rectangle {
    private Coordinate coordinate;

    public Cell(double size, Color color, int ROW, int COLUMN) {
        super(size, size, color);
        this.coordinate = new Coordinate(ROW, COLUMN);
    }
    public Cell(double size, Coordinate coordinate){
        super(size, size);
        this.coordinate = coordinate;
    }

    public Cell(double size){
        super(size, size);
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

}

