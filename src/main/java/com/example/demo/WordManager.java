package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

public class WordManager {

    private String targetWord;
    private int lettersCollected;
    private List<String> words;

    public WordManager(String targetWord){
        this.targetWord = targetWord;
        this.lettersCollected = 0;
        this.words = initializeWords(AppConfig.getWordsPathFile());
    }

    public ArrayList<String> initializeWords(String pathToFile){
        ArrayList<String> words = new ArrayList<>();
        try{
            BufferedReader buffReader = new BufferedReader(new FileReader(pathToFile));
            String strCurrentLine;
            while((strCurrentLine = buffReader.readLine()) != null){
                words.add(strCurrentLine);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return words;
    }

    public boolean checkNextLetter(char letterValue){
        boolean isCorrect = letterValue == targetWord.charAt(lettersCollected);

        if(isCorrect){
            lettersCollected++;
            checkWordIsComplete();
        }
        return isCorrect;
    }

    public void checkWordIsComplete(){
        if(lettersCollected == targetWord.length()){
            introduceNewWord();
        }
    }

    public void introduceNewWord(){
        this.lettersCollected = 0;
        this.targetWord = getRandomWord();
        System.out.println(targetWord);
    }

    private String getRandomWord() {
        int randomIndex = Util.generateRandomIndex(words.size());
        return words.get(randomIndex);
    }

    public String getTargetWord(){
        return this.targetWord;
    }

    public int getLettersCollected(){
        return this.lettersCollected;
    }

}
