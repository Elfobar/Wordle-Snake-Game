package com.example.demo;

public class ScoreCounter {
    private int score;

    public ScoreCounter() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        score += points;
    }

    public void deductPoints(int points) {
        if (score > 0) {
            score = Math.max(0, score - points);
        }
    }

    public void resetScore() {
        score = 0;
    }

}