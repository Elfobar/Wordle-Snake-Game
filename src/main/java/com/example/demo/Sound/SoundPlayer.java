package com.example.demo.Sound;

import com.example.demo.GameCore.AppConfig;
import com.example.demo.Util.Util;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Properties;
import java.io.*;

public class SoundPlayer {
    private static SoundPlayer instance;
    private MediaPlayer backgroundPlayer;
    private SoundSettings soundSettings;

    private SoundPlayer() {
        this.soundSettings = new SoundSettings();
    } //only one instance of the class exists due to singleton pattern

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
        Media sound = new Media(soundEnum.getSoundPath());
        backgroundPlayer = new MediaPlayer(sound);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.setVolume(getBackgroundVolume()); // Set the volume lvl
        backgroundPlayer.play();
    }

    public void playSFX(Sounds... soundEnums) {
        int randomIndex = Util.generateRandomIndex(soundEnums.length);
        Sounds soundEnum = soundEnums[randomIndex]; // Use the random index to select a sound
        Media sound = new Media(soundEnum.getSoundPath());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(getSFXVolume());
        mediaPlayer.play();
    }

    public void pauseBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.pause();
        }
    }

    public void resumeBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.play();
        }
    }

    public void saveSettings(){
        Util.writeToFile(soundSettings, AppConfig.getAudioSettingsPathFile());
    }

    public double getBackgroundVolume() {
        return soundSettings.getBackgroundVolume();
    }

    public double getSFXVolume() {
        return soundSettings.getSfxVolume();
    }

    public void setBackgroundVolume(double volume) {
        soundSettings.setBackgroundVolume(volume);
        saveSettings();
    }

    public void setSFXVolume(double volume) {
        soundSettings.setSfxVolume(volume);
        saveSettings();
    }

}

