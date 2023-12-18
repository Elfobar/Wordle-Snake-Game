package com.example.demo;

import javafx.scene.image.Image;

import java.util.Objects;

public enum MenuImage implements ImageInfo {
    FRAME("ButtonFrame", "/images/ButtonFrame.png"),
    LOADING("Loading", "/images/LoadingScreen.png"),
    ARROWS("ArrowsImg", "/images/ArrowsImg.png"),
    SETTINGS_FRAME("SettingsFrame", "/images/SettingsFrame.png"),
    GAMEOVER("GameOver", "/images/GameOver.png"),
    BACKGROUND("Background", "/images/Background.png"),
    LEADERBOARD_LBL("LeaderboardLabel", "/images/LeaderboardLabel.png"),
    EXIT_LBL("ExitLabel", "/images/ExitLabel.png"),
    NAME_LBL("NameImg", "/images/NameImg.png"),
    SETTINGS_LBL("SettingsLabel", "/images/SettingsLabel.png"),
    START_LBL("StartLabel", "/images/StartLabel.png");

    private final String name;
    private final String path;

    MenuImage(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        return image;
    }
}
