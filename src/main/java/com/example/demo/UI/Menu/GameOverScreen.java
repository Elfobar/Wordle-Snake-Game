package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameOverScreen extends AbstractMenu {

    private static final String GAME_OVER_BACKGROUND_IMAGE = "GameOver";

    public GameOverScreen(){
        super();
    }

    public StackPane createContent(){
        StackPane stackPane = new StackPane();
        ImageView background = new ImageView(cache.getImage(GAME_OVER_BACKGROUND_IMAGE));
        background.setFitWidth(GameConfig.WIDTH);
        background.setFitHeight(GameConfig.HEIGHT);
        stackPane.getChildren().add(background);

        return stackPane;
    }
}
