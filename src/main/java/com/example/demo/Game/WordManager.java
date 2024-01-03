package com.example.demo.Game;

import com.example.demo.GameCore.AppConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.Util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class WordManager {
    private String targetWord;
    private int lettersCollected;
    private List<String> words;

    public WordManager(){
        this.lettersCollected = 0;
        this.words = initializeWords();
        this.targetWord = getRandomWord();
    }

    public ArrayList<String> initializeWords(){
        ArrayList<String> words = new ArrayList<>();
        try{
            FileReader reader = new FileReader(AppConfig.getWordsPathFile());
            BufferedReader buffReader = new BufferedReader(reader);
            String strCurrentLine;
            while((strCurrentLine = buffReader.readLine()) != null){
                words.add(strCurrentLine);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("Error initializing the list of words: \n"+ e.getMessage());
        }
        return words;
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
        int randomIndex = Util.generateRandomIndex(words.size());
        targetWord = words.get(randomIndex);
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
