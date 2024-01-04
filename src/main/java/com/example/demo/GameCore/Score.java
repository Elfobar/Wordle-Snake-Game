package com.example.demo.GameCore;

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
}
