package com.example.demo.GameCore; // package / destination

import javafx.scene.image.Image; // imports to make class work
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class CellFactory {

    public static Cell createGridCellType1(){ // this gives us a grid cell of type 1
        return createCell(AppConfig.getImagesPathType1()); // create cell with image path type 1
    }

    public static Cell createGridCellType2(){ // this gives us a grid cell of type 2
        return createCell(AppConfig.getImagesPathType2()); // create cell with image path type 2
    }

    public static Cell createSnakeHead(){ // this gives us a snake head cell
        return createCell(AppConfig.getSnakeHeadPath()); // create cell with snake head image path
    }

    public static Cell createSnakeSegment(){ // this gives us a snake segment cell
        return createCell(AppConfig.getSnakeSegmentPath()); // create cell with snake segment image path
    }

    public static Cell createSnakeTail(){ // this gives us a snake tail cell
        return createCell(AppConfig.getSnakeTailPath()); // create cell with snake tail image path
    }

    public static Cell createObstacle(){ // this gives us an obstacle cell
        return createCell(AppConfig.getObstaclePath()); // create cell with obstacle image path
    }

    private static Cell createCell(String pathToFile){ // this gives us a cell with a specific image
        try{
            Image image = new Image(pathToFile); // try catch block to load the image
            ImagePattern imgPattern = new ImagePattern(image); // create an image pattern from the image

            Cell cell = new Cell(GameConfig.CELL_SIZE); // create a new cell
            cell.setFill(imgPattern); // fill the cell with the image pattern

            return cell; // return the cell
        } catch(Exception e){
            System.out.println("Error loading the image: " + e.getMessage()); // print error message if exception occur
            return createDefaultCell(); // return a default cell below method
        }
    }

    private static Cell createDefaultCell() { // this gives us a default cell
        Cell defaultCell = new Cell(GameConfig.CELL_SIZE); // create a new cell
        defaultCell.setFill(Color.GRAY); // fill the cell with gray color
        return defaultCell; // return the cell
    }

}