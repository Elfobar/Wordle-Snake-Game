package com.example.demo;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends Rectangle {
    private Coordinate coordinate;
    private Text text;

    public Cell(double size, Color color, int ROW, int COLUMN) {
        super(size, size, color);
        this.coordinate = new Coordinate(ROW, COLUMN);

        this.text = new Text();
        this.text.setFill(Color.WHITE);
        this.text.setFont(Font.font(size * 0.6));
        this.text.setStyle("-fx-alignment: center;");
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Text getText() {
        return text;
    }
}

