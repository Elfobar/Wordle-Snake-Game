package com.example.demo.Menu;

import com.example.demo.SnakeGame;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameOverScreen extends AbstractMenu {

    public GameOverScreen(){
        super("Menu");
    }

    public StackPane createContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(SnakeGame.ROWS*SnakeGame.CELL_SIZE, SnakeGame.COLUMNS*SnakeGame.CELL_SIZE + SnakeGame.HEADER_SPACE);
        ImageView background = new ImageView(cache.getImage("GameOver"));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        stackPane.getChildren().add(background);

        return stackPane;
    }
}
