package com.example.demo.GameCore;

import java.net.URL;

public class AppConfig {
    private static final String SCORE_PATH = "/output/score.json";
    private static final String FONT_PATH = "/custom_font/blade.ttf";
    private static final String WORDS_PATH = "/file_input/words.json";
    private static final String TILE_TYPE1 = "/Tile1.png";
    private static final String TILE_TYPE2 = "/Tile2.png";
    private static final String SNAKE_HEAD = "/snakeHead.png";
    private static final String SNAKE_SEGMENT = "/snakeSegment.png";
    private static final String SNAKE_TAIL = "/snakeTail.png";
    private static final String OBSTACLE = "/obstacle.jpg";
    private static final String IMAGES_PATH = "/images";
    private static final String VOLUME_SETTINGS_FILE = "/audio_settings/volume.properties.txt";

    public static String getWordsPathFile() {
        return getResourcePath(WORDS_PATH).getPath();
    }
    public static String getAudioSettingsPathFile() {return getResourcePath(VOLUME_SETTINGS_FILE).getPath();}
    public static String getScorePathFile(){
        return getResourcePath(SCORE_PATH).getPath();
    }
    public static String getCustomFontPathFile()  {
        return getResourcePath(FONT_PATH).toString();
    }
    public static String getImagesPathType1()  {
        return  getResourcePath(IMAGES_PATH + TILE_TYPE1).toString();
    }
    public static String getImagesPathType2()  {
        return  getResourcePath(IMAGES_PATH + TILE_TYPE2).toString();
    }
    public static String getSnakeHeadPath()  {
        return getResourcePath(IMAGES_PATH + SNAKE_HEAD).toString();
    }
    public static String getSnakeSegmentPath()  {
        return getResourcePath(IMAGES_PATH + SNAKE_SEGMENT).toString();
    }
    public static String getSnakeTailPath()  {
        return getResourcePath(IMAGES_PATH + SNAKE_TAIL).toString();
    }
    public static String getObstaclePath()  {
        return getResourcePath(IMAGES_PATH+OBSTACLE).toString();
    }
    private static URL getResourcePath(String relativePath) {
        return AppConfig.class.getResource(relativePath);
    }
}
