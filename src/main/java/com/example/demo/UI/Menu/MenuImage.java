package com.example.demo.UI.Menu;

import com.example.demo.UI.ImageInfo;
import javafx.scene.image.Image;

import java.util.Objects;

public enum MenuImage implements ImageInfo {
    // Image names and paths, will be called with enums.
    FRAME("ButtonFrame", "/images/ButtonFrame.png"),
    LOADING("Loading", "/images/LoadingScreen.png"),
    ARROWS("ArrowsImg", "/images/ArrowsImg.png"),
    SETTINGS_FRAME("SettingsFrame", "/images/SettingsFrame.png"),
    GAMEOVER("GameOver", "/images/GameOver.png"),
    BACKGROUND("Background", "/images/Background.png"),
    SCOREBOARD_LBL("ScoreboardLabel", "/images/ScoreboardLabel.png"),
    EXIT_LBL("ExitLabel", "/images/ExitLabel.png"),
    NAME_LBL("NameImg", "/images/NameImg.png"),
    SETTINGS_LBL("SettingsLabel", "/images/SettingsLabel.png"),
    START_LBL("StartLabel", "/images/StartLabel.png"),
    MINIGAME_LBL("MiniGameLabel", "/images/MiniGameLabel.png"),
    CONTINUE_LBL("ContinueLabel", "/images/ContinueLabel.png"),;

    private final String name;
    private final String path;

    MenuImage(String name, String path) {   // Constructor to initialize name and path
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }   // Method to retrieve the name

    public Image getImage() {
        // Use the method from the supertype to retrieve the image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        return image;
    }
}
