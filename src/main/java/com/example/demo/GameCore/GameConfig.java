package com.example.demo.GameCore;

// Other classes use these final
public class GameConfig {
    public static final int POINTS_FOR_COLLECTED_WORD = 10;
    public static final int INCREASING_POINTS_RATE = 5;
    public static final int DECREASING_POINTS_RATE = 5;
    public static final int INIT_SNAKE_LENGTH = 3;
    public static final int TARGET_WORD_FONT_SIZE = 60;
    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;
    public static final int HEADER_SPACE = 68;
    public static final int HEIGHT = (ROWS * CELL_SIZE) + HEADER_SPACE;
    public static final int WIDTH = (ROWS * CELL_SIZE);
    public static final String GAME_NAME = "Snake";
}
