package com.example.demo;

import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
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

    public AnchorPane getScorePane(Text score){
        AnchorPane scorePane = new AnchorPane();
        double prefWidth = 20.0;
        double prefHeight = 20.0;

        scorePane.setPrefWidth(prefWidth);
        scorePane.setPrefHeight(prefHeight);

        scorePane.setPrefSize(80.0, 68.0);
        scorePane.setMaxSize(prefWidth, prefHeight);
        scorePane.setMinSize(prefWidth, prefHeight);
        AnchorPane.setLeftAnchor(score, 10.0);
        AnchorPane.setTopAnchor(score, 10.0);

        scorePane.getChildren().add(score);
        return scorePane;
    }
}
