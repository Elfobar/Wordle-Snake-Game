package com.example.demo.Menu;

import com.example.demo.GameConfig;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsMenu extends AbstractMenu {

    private static final double SETTINGS_BOX_WIDTH = 200;
    private static final double SETTINGS_BOX_HEIGHT = 100;
    private static final String SETTINGS_LABEL_IMAGE = "SettingsLabel";
    private static final double SETTINGS_LABEL_WIDTH = 550;
    private static final double SETTINGS_LABEL_HEIGHT = 150;
    private static final String SETTINGS_FRAME_IMAGE = "SettingsFrame";
    private static final double SETTINGS_FRAME_WIDTH = 700;
    private static final double SETTINGS_FRAME_HEIGHT = 400;
    private static final String BACK_BUTTON_IMAGE = "ArrowsImg";
    private static final double BACK_BUTTON_WIDTH = 150;
    private static final double BACK_BUTTON_HEIGHT = 130;

    public SettingsMenu(){
        super();
    }

    public StackPane createContent(){
        StackPane stackPane = createStackPane();
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox settingsBox = createSettingsBox();

        stackPane.getChildren().add(settingsBox);

        return stackPane;
    }

    private StackPane createStackPane() {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT);
        return stackPane;
    }

    private VBox createSettingsBox() {
        VBox settingsBox = new VBox();
        settingsBox.setAlignment(Pos.TOP_CENTER);
        settingsBox.setPrefHeight(SETTINGS_BOX_WIDTH);
        settingsBox.setPrefWidth(SETTINGS_BOX_HEIGHT);

        ImageView label = createImageView(SETTINGS_LABEL_IMAGE, SETTINGS_LABEL_WIDTH, SETTINGS_LABEL_HEIGHT);
        ImageView frame = createImageView(SETTINGS_FRAME_IMAGE, SETTINGS_FRAME_WIDTH, SETTINGS_FRAME_HEIGHT);
        ImageView back = getBackButton();

        settingsBox.getChildren().addAll(label, frame, back);
        return settingsBox;
    }

    private ImageView createImageView(String imageName, double width, double height) {
        ImageView imageView = new ImageView(cache.getImage(imageName));
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    private ImageView getBackButton(){

        ImageView back = new ImageView(cache.getImage(BACK_BUTTON_IMAGE));
        back.setId("back");
        back.setFitWidth(BACK_BUTTON_WIDTH);
        back.setFitHeight(BACK_BUTTON_HEIGHT);
        back.setPreserveRatio(true);

        return back;
    }

}
