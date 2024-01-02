package com.example.demo.Game;

import com.example.demo.GameCore.AppConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.Util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public WordManager(){
        this.lettersCollected = 0;
        this.words = initializeWords(AppConfig.getWordsPathFile());
        this.targetWord = getRandomWord();
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
        boolean isCorrect = false;
        if(!targetWord.isEmpty() && lettersCollected < targetWord.length()){
            isCorrect = letterValue == targetWord.charAt(lettersCollected);
        }

        if(isCorrect){
            lettersCollected++;
            checkWordIsComplete();
        }
        return isCorrect;
    }

    public void checkWordIsComplete(){
        if(lettersCollected == targetWord.length()){
            SoundPlayer.getInstance().playSFX(
                    Sounds.COMPLETED_WORD_1,
                    Sounds.COMPLETED_WORD_2,
                    Sounds.COMPLETED_WORD_3);
            introduceNewWord();
        }
    }

    public void introduceNewWord(){
        this.lettersCollected = 0;
        this.targetWord = getRandomWord();
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

    public String getCurrentInput(){
        return targetWord.substring(0, lettersCollected);
    }

}
