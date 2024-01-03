package com.example.demo.Game;

import org.json.JSONArray;
import com.example.demo.GameCore.AppConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.Util.Util;
import java.util.ArrayList;
import java.util.List;

public class WordManager {
    private String targetWord;
    private int lettersCollected;
    private JSONArray words;

    public WordManager(){
        this.lettersCollected = 0;
        this.words = Util.initializeWordsAsJsonArray();
        this.targetWord = getRandomWord();
    }


    public boolean checkNextLetter(char value){
        boolean isCorrect = false;
        if(!targetWord.isEmpty()){
            char expectedLetter = targetWord.charAt(lettersCollected);
            if(value == expectedLetter){
                isCorrect = true;
                lettersCollected++;
                checkWordIsComplete();
            }
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
        int randomIndex = Util.generateRandomIndex(words.length());
        targetWord = words.getString(randomIndex);
        targetWord = targetWord.substring(2, targetWord.length()-1);
        System.out.println(targetWord);
        return targetWord;
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
