package com.example.demo.GameCore;

import com.example.demo.Util.Util;

public class Score {

    private int score;

    public Score(){
        this.score = 0;
    }

    public void increaseScore(int points){
        this.score = score + points;
    }

    public void decreaseScore(int points){
        this.score = score - points;
        if(this.score < 0){
            score = 0;
        }
    }

    public int getScore(){
        return this.score;
    }

    public void saveScore(String filePath) {
        Util.saveScoreToFile(score, filePath);
    }
}
