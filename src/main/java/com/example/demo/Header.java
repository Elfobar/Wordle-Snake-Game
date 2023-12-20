package com.example.demo;

import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Header {

    private final Font font;

    public Header(){
        this.font = Util.loadCustomFont(getClass());
    }

    public HBox createHeader(){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #160244;");
        hbox.setSpacing(10.0);

        return hbox;
    }

    public Text getScoreText() {

        Text scoreText = new Text("0");
        scoreText.setStyle("-fx-font-size: 25");
        scoreText.setFill(Color.LIGHTBLUE);

        return scoreText;
    }

    public Text getTargetWord(String word) {
        Text targetWord = new Text(word);
        targetWord.setFont(this.font);
        targetWord.setFill(Color.LIGHTBLUE);
        return targetWord;
    }

    public Text getCurrentInput() {
        Text currentInput = new Text();
        Glow glow = new Glow();
        glow.setLevel(0.85);
        currentInput.setFont(this.font);
        currentInput.setFill(Color.LIGHTBLUE);
        currentInput.setEffect(glow);
        return currentInput;
    }

    public GridPane getScorePane(Text score){
        GridPane scorePane = new GridPane();
        VBox scoreVbox = new VBox();
        scoreVbox.setAlignment(Pos.CENTER);

        Text text = new Text("Score:");
        text.setStyle("-fx-font-size: 25");
        text.setFill(Color.LIGHTBLUE);

        scoreVbox.getChildren().add(text);
        scoreVbox.getChildren().add(score);

        scorePane.add(scoreVbox, 0, 0);
        scorePane.setAlignment(Pos.CENTER);
        return scorePane;
    }
}
