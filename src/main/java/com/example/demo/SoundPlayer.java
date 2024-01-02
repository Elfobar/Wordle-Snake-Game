package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Properties;
import java.io.*;
import java.util.Random;

public class SoundPlayer {
    private static Properties properties = new Properties();
    private static SoundPlayer instance;
    private MediaPlayer menuPlayer;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer sfxPlayer;
    private Random random;

    private static double backgroundVolume; // Default volume in middle
    private static double sfxVolume; // Default volume in middle

    private SoundPlayer() {
        random = new Random();
        // Load settings from file
        try (InputStream input = new FileInputStream(AppConfig.getAudioSettingsPath())) {
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
        Media sound = new Media(new File(soundEnum.getSoundPath()).toURI().toString());
        backgroundPlayer = new MediaPlayer(sound);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.setVolume(backgroundVolume); // Set the volume lvl
        backgroundPlayer.play();
    }

    public void playSFX(Sounds... soundEnums) {
        int randomIndex = random.nextInt(soundEnums.length); // Get a random index between 1-4 (index 0-3)
        Sounds soundEnum = soundEnums[randomIndex]; // Use the random index to select one of the sounds
        Media sound = new Media(new File(soundEnum.getSoundPath()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(sfxVolume); // Set the volume of the sfx
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
        try (OutputStream output = new FileOutputStream(AppConfig.getAudioSettingsPath())) {
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
