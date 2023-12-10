package com.example.demo;

public class AppConfig {
    private static final String WORDS_RELATIVE_PATH = "/file_input/words.txt";

    public static String getWordsPathFile(){
        String currentWorkingDir = System.getProperty("user.dir");
        return currentWorkingDir + WORDS_RELATIVE_PATH;
    }
}
