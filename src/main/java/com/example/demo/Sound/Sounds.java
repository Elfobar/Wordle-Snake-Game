package com.example.demo.Sound;

import com.example.demo.GameCore.AppConfig;

import java.net.URL;

public enum Sounds implements SoundInfo{

    BACKGROUND_TRACK("/sounds/earworm.mp3"),
    MINIGAME_MUSIC("/sounds/earworm miniGame.mp3"),

    //Sounds when errors occurs
    ERROR_1 ("/sounds/exception handling 1.mp3"),
    ERROR_2 ("/sounds/exception handling 2.mp3"),

    //Choosing a main menu button sounds (except start)
    BUTTON_1 ("/sounds/menu choose button 1.mp3"),
    BUTTON_2 ("/sounds/menu choose button 2.mp3"),
    BUTTON_3 ("/sounds/menu choose button 3.mp3"),
    BUTTON_4 ("/sounds/menu choose button 4.mp3"),

    //start button game sounds
    START_1 ("/sounds/start game 1.mp3"),
    START_2 ("/sounds/start game 2.mp3"),
    START_3 ("/sounds/start game 3.mp3"),

    //Sound produced when snake rotates
    ROTATE_1 ("/sounds/snake changes direction 1.mp3"),
    ROTATE_2 ("/sounds/snake changes direction 2.mp3"),
    ROTATE_3 ("/sounds/snake changes direction 3.mp3"),
    ROTATE_4 ("/sounds/snake changes direction 4.mp3"),

    //Sound produced when snake eats a letter
    EAT_1 ("/sounds/snake eats a letter 1.mp3"),
    EAT_2 ("/sounds/snake eats a letter 2.mp3"),
    EAT_3 ("/sounds/snake eats a letter 3.mp3"),
    EAT_4 ("/sounds/snake eats a letter 4.mp3"),

    //Sounds produced when the player completes a full word
    COMPLETED_WORD_1 ("/sounds/snake collects a full word 2.mp3"),
    COMPLETED_WORD_2 ("/sounds/snake collects a full word 3.mp3"),
    COMPLETED_WORD_3 ("/sounds/snake collects a full word 4.mp3"),

    //Sounds produced when the snake dies
    COLLIDE_1 ("/sounds/snake dies 1.mp3"),
    COLLIDE_2 ("/sounds/snake dies 2.mp3"),
    COLLIDE_3 ("/sounds/snake dies 3.mp3"),
    COLLIDE_4 ("/sounds/snake dies 4.mp3"),

    //Sounds produced when wrong letter collected
    WRONG_LETTER_1 ("/sounds/wrong letter 1.mp3"),
    WRONG_LETTER_2 ("/sounds/wrong letter 2.mp3"),
    WRONG_LETTER_3 ("/sounds/wrong letter 3.mp3"),
    WRONG_LETTER_4 ("/sounds/wrong letter 4.mp3");

    private final String name;
    Sounds(String name) {
        this.name = name;
    }
    @Override
    public String getSoundName() {
        return name;
    }
    @Override
    public String getSoundPath() {
        URL resourceUrl = getClass().getResource(name);
        if (resourceUrl != null) {
            return resourceUrl.toString();
        } else {
            throw new RuntimeException("Sound file not found: " + name);
        }
    }
}
