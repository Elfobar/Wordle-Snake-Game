package com.example.demo.Menu;

import com.example.demo.SnakeGame;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LoadingScreen extends AbstractMenu {

    public LoadingScreen(){
        super("Menu");
    }

    public StackPane createContent(){
        StackPane pane = new StackPane();
        ImageView background = new ImageView(cache.getImage("Loading"));
        background.setFitWidth(SnakeGame.ROWS*SnakeGame.CELL_SIZE);
        background.setFitHeight(SnakeGame.COLUMNS*SnakeGame.CELL_SIZE + SnakeGame.HEADER_SPACE);
        pane.getChildren().add(background);
        return pane;
    }
}
