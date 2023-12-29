package com.example.demo.Menu;

import com.example.demo.SnakeConfig;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameOverScreen extends AbstractMenu {

    public GameOverScreen(){
        super();
    }

    public StackPane createContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(SnakeConfig.ROWS*SnakeConfig.CELL_SIZE, SnakeConfig.COLUMNS*SnakeConfig.CELL_SIZE + SnakeConfig.HEADER_SPACE);
        ImageView background = new ImageView(cache.getImage("GameOver"));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        stackPane.getChildren().add(background);

        return stackPane;
    }
}
