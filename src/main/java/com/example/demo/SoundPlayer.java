package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {
    private static SoundPlayer instance;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer sfxPlayer;
    public static final int SOUNDS_NUMBER = 4;
    private SoundPlayer() {} //only one instance of the class exists due to singleton pattern

    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }
    public void playBackgroundMusic(Sounds soundEnum) {
        if (backgroundPlayer != null) {
            backgroundPlayer.stop();
        }
        Media sound = new Media(new File(soundEnum.getSoundPath()).toURI().toString());
        backgroundPlayer = new MediaPlayer(sound);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.play();
    }

    public void playSFX(Sounds soundEnum) {
        Media sound = new Media(new File(soundEnum.getSoundPath()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
