package com.example.demo.Sound;

import com.example.demo.GameCore.AppConfig;
import com.example.demo.Util.Util;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Properties;
import java.io.*;

public class SoundPlayer {
    private static Properties properties = new Properties();
    private static SoundPlayer instance;
    private MediaPlayer backgroundPlayer;

    private static double backgroundVolume; // Default volume in middle
    private static double sfxVolume; // Default volume in middle

    private SoundPlayer() {
        // Load settings from file
        try (InputStream input = new FileInputStream(AppConfig.getAudioSettingsPathFile())) {
            properties.load(input);
            backgroundVolume = Double.parseDouble(properties.getProperty("backgroundVolume", "0.5"));
            sfxVolume = Double.parseDouble(properties.getProperty("sfxVolume", "0.5"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        backgroundPlayer.setVolume(backgroundVolume); // Set the volume lvl
        backgroundPlayer.play();
    }

    public void playSFX(Sounds... soundEnums) {
        int randomIndex = Util.generateRandomIndex(soundEnums.length);
        Sounds soundEnum = soundEnums[randomIndex]; // Use the random index to select a sound
        Media sound = new Media(soundEnum.getSoundPath());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(sfxVolume);
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

    public static double getBackgroundVolume() {
        return backgroundVolume;
    }

    public static double getSFXVolume() {
        return sfxVolume;
    }

    public static void setBackgroundVolume(double volume) {
        backgroundVolume = volume;
        properties.setProperty("backgroundVolume", Double.toString(volume));
        saveSettings();
    }

    public static void setSFXVolume(double volume) {
        sfxVolume = volume;
        properties.setProperty("sfxVolume", Double.toString(volume));
        saveSettings();
    }

    private static void saveSettings() {
        try (OutputStream output = new FileOutputStream(AppConfig.getAudioSettingsPathFile())) {
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

