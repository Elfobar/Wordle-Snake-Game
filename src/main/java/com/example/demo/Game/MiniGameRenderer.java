package com.example.demo.Game;

import com.example.demo.GameCore.Letter;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.UI.ImgCache;
import com.example.demo.Util.Util;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
//MiniGameRenderer is used to draw everything that happens on the screen when the mini game is started.
public class MiniGameRenderer {
    private MiniGameController miniGameController;
    private StackPane stackPane;
    private AnchorPane anchorPane;
    private ImgCache animationChache;
    private ImageView snake;
    private ImageView frame;
    private ImageView obstacle;
    private ArrayList<Text> letters;
    private int animationCounter;
    private boolean obstacleSide;
    private BorderPane content;

    // Constructor for mini game.
    public MiniGameRenderer(MiniGameController miniGameController) {
        this.miniGameController = miniGameController;
        this.animationChache = new ImgCache("MiniGame");
        this.stackPane = new StackPane();
        this.anchorPane = new AnchorPane();
        this.snake = new ImageView(animationChache.getImage("SnakeUpfront"));
        snake.setFitWidth(650);
        snake.setFitHeight(560);
        this.animationCounter = 0;
        this.letters = new ArrayList<>();
        this.obstacleSide = true;
        this.content = createMiniGameContent();
    }

    //Creates the content of the mini-game.
    public BorderPane createMiniGameContent() {
        ImageView background = new ImageView(animationChache.getImage("Background"));
        background.setFitHeight(800);
        background.setFitWidth(800);
        this.stackPane.getChildren().add(background);
        this.stackPane.getChildren().add(this.snake);
        StackPane.setMargin(snake, new Insets(100, 10, 0, 0));
        this.stackPane.getChildren().add(this.anchorPane);
        this.frame = new ImageView(animationChache.getImage("Frame"));
        frame.setFitHeight(800);
        frame.setFitWidth(800);
        this.obstacle = new ImageView();
        obstacle.setFitWidth(800);
        obstacle.setFitHeight(800);
        this.stackPane.getChildren().add(frame);
        this.stackPane.getChildren().add(obstacle);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.stackPane);
        return borderPane;
    }

    //handles  including handling key presses, updating the frame, animating the snake, clearing letters, drawing new letters,
    // and removing obstacles.
    public void renderGame() {

        addObstacles();
        if (miniGameController.getKeyPressed()) {
            changeFrame(miniGameController.getLastKeyPressed());
            miniGameController.setKeyPressed(false);
        } else {
            this.frame.setImage(animationChache.getImage("Frame"));
        }
        animateSnake();
        if(miniGameController.getUpdateNeeded()){
            clearLetters();
            drawLetters();
            miniGameController.setUpdateNeeded(false);
        }
        removeObstacle();
    }

    //Changes the frame based on the last key pressed, triggering a rotation animation and playing a sound effect.
    private void changeFrame(int lastKeyPressed) {
        if (lastKeyPressed == 0) {
            SoundPlayer.getInstance().playSFX(// Four sounds for rotating the snake.
                    Sounds.ROTATE_1,
                    Sounds.ROTATE_2,
                    Sounds.ROTATE_3,
                    Sounds.ROTATE_4);
            this.frame.setImage(animationChache.getImage("FrameLeft"));
        } else if (lastKeyPressed == 1) {
            SoundPlayer.getInstance().playSFX(
                    Sounds.ROTATE_1,
                    Sounds.ROTATE_2,
                    Sounds.ROTATE_3,
                    Sounds.ROTATE_4);
            this.frame.setImage(animationChache.getImage("FrameRight"));
        }
    }

    private void clearLetters() { // Removes all letters.
        this.anchorPane.getChildren().removeAll(letters);
        letters.clear();
    }

    public void animateSnake() { // Sets the snake to the corresponding image based on direction.
        if (this.animationCounter == 0) {
            this.snake.setImage(animationChache.getImage("SnakeUpfront"));
            animationCounter++;
        } else if (this.animationCounter == -1) {
            this.snake.setImage(animationChache.getImage("SnakeRight"));
            animationCounter++;
        } else if (this.animationCounter == 1) {
            this.snake.setImage(animationChache.getImage("SnakeLeft"));
            animationCounter++;
        } else if (this.animationCounter == 2) {
            this.snake.setImage(animationChache.getImage("SnakeUpfront"));
            this.animationCounter = -1;
        }
    }

    public void drawLetters() { // Draw the letters on the screen.
        for (Letter letter : miniGameController.getLetters()) {
            Text textLetter = createVisualLetter(letter);
            if(letter.getLetterLeftRightIndex() == 0){
                AnchorPane.setLeftAnchor(textLetter, 0.0);
            } else {
                AnchorPane.setRightAnchor(textLetter, 0.0);
            }
            AnchorPane.setTopAnchor(textLetter, 350.0);
            anchorPane.getChildren().add(textLetter);
            letters.add(textLetter);
            animateLetterToCenter(textLetter);
        }
    }

    public void addObstacles () { // Adds letter
        if (miniGameController.getFrameAdjuster() == 2) {
            if (obstacleSide) {
                this.obstacle.setImage(animationChache.getImage("ObstacleBottom"));
            } else {
                this.obstacle.setImage(animationChache.getImage("ObstacleTop"));
            }
            this.obstacleSide = !this.obstacleSide;
        }
    }

    public void removeObstacle() { // Removes the letter.
        if (miniGameController.getFrameAdjuster() == 3) {
            this.obstacle.setImage(null);
        }
    }

    public Text createVisualLetter(Letter letter){ // Makes a text object for the letter.
        char letterValue = letter.getValue();
        String visualLetter = letterValue + "";
        Font customFont = Util.loadCustomFont();
        Text text = new Text(visualLetter);
        text.setFont(customFont);
        text.setFill(Color.WHITE); // Sets letter color.
        text.setStyle("-fx-font-size: 40pt"); // Sets font size.
        return text;
    }

    public void animateLetterToCenter(Text letter) {
        double centerX = stackPane.getWidth() / 2; // Setts the letters target in the middle of the screen.
        TranslateTransition translation = new TranslateTransition(Duration.millis(2000), letter);
        double translationDistance = 0.0;
        if (AnchorPane.getLeftAnchor(letter) != null) {
            translationDistance = centerX;
        } else {
            translationDistance = centerX - anchorPane.getWidth();
        }
        translation.setToX(translationDistance);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), letter);// 2 seconds to transition.
        fadeTransition.setFromValue(1.0f);// Transitions from 1 to 0 opacity.
        fadeTransition.setToValue(0.0f);
        ParallelTransition transitions = new ParallelTransition();
        transitions.getChildren().addAll(fadeTransition, translation);
        transitions.setCycleCount(Timeline.INDEFINITE);
        transitions.play();
    }

    public BorderPane getContent() {
        return this.content;
    }
}
