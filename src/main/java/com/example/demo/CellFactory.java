package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;

public class CellFactory {

    public static Cell createGridCellType1(){
        return createCell(AppConfig.getImagesPathType1());
    }

    public static Cell createGridCellType2(){
        return createCell(AppConfig.getImagesPathType2());
    }

    public static Cell createSnakeHead(){
        return createCell(AppConfig.getSnakeHeadPath());
    }

    public static Cell createSnakeSegment(){
        return createCell(AppConfig.getSnakeSegmentPath());
    }

    public static Cell createSnakeTail(){
        return createCell(AppConfig.getSnakeTailPath());
    }

        public static Cell createObstacle(){
        return createCell(AppConfig.getObstaclePath());
    }

    private static Cell createCell(String pathToFile){
        try{
            Image image = new Image(pathToFile);
            ImagePattern imgPattern = new ImagePattern(image);

            Cell cell = new Cell(SnakeConfig.CELL_SIZE);
            cell.setFill(imgPattern);

            return cell;
        } catch(Exception e){
            System.out.println("Error loading the image: " + e.getMessage());
            return createDefaultCell();
        }
    }

    private static Cell createDefaultCell() {
        Cell defaultCell = new Cell(SnakeConfig.CELL_SIZE);
        defaultCell.setFill(Color.GRAY);
        return defaultCell;
    }
}
