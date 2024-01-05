package com.example.demo.GameCore;
import javafx.scene.shape.Rectangle;

//Cell class makes it easier to create the grid. Our game consists of rectangles that are filled with images. Having a cell class
//gives us possibility to not enter the size twice: new Rectangle(size, size);
public class Cell extends Rectangle {
    public Cell(double size){
        super(size, size);
    }

}

