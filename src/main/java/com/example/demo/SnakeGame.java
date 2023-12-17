package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application {
    private GameRenderer gameRenderer;
    private GameController gameController;

    private Menu menu;
    private Text targetWord;

    private Timeline timeline;

    private static final int HEADER_SPACEHOLDER = 68;
    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int CELL_SIZE = 40;
    public static final int SNAKE_LENGTH = 3;


    @Override
    public void start(Stage primaryStage) throws Exception {
        createMenu(primaryStage);
    }

    public void showGame(Stage primaryStage){
        initializeGame();
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
        initializeGameLoop();
    }

    public void initializeGameLoop(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public VBox createHeaderWithWord(){
        this.targetWord = new Text(gameController.getTargetWord());
        Font cyberFont = Util.loadCustomFont(getClass());
        targetWord.setFont(cyberFont);
        targetWord.setFill(Color.LIGHTBLUE);
        HBox hBox = new HBox(targetWord);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #160244;");
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
        updateWord();
    }

    public void updateWord(){
        String currentWord = gameController.getTargetWord();
        if(!currentWord.equals(targetWord.toString())){
            targetWord.setText(currentWord);
        }
    }

    public void createMenu(Stage stage){
        this.menu = new Menu();
        Scene scene = new Scene(new Pane(), 800, 800);
        stage.setScene(scene);
        showMenu(stage);
    }

    public void showMenu(Stage stage){
        menu.setState(0);
        if (menu.getMenuContent().getScene() == null){
            stage.getScene().setRoot(menu.getMenuContent());
        } else {
            stage.setScene(menu.getMenuContent().getScene());
        }
        stage.setTitle("SnakeGameMenu");
        stage.show();
        this.timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> updateScene(menu.getState(), stage)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateScene(int state, Stage stage){
        switch (state){
            case 1:
                this.timeline.stop();
                showGame(stage);
                break;
            case 2:
                stage.getScene().setRoot(menu.getSettingsContent());
                stage.setTitle("Settings");
                stage.show();
                break;
            case 3:
                stage.getScene().setRoot(menu.getLeaderboardContent());
                stage.setTitle("Leaderboard");
                stage.show();
                break;
            case 4:
                this.timeline.stop();
                System.exit(0);
                break;
            case 5:
                menu.setState(0);
                stage.getScene().setRoot(menu.getMenuContent());
                stage.setTitle("SnakeGameMenu");
                stage.show();
        }
    }

    public void showGameOver(Stage stage){
        this.timeline.stop();
        Pane gameOverContent = menu.createGameOverContent();
        Scene gameOverScene = new Scene(gameOverContent, 800, 800);
        gameOverScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                showGame(stage);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                showMenu(stage);
            }
        });
        stage.setScene(gameOverScene);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch();
    }



}
