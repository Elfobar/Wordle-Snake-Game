package com.example.demo.UI.Menu;
import com.example.demo.UI.ImgCache;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class AbstractMenu {
    protected ImgCache cache;

    public AbstractMenu(){ // Concstructor
        this.cache = new ImgCache("Menu");
    }
    public abstract StackPane createContent();

    public ImageView getBackground(StackPane stackPane){ // Generates the background using a image
        ImageView background = new ImageView(cache.getImage("Background"));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        return background;
    }
}
