package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public enum Sounds implements SoundInfo{

    BACKGROUND_TRACK("/sounds/earworm.mp3"),

    //Sounds when error occurs
    ERROR_SOUND_1 ("/sounds/exception handling 1.mp3"),
    ERROR_SOUND_2 ("/sounds/exception handling 2.mp3"),

    //Choosing a main menu button sounds (except start)
    MENU_BUTTON_SOUND_1 ("/sounds/menu choose button 1.mp3"),
    MENU_BUTTON_SOUND_2 ("/sounds/menu choose button 2.mp3"),
    MENU_BUTTON_SOUND_3 ("/sounds/menu choose button 3.mp3"),
    MENU_BUTTON_SOUND_4 ("/sounds/menu choose button 4.mp3"),

    //start button game sounds
    START_GAME_SOUND_1 ("/sounds/start game 1.mp3"),
    START_GAME_SOUND_2 ("/sounds/start game 2.mp3"),
    START_GAME_SOUND_3 ("/sounds/start game 3.mp3"),

    //Sound produced when snake rotates
    SNAKE_ROTATE_SOUND_1 ("/sounds/snake changes direction 1.mp3"),
    SNAKE_ROTATE_SOUND_2 ("/sounds/snake changes direction 2.mp3"),
    SNAKE_ROTATE_SOUND_3 ("/sounds/snake changes direction 3.mp3"),
    SNAKE_ROTATE_SOUND_4 ("/sounds/snake changes direction 4.mp3"),

    //Sound produced when snake eats a letter
    SNAKE_EATS_SOUND_1 ("/sounds/snake eats a letter 1.mp3"),
    SNAKE_EATS_SOUND_2 ("/sounds/snake eats a letter 2.mp3"),
    SNAKE_EATS_SOUND_3 ("/sounds/snake eats a letter 3.mp3"),
    SNAKE_EATS_SOUND_4 ("/sounds/snake eats a letter 4.mp3"),

    //Sounds produced when the player completes a full word
    COMPLETE_WORD_SOUND_1 ("/sounds/snake collects a full word 1.mp3"),
    COMPLETE_WORD_SOUND_2 ("/sounds/snake collects a full word 2.mp3"),
    COMPLETE_WORD_SOUND_3 ("/sounds/snake collects a full word 3.mp3"),
    COMPLETE_WORD_SOUND_4 ("/sounds/snake collects a full word 4.mp3"),

    //Sounds produced when the snake dies
    SNAKE_DIES_SOUND_1 ("/sounds/snake dies 1.mp3"),
    SNAKE_DIES_SOUND_2 ("/sounds/snake dies 2.mp3"),
    SNAKE_DIES_SOUND_3 ("/sounds/snake dies 3.mp3"),;

    private final String name;
    Sounds(String name) {
        this.name = name;
    }
    @Override
    public String getSoundName() {
        return name;
    }
    public String getSoundPath() {
        return AppConfig.getCurrentWorkingDir() + getSoundName();
    }
}
