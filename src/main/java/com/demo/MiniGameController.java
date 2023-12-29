package com.example.demo;

import javafx.scene.input.KeyCode;

public class MiniGameController extends AbstractController{
    private int frameAdjuster;
    private boolean updateNeeded;
    private boolean keyPressed;
    private int lastKeyPressed;
    public MiniGameController() {
        this.frameAdjuster = 0;
        this.updateNeeded = true;
        createLetters();
    }
    public void createLetters() {
        int index = Util.generateRandomIndex(2);
        int otherIndex;
        if (index == 0) {
            otherIndex = 1;
        } else {
            otherIndex = 0;
        }
        createLetterFromWord(index);
        createRandomLetter(otherIndex);
    }

    public void createRandomLetter(int otherIndex) {
        Letter letter = new Letter(Util.generateRandomLowercaseLetter());
        letter.setLetterLeftRightIndex(otherIndex);
        super.add(letter);
    }

    public void createLetterFromWord(int index) {
        Letter letter = new Letter(pickNextLetterFromWord());
        letter.setLetterLeftRightIndex(index);
        super.add(letter);
    }
    public void handleKeyPress(KeyCode code) {
        switch(code){
            case LEFT:
                keyPressed = true;
                lastKeyPressed = 0;
                eatLeft();
                break;
            case RIGHT:
                keyPressed = true;
                lastKeyPressed = 1;
                eatRight();
                break;
        }
    }

    public void eatLeft() {
        boolean isCorrect = false;
        for (Letter letter : super.getLetters()) {
            if (letter.getLetterLeftRightIndex() == 0 && frameAdjuster % 5 == 0) {
                if (super.checkNextLetter(letter.getValue())) {
                    isCorrect = true;
                }
            }
        }
        if (isCorrect){
            updateNeeded = true;
            updateGame();
        }
    }

    public void eatRight() {
        boolean isCorrect = false;
        for (Letter letter : getLetters()) {
            if (letter.getLetterLeftRightIndex() == 1 && frameAdjuster % 5 == 0) {
                if (super.checkNextLetter(letter.getValue())) {
                    isCorrect = true;
                }
            }
        }
        if (isCorrect) {
            updateNeeded = true;
            updateGame();
        }
    }

    public void checkUpdate() {
        if (getUpdateNeeded()){
            removeLetters();
            createLetters();
            frameAdjuster = 0;
        }
    }
    public void updateGame() {
        if(frameAdjuster % 6 == 0){
            updateNeeded = true;
        }
        checkUpdate();
        frameAdjuster++;
    }

    private void removeLetters() {
        super.getLetters().clear();
    }

    public int getFrameAdjuster() {
        return this.frameAdjuster;
    }

    public boolean getUpdateNeeded() {
        return this.updateNeeded;
    }
    public void setUpdateNeeded(boolean updateNeeded) {
        this.updateNeeded = updateNeeded;
    }

    public boolean getKeyPressed() {
        return this.keyPressed;
    }
    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }
    public int getLastKeyPressed() {
        return this.lastKeyPressed;
    }
}
