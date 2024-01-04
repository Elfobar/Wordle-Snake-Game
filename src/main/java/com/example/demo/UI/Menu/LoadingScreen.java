package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class LoadingScreen extends AbstractMenu {

    private static final String LOADING_SCREEN_IMAGE = "Loading";

    //Constructor of the AbstractMenu is used, ImageCache is inherited
    public LoadingScreen(){
        super();
    }

    public StackPane createContent(){
        StackPane pane = new StackPane(); //StackPane is used according to the definition of an abstract method createContent in AbstractMenu
        ImageView background = new ImageView(cache.getImage(LOADING_SCREEN_IMAGE)); //Image is returned my Image cache (preloaded by image cache)
        background.setFitWidth(GameConfig.WIDTH);
        background.setFitHeight(GameConfig.HEIGHT);
        pane.getChildren().add(background); //Background is added to the StackPane
        return pane;
    }
}
