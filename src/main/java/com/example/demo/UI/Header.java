package com.example.demo.UI;

import com.example.demo.Game.GameController;
import com.example.demo.Game.MiniGameController;
import com.example.demo.Util.Util;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Header {

    private final Font font;
    private GameController gameController;
    private MiniGameController miniGameController;
    private Text targetWord;
    private Text currentInput;
    private Text scoreText;

    public Header(GameController gameController){
        this.font = Util.loadCustomFont(getClass());
        this.gameController = gameController;
        this.targetWord = getTargetWord(gameController.getTargetWord());
        this.currentInput = getCurrentInput();
        this.scoreText = getScoreText();
    }
    public Header(MiniGameController miniGameController){
        this.font = Util.loadCustomFont(getClass());
        this.miniGameController = miniGameController;
        this.targetWord = getTargetWord(miniGameController.getTargetWord());
        this.currentInput = getCurrentInput();
        this.scoreText = getScoreText();
    }

    public HBox createHeader(){
        HBox hbox = initializeHeader();

        Text targetWord = this.targetWord;
        Text currentInput = this.currentInput;
        Text scoreText = this.scoreText;
        GridPane textPane = getScorePane(scoreText);

        StackPane textOverlap = new StackPane();
        textOverlap.getChildren().addAll(targetWord, currentInput);
        textOverlap.setAlignment(Pos.CENTER_LEFT);

        GridPane overlapPane = new GridPane();
        overlapPane.setAlignment(Pos.CENTER);
        overlapPane.getChildren().add(textOverlap);

        hbox.getChildren().addAll(textPane, overlapPane);
        textPane.setMinWidth(40);
        HBox.setHgrow(textPane, Priority.NEVER);
        HBox.setHgrow(overlapPane, Priority.ALWAYS);

        return hbox;
    }

    public void updateHeader(){
        updateWord();
        updateScore();
    }

    private HBox initializeHeader(){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #160244;");
        hbox.setSpacing(10.0);
        return hbox;
    }

    private void updateWord(){
        String currentWord = "";
        if (gameController != null){
            currentWord = gameController.getTargetWord();
        } else {
            currentWord = miniGameController.getTargetWord();
        }
        /////
        if(currentInput != null){
            String currentInputString = "";
            if (gameController != null){
                currentInputString = gameController.getCurrentInput();
                currentInput.setText(currentInputString);
            } else {
                currentInputString = miniGameController.getCurrentInput();
                currentInput.setText(currentInputString);
            }
        }
        if(!currentWord.equals(targetWord.toString())){
            targetWord.setText(currentWord);
        }
    }

    private void updateScore(){
        int score;
        if (gameController != null) {
            score = gameController.getScore();
        } else {
            score = miniGameController.getScore();
        }
        this.scoreText.setText(Integer.toString(score));
    }

    private Text getScoreText() {

        Text scoreText = new Text("0");
        scoreText.setStyle("-fx-font-size: 25");
        scoreText.setFill(Color.LIGHTBLUE);

        return scoreText;
    }

    private Text getTargetWord(String word) {
        Text targetWord = new Text(word);
        targetWord.setFont(this.font);
        targetWord.setFill(Color.LIGHTBLUE);
        return targetWord;
    }

    private Text getCurrentInput() {
        Text currentInput = new Text();
        Glow glow = new Glow();
        glow.setLevel(0.85);
        currentInput.setFont(this.font);
        currentInput.setFill(Color.LIGHTBLUE);
        currentInput.setEffect(glow);
        return currentInput;
    }

    private GridPane getScorePane(Text score){
        GridPane scorePane = new GridPane();
        VBox scoreVbox = new VBox();
        scoreVbox.setAlignment(Pos.CENTER);

        Text text = new Text("Score:");
        text.setStyle("-fx-font-size: 30");
        text.setFill(Color.LIGHTBLUE);
        score.setFont(font);
        text.setFont(font);

        scoreVbox.getChildren().add(text);
        scoreVbox.getChildren().add(score);

        scorePane.add(scoreVbox, 0, 0);
        scorePane.setAlignment(Pos.CENTER);
        return scorePane;
    }
}
