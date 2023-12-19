package com.example.demo.Menu;

import com.example.demo.GameState;
import com.example.demo.ImgCache;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class AbstractMenu {
    protected ImgCache cache;

    public AbstractMenu(String cacheName){
        this.cache = new ImgCache(cacheName);
    }
    public abstract StackPane createContent();

    public ImageView getBackground(StackPane stackPane){
        ImageView background = new ImageView(cache.getImage("Background"));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        return background;
    }



}
