package com.example.demo.Menu;

import com.example.demo.SnakeConfig;
import com.example.demo.SnakeGame;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LoadingScreen extends AbstractMenu {

    public LoadingScreen(){
        super();
    }

    public StackPane createContent(){
        StackPane pane = new StackPane();
        ImageView background = new ImageView(cache.getImage("Loading"));
        background.setFitWidth(SnakeConfig.ROWS*SnakeConfig.CELL_SIZE);
        background.setFitHeight(SnakeConfig.COLUMNS*SnakeConfig.CELL_SIZE + SnakeConfig.HEADER_SPACE);
        pane.getChildren().add(background);
        return pane;
    }
}
