package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;

import com.example.demo.AppConfig;
import com.example.demo.GameConfig;
import com.example.demo.SoundPlayer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
    private static final double VOLUME_SLIDER = 300;

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
        ImageView back = getBackButton();

        // Create volume controls
        VBox bgMusicControl = createVolumeControl("background music");
        VBox sfxControl = createVolumeControl("sound effects");

        // Create a VBox for the frame and add the controls to it
        VBox frameBox = new VBox();
        frameBox.setAlignment(Pos.CENTER);
        frameBox.getChildren().addAll(bgMusicControl, sfxControl);

        // Create the frame and add the frameBox to it
        StackPane frame = new StackPane();
        frame.getChildren().addAll(createImageView(SETTINGS_FRAME_IMAGE, SETTINGS_FRAME_WIDTH, SETTINGS_FRAME_HEIGHT), frameBox);

        settingsBox.getChildren().addAll(label, frame, back);
        return settingsBox;
    }

    private VBox createVolumeControl(String text) {
        // Create a label with smaller font size
        Label label = new Label(text);
        label.setFont(Font.loadFont(getClass().getResourceAsStream(AppConfig.FONT_RELATIVE_PATH), AppConfig.WORD_FONT_SIZE / 1.2)); // Adjust the divisor as needed
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50); // Default volume

        // Change the color of the slider's thumb and track
        slider.setStyle("-fx-control-inner-background: #FFFFFF; -fx-thumb-fill: #FFFFFF;");

        // Wrap the slider in an HBox, set the maximum width of the HBox, and center its contents
        HBox sliderBox = new HBox(slider);
        sliderBox.setMaxWidth(300); // Set the maximum width as needed
        sliderBox.setAlignment(Pos.CENTER); // Center the contents of the HBox
        SoundPlayer soundPlayer = SoundPlayer.getInstance();
        if (text.equals("background music")) {
            slider.setValue(SoundPlayer.getBackgroundVolume() * 100);
        } else if (text.equals("sound effects")) {
            slider.setValue(SoundPlayer.getSFXVolume() * 100);
        }

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Slider value changed");
            double volume = slider.getValue() / 100; // Convert to a value between 0.0 and 1.0
            if (text.equals("background Music")) {
                SoundPlayer.setBackgroundVolume(volume);
            } else if (text.equals("sound Effects")) {
                SoundPlayer.setSFXVolume(volume);
            }
        });

        // Create a VBox to hold the label and slider
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, sliderBox);

        return vBox;
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
