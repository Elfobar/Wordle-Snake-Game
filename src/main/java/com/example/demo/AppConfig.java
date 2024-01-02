package com.example.demo;

public class AppConfig {

    //FONT NEEDS TO BE PUBLIC
    public static final String FONT_RELATIVE_PATH = "/custom_font/blade.ttf";
    public static final int WORD_FONT_SIZE = 60;
    private static final String WORDS_RELATIVE_PATH = "/file_input/words.txt";
    private static final String TILE_TYPE1 = "/images/Tile1.png";
    private static final String TILE_TYPE2 = "/images/Tile2.png";
    private static final String SNAKE_HEAD = "/images/snakeHead.png";
    private static final String SNAKE_SEGMENT = "/images/snakeSegment.png";
    private static final String SNAKE_TAIL = "/images/snakeTail.png";
    private static final String PROJECT_PATH = "/src/main/resources";
    private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir") + PROJECT_PATH;
    private static final String OBSTACLE = "/images/obstacle.jpg";
    private static final String VOLUME_SETTINGS_FILE = "/audio_settings/volume.properties.txt";

    public static String getWordsPathFile() {
        return CURRENT_WORKING_DIR + WORDS_RELATIVE_PATH;
    }

    public static String getImagesPathType1(){
        return CURRENT_WORKING_DIR + TILE_TYPE1;
    }

    public static String getImagesPathType2(){
        return CURRENT_WORKING_DIR + TILE_TYPE2;
    }

    public static String getSnakeHeadPath(){
        return CURRENT_WORKING_DIR + SNAKE_HEAD;
    }

    public static String getSnakeSegmentPath(){
        return CURRENT_WORKING_DIR + SNAKE_SEGMENT;
    }

    public static String getSnakeTailPath(){
        return CURRENT_WORKING_DIR + SNAKE_TAIL;
    }

    public static String getCurrentWorkingDir(){
        return CURRENT_WORKING_DIR;
    }

    public static String getObstaclePath(){
        return CURRENT_WORKING_DIR + OBSTACLE;
    }
    public static String getAudioSettingsPath() {return CURRENT_WORKING_DIR + VOLUME_SETTINGS_FILE;}

}
