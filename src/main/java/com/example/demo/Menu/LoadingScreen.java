package com.example.demo.Menu;

import com.example.demo.GameConfig;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LoadingScreen extends AbstractMenu {

    private static final String LOADING_SCREEN_IMAGE = "Loading";

    public LoadingScreen(){
        super();
    }

    public StackPane createContent(){
        StackPane pane = new StackPane();
        ImageView background = new ImageView(cache.getImage(LOADING_SCREEN_IMAGE));
        background.setFitWidth(GameConfig.WIDTH);
        background.setFitHeight(GameConfig.HEIGHT);
        pane.getChildren().add(background);
        return pane;
    }
}
