package com.example.demo.Game;

import com.example.demo.Game.AbstractController;
import com.example.demo.GameCore.Letter;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.Util.Util;
import javafx.scene.input.KeyCode;

public class MiniGameController extends AbstractController {
    private int frameAdjuster;
    private boolean updateNeeded;
    private boolean keyPressed;
    private int lastKeyPressed;

    public MiniGameController() { // Constructor
        this.frameAdjuster = 0;
        this.updateNeeded = true;
        createLetters();
    }
    public void createLetters() { // Spawn letters   //
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
    public void createRandomLetter(int otherIndex) { // Genereates a random letter
        Letter letter = new Letter(Util.generateRandomLowercaseLetter());
        letter.setLetterLeftRightIndex(otherIndex);
        super.add(letter);
    }

    public void createLetterFromWord(int index) { // Generates a letter from given word 
        Letter letter = new Letter(pickNextLetterFromWord());
        letter.setLetterLeftRightIndex(index);
        super.add(letter);
    }
    public void handleKeyPress(KeyCode code) { // Registers the control buttons
        SoundPlayer.getInstance().playSFX(
                Sounds.EAT_1,
                Sounds.EAT_2,
                Sounds.EAT_3,
                Sounds.EAT_4);
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

    public void eatLeft() { // Check letter on left side on press
        boolean isCorrect = false;
        for (Letter letter : super.getLetters()) {
            if (letter.getLetterLeftRightIndex() == 0 && frameAdjuster % 5 == 0) {
                if (super.checkNextLetter(letter.getValue())) {
                    isCorrect = true;
                    super.incrementScore();
                }
            }
        }
        if (isCorrect){
            updateNeeded = true;
            updateGame();
            SoundPlayer.getInstance().playSFX(
                    Sounds.EAT_1,
                    Sounds.EAT_2,
                    Sounds.EAT_3,
                    Sounds.EAT_4);
        } else {
            SoundPlayer.getInstance().playSFX(
                    Sounds.WRONG_LETTER_1,
                    Sounds.WRONG_LETTER_2,
                    Sounds.WRONG_LETTER_3,
                    Sounds.WRONG_LETTER_4);
        }
    }

    public void eatRight() { // Check letter on right sode on press
        boolean isCorrect = false;
        for (Letter letter : getLetters()) {
            if (letter.getLetterLeftRightIndex() == 1 && frameAdjuster % 5 == 0) {
                if (super.checkNextLetter(letter.getValue())) {
                    isCorrect = true;
                    super.incrementScore();
                }
            }
        }
        if (isCorrect) {
            updateNeeded = true;
            updateGame();
            SoundPlayer.getInstance().playSFX(
                    Sounds.EAT_1,
                    Sounds.EAT_2,
                    Sounds.EAT_3,
                    Sounds.EAT_4);
        } else {
            SoundPlayer.getInstance().playSFX(
                    Sounds.WRONG_LETTER_1,
                    Sounds.WRONG_LETTER_2,
                    Sounds.WRONG_LETTER_3,
                    Sounds.WRONG_LETTER_4);
        }
    }

    public void checkUpdate() {
        if (getUpdateNeeded()){
            removeLetters();
            createLetters();
            frameAdjuster = 0;
        }
    }
    public void updateGame() { // Updates game
        if(frameAdjuster % 6 == 0){
            updateNeeded = true;
        }
        checkUpdate();
        frameAdjuster++;
    }

    private void removeLetters() { // Remove letter
        super.getLetters().clear();
    }

    public int getFrameAdjuster() {
        return this.frameAdjuster;
    }

    public boolean getUpdateNeeded() {
        return this.updateNeeded;
    }
    public void setUpdateNeeded(boolean updateNeeded) { // Declare that an update is needed
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
