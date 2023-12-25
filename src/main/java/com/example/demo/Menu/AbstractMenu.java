package com.example.demo.Menu;
import com.example.demo.ImgCache;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class AbstractMenu {
    protected ImgCache cache;

    public AbstractMenu(){
        this.cache = new ImgCache("Menu");
    }
    public abstract StackPane createContent();

    public ImageView getBackground(StackPane stackPane){
        ImageView background = new ImageView(cache.getImage("Background"));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        return background;
    }
}
