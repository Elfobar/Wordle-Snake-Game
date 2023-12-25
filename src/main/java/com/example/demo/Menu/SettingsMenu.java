package com.example.demo.Menu;

import com.example.demo.SnakeConfig;
import com.example.demo.SnakeGame;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsMenu extends AbstractMenu {

    public SettingsMenu(){
        super();
    }

    public StackPane createContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(SnakeConfig.ROWS*SnakeConfig.CELL_SIZE, SnakeConfig.COLUMNS*SnakeConfig.CELL_SIZE + SnakeConfig.HEADER_SPACE);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox settingsBox = new VBox();
        settingsBox.setAlignment(Pos.TOP_CENTER);
        settingsBox.setPrefHeight(200);
        settingsBox.setPrefWidth(100);

        ImageView label = new ImageView(cache.getImage("SettingsLabel"));
        label.setPickOnBounds(true);
        label.setPreserveRatio(true);
        label.setFitWidth(550);
        label.setFitHeight(150);

        ImageView frame = new ImageView(cache.getImage("SettingsFrame"));
        frame.setFitWidth(700.0);
        frame.setFitHeight(400.0);

        ImageView back = getBackButton();

        settingsBox.getChildren().add(label);
        settingsBox.getChildren().add(frame);
        settingsBox.getChildren().add(back);

        stackPane.getChildren().add(settingsBox);

        return stackPane;
    }

    private ImageView getBackButton(){

        ImageView back = new ImageView(cache.getImage("ArrowsImg"));
        back.setId("back");
        back.setFitWidth(150.0);
        back.setFitHeight(130.0);
        back.setPreserveRatio(true);

        return back;
    }

}
