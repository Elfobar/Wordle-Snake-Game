package com.example.demo;

public class AppConfig {
    private static final String WORDS_RELATIVE_PATH = "/src/main/resources//file_input/words.txt";
    private static final String FONT_RELATIVE_PATH = "/custom_font/blade.ttf";
    private static final int FONT_SIZE = 60;

    public static String getWordsPathFile() {
        String currentWorkingDir = System.getProperty("user.dir");
        return currentWorkingDir + WORDS_RELATIVE_PATH;
    }

    public static String getFontPathFile(){
        return FONT_RELATIVE_PATH;
    }

    public static int getFontSize(){
        return FONT_SIZE;
    }

}
