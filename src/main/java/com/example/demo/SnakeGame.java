package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application {
    private GameRenderer gameRenderer;
    private GameController gameController;

    private static final int HEADER_SPACEHOLDER = 68;
    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;
    public static final int SNAKE_LENGTH = 3;


    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeGame();
        createGameWindow(primaryStage);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void createGameWindow(Stage primaryStage){
        BorderPane root = new BorderPane();
        VBox headerAndGrid = createHeaderWithWord();
        VBox.setVgrow(gameRenderer.getGrid(), Priority.ALWAYS);
        root.setTop(headerAndGrid);
        root.setCenter(gameRenderer.getGrid());
        Scene scene = new Scene(root, ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACEHOLDER);
        scene.setOnKeyPressed(event -> gameController.handleKeyPress(event.getCode()));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public VBox createHeaderWithWord(){
        Text targetWord = new Text(gameController.getTargetWord());
        Font cyberFont = loadCustomFont();
        targetWord.setFont(cyberFont);
        HBox hBox = new HBox(targetWord);
        hBox.setAlignment(Pos.CENTER);
        return new VBox(hBox, gameRenderer.getGrid());
    }
    private void initializeGame() {
        gameController = new GameController(SNAKE_LENGTH);
        gameRenderer = new GameRenderer(gameController);
        gameRenderer.drawGrid();
    }

    public void updateGame(){
        gameController.updateGame();
        gameRenderer.renderGame();
    }

    public Font loadCustomFont() {
        try {
            return Font.loadFont(getClass().getResourceAsStream(AppConfig.FONT_RELATIVE_PATH), AppConfig.FONT_SIZE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Font.getDefault();
        }
    }

    public static void main(String[] args){
        Application.launch();
    }



}
