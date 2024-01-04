package com.example.demo.UI.Menu;


import com.example.demo.GameCore.GameConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Util.Util;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SettingsMenu extends AbstractMenu {

    private static final double SETTINGS_BOX_WIDTH = 200;
    private static final double SETTINGS_BOX_HEIGHT = 100;
    private static final String SETTINGS_LABEL_IMAGE = "SettingsLabel"; //Image id (name), used by ImgCache
    private static final double SETTINGS_LABEL_WIDTH = 550; //Image width, manually calculated
    private static final double SETTINGS_LABEL_HEIGHT = 150; //Image height, manually calculated
    private static final String SETTINGS_FRAME_IMAGE = "SettingsFrame";
    private static final double SETTINGS_FRAME_WIDTH = 700;
    private static final double SETTINGS_FRAME_HEIGHT = 400;
    private static final String BACK_BUTTON_IMAGE = "ArrowsImg";
    private static final double BACK_BUTTON_WIDTH = 150;
    private static final double BACK_BUTTON_HEIGHT = 130;
    private static final double VOLUME_SLIDER = 300;
    private final Font font; //Font used as attribute to minimize loading


    //Constructor of the AbstractMenu is used, ImageCache is inherited
    public SettingsMenu(){
        super();
        this.font = Util.loadCustomFont(); //Font is loaded once to display labels
    }

    public StackPane createContent(){
        StackPane stackPane = createStackPane();
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background); //Background image is applied

        VBox settingsBox = createSettingsBox(); //VBox with settings content is created

        stackPane.getChildren().add(settingsBox); //VBox is added on top of background image

        return stackPane;
    }

    private StackPane createStackPane() {
        StackPane stackPane = new StackPane(); //StackPane is created with the same width and height as window size
        stackPane.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT);
        return stackPane;
    }

    private VBox createSettingsBox() {
        VBox settingsBox = new VBox();
        settingsBox.setAlignment(Pos.TOP_CENTER);
        settingsBox.setPrefHeight(SETTINGS_BOX_WIDTH);
        settingsBox.setPrefWidth(SETTINGS_BOX_HEIGHT);

        ImageView label = createImageView(SETTINGS_LABEL_IMAGE, SETTINGS_LABEL_WIDTH, SETTINGS_LABEL_HEIGHT);
        label.setMouseTransparent(true); //Behavior: label is not clickable (transparent to mouse)
        ImageView back = getBackButton(); //Button that allows to return to the main menu is created

        // Create volume controls
        VBox bgMusicControl = createVolumeControl("music");
        VBox sfxControl = createVolumeControl("sound effects");

        // Create a VBox for the frame and add the controls to it
        VBox frameBox = new VBox();
        frameBox.setAlignment(Pos.CENTER);
        frameBox.getChildren().addAll(bgMusicControl, sfxControl);

        // Create the frame and add the frameBox to it
        StackPane frame = new StackPane();
        frame.getChildren().addAll(createImageView(SETTINGS_FRAME_IMAGE, SETTINGS_FRAME_WIDTH, SETTINGS_FRAME_HEIGHT), frameBox);

        settingsBox.getChildren().addAll(label, frame, back); //All elements are added
        return settingsBox;
    }

    private VBox createVolumeControl(String text) {
        // Create a label with smaller font size
        SoundPlayer soundPlayer = SoundPlayer.getInstance();
        Label label = new Label(text);
        label.setFont(Util.loadCustomFont());
        label.setMaxWidth(600);
        label.setTextFill(Color.web("#aeb7ff"));
        label.setAlignment(Pos.CENTER);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);

        // Change the color of the slider's thumb and track
        slider.setStyle("-fx-control-inner-background: #FFFFFF; -fx-thumb-fill: #FFFFFF;");

        // Wrap the slider in an HBox, and center its contents
        HBox sliderBox = new HBox(slider);
        sliderBox.setAlignment(Pos.CENTER); // Center the contents of the HBox

        if (text.equals("music")) {
            slider.setValue(soundPlayer.getBackgroundVolume() * 100);
        } else if (text.equals("sound effects")) {
            slider.setValue(soundPlayer.getSFXVolume() * 100);
        }

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Convert to a value between 0.0 and 1.0
            if (text.equals("music")) {
                soundPlayer.setBackgroundVolume(slider.getValue() / 100);
            } else if (text.equals("sound effects")) {
                soundPlayer.setSFXVolume(slider.getValue() / 100);
            }
        });

        // Create a VBox to hold the label and slider
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, sliderBox);

        return vBox;
    }

    private ImageView createImageView(String imageName, double width, double height) {
        ImageView imageView = new ImageView(cache.getImage(imageName)); //Image is found through image cache (preloaded by ImgCache)
        imageView.setPreserveRatio(true); //Behavior: ratio height to width of the image is preserved/not adjusted to the parent node size
        imageView.setFitWidth(width); //height and width and ensured as manually calculated
        imageView.setFitHeight(height);
        return imageView;
    }

    private ImageView getBackButton(){

        ImageView back = new ImageView(cache.getImage(BACK_BUTTON_IMAGE)); //Image is found through image cache (preloaded by ImgCache)
        back.setId("back"); //ID is assigned to each label for mouse event recognition
        back.setFitWidth(BACK_BUTTON_WIDTH);
        back.setFitHeight(BACK_BUTTON_HEIGHT);
        back.setPreserveRatio(true); //Behavior: ratio height to width of the image is preserved/not adjusted to the parent node size

        return back;
    }

}
