package com.example.demo;

import com.example.demo.Menu.MenuManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application implements GameActions {

    private MiniGameController miniGameController;
    private MiniGameRenderer miniGameRenderer;
    private GameController gameController;
    private GameRenderer gameRenderer;
    private MenuManager menuManager;
    private Timeline gameLoop;
    private Grid grid;
    private Header header;
    private Text targetWord;
    private Text currentInput;
    private Text scoreText;


    @Override
    public void start(Stage primaryStage) {
        initializeGame(primaryStage);
        menuManager.showMenu();
    }

    @Override
    public void startGame(Stage stage) {
        resetGame();
        createGameWindow(stage);
        createGameLoop();
    }

    @Override
    public void stopGame(){
        if(gameLoop != null){
            gameLoop.stop();
        }
    }

    @Override
    public void resumeGame(Stage stage){
        createGameWindow(stage);
        gameLoop.play();
    }

    public void initializeGame(Stage stage) {
        this.gameController = new GameController(SnakeConfig.INIT_SNAKE_LENGTH);
        this.grid = new Grid();
        this.gameRenderer = new GameRenderer(gameController, grid);
        gameRenderer.drawObstacle();
        GameActions gameActions = this;
        this.menuManager = new MenuManager(stage, gameActions);
    }

    public void resetGame(){
        this.gameController = new GameController(SnakeConfig.INIT_SNAKE_LENGTH);
        grid.clearGrid(gameRenderer.getVisualSnakeBody(), gameRenderer.getVisualLetters());
        this.gameRenderer = new GameRenderer(gameController, grid);
    }

    public void createGameWindow(Stage gameStage) {
        BorderPane root = new BorderPane();
        VBox headerAndGrid = new VBox();
        this.header = new Header();
        HBox header = this.header.createHeader();

        this.scoreText = this.header.getScoreText();
        GridPane textPane = this.header.getScorePane(this.scoreText);

        this.targetWord = this.header.getTargetWord(gameController.getTargetWord());
        this.currentInput = this.header.getCurrentInput();

        StackPane textOverlap = new StackPane();
        textOverlap.getChildren().addAll(targetWord, currentInput);
        textOverlap.setAlignment(Pos.CENTER_LEFT);

        GridPane overlapPane = new GridPane();
        overlapPane.setAlignment(Pos.CENTER);
        overlapPane.getChildren().add(textOverlap);

        header.getChildren().addAll(textPane, overlapPane);
        textPane.setMinWidth(40);
        HBox.setHgrow(textPane, Priority.NEVER);
        HBox.setHgrow(overlapPane, Priority.ALWAYS);

        headerAndGrid.getChildren().add(header);
        headerAndGrid.getChildren().add(grid.getGrid());

        VBox.setVgrow(grid.getGrid(), Priority.ALWAYS);
        root.setTop(headerAndGrid);
        root.setCenter(grid.getGrid());


        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                menuManager.handleGamePause();
            } else {
                gameController.handleKeyPress(event.getCode());
            }
        });

        gameStage.setScene(scene);
        gameStage.setTitle("Snake");
        gameStage.setResizable(false);
        gameStage.show();
    }

    public void createGameLoop(){
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void updateGame() {
        gameController.updateGame();
        gameRenderer.renderGame();
        updateWord();
        updateScore();
        if (gameController.getGameOverStatus()) {
            menuManager.handleGameOver();
        }
    }

    public void updateWord(){
        String currentWord = "";
        currentWord = gameController.getTargetWord();
        if(currentInput != null){
            String currentInputString = gameController.getCurrentInput();
            currentInput.setText(currentInputString);
        }
        if(!currentWord.equals(targetWord.toString())){
            targetWord.setText(currentWord);
        }
    }

    public void updateScore(){
        GameController gc = gameController;
        int score = gc.getScore();
        this.scoreText.setText(Integer.toString(score));
    }



    public void createMiniGameWindow(Stage stage){
        initializeMiniGame();
        BorderPane miniGameContent = miniGameRenderer.getMiniGameContent();
//        VBox header = createHeader();
//        header.getChildren().get(0).setStyle("-fx-background-color: #000000;");
//        miniGameContent.setTop(header);
        Scene miniGameScene = new Scene(miniGameContent, SnakeConfig.WIDTH, SnakeConfig.HEIGHT);
        miniGameScene.setOnKeyPressed(event -> miniGameController.handleKeyPress(event.getCode()));
        stage.setScene(miniGameScene);
        stage.setTitle("MiniGame");
        stage.setResizable(false);
        stage.show();
    }

    public void initializeMiniGame() {
        miniGameController = new MiniGameController();
        miniGameRenderer = new MiniGameRenderer(miniGameController);
    }

    public void createMiniGameLoop(){
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateMiniGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void updateMiniGame() {
        miniGameController.updateGame();
        miniGameRenderer.renderGame();
//        updateWord();
        if(miniGameController.getGameOverStatus()){
//            showGameOver((Stage) miniGameRenderer.getGrid().getScene().getWindow());
            menuManager.handleGameOver();
        }
    }

    public static void main(String[] args){
        Application.launch();
    }
}
