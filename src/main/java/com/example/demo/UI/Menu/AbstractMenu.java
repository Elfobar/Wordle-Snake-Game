package com.example.demo.UI.Menu;
import com.example.demo.UI.ImgCache;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

//AbstractMenu serves as the base for all the other menus in the game by providing common functionality and an abstract method that
//every subclass that inherits it, needs to implement.
public abstract class AbstractMenu {
    protected ImgCache cache;

    //Initializes the ImgCache with menu images.
    public AbstractMenu(){
        this.cache = new ImgCache("Menu");
    }
    public abstract StackPane createContent();

    //4 menus share the same background, so we decided to move this method here to reduce code duplication
    public ImageView getBackground(StackPane stackPane){
        ImageView background = new ImageView(cache.getImage("Background"));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        return background;
    }
}
