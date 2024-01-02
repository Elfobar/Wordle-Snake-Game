package com.example.demo.Game;

import com.example.demo.GameCore.GameConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.UI.Grid;
import com.example.demo.UI.Header;
import com.example.demo.UI.Menu.MenuManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application implements GameActions {
    private MiniGameController miniGameController;
    private MiniGameRenderer miniGameRenderer;
    private GameController gameController;
    private GameRenderer gameRenderer;
    private MenuManager menuManager;
    private Timeline gameLoop;
    private Header header;
    private Grid grid;


    @Override
    public void start(Stage primaryStage) {
        initializeGame(primaryStage);
        initializeMiniGame(primaryStage);
    }

    @Override
    public void startGame(Stage stage) {
        resetGame();
        createGameWindow(stage);
        createGameLoop();
    }
    @Override
    public void startMiniGame(Stage stage) {
        resetMiniGame();
        createMiniGameWindow(stage);
        createMiniGameLoop();
    }

    @Override
    public void stopGame(){
        if(gameLoop != null){
            gameLoop.stop();
        }
        SoundPlayer.getInstance().pauseBackgroundMusic();
    }
    @Override
    public void stopMiniGame(){
        if(gameLoop != null){
            gameLoop.stop();
        }
        SoundPlayer.getInstance().pauseBackgroundMusic();
    }

    @Override
    public void resumeGame(Stage stage){
        createGameWindow(stage);
        gameLoop.play();
        SoundPlayer.getInstance().resumeBackgroundMusic();
    }

    @Override
    public void resumeMiniGame(Stage stage){
        createMiniGameWindow(stage);
        gameLoop.play();
        SoundPlayer.getInstance().resumeBackgroundMusic();
    }

    public void initializeGame(Stage stage) {
        this.gameController = new GameController(GameConfig.INIT_SNAKE_LENGTH);
        this.grid = new Grid();
        this.gameRenderer = new GameRenderer(gameController, grid);
        gameRenderer.drawObstacle();
        GameActions gameActions = this;
        this.menuManager = new MenuManager(stage, gameActions);
        menuManager.setState("Game");
    }

    public void initializeMiniGame(Stage stage){
        this.miniGameController = new MiniGameController();
        this.miniGameRenderer = new MiniGameRenderer(miniGameController);
        menuManager.setState("MiniGame");
    }

    public void resetGame(){
        this.gameController = new GameController(GameConfig.INIT_SNAKE_LENGTH);
        grid.clearGrid(gameRenderer.getVisualSnakeBody(), gameRenderer.getVisualLetters());
        this.gameRenderer = new GameRenderer(gameController, grid);
        menuManager.setState("Game");
    }

    public void resetMiniGame(){
        this.miniGameController = new MiniGameController();
        this.miniGameRenderer = new MiniGameRenderer(miniGameController);
        menuManager.setState("MiniGame");
    }

    public void createGameWindow(Stage gameStage) {
        BorderPane root = new BorderPane();
        VBox headerAndGrid = new VBox();
        this.header = new Header(gameController);
        HBox header = this.header.createHeader();

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
        gameStage.setTitle(GameConfig.GAME_NAME);
        gameStage.setResizable(false);
        gameStage.show();
    }

    public void createGameLoop(){
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void createMiniGameLoop(){
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateMiniGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void updateGame() {
        gameController.updateGame();
        gameRenderer.renderGame();
        header.updateHeader();
        if (gameController.getGameOverStatus()) {
            menuManager.handleGameOver();
        }
    }

    public void createMiniGameWindow(Stage stage){
        try {
            BorderPane miniGameContent = miniGameRenderer.getContent();
        } catch (Exception exception){
            Scene miniGameContent = miniGameRenderer.getContent().getScene();
        }
        this.header = new Header(miniGameController);
        HBox header = this.header.createHeader();
        Scene miniGameScene;
        try {
            BorderPane miniGameContent = miniGameRenderer.getContent();
            miniGameContent.setTop(header);
            miniGameScene = new Scene(miniGameContent, GameConfig.WIDTH, GameConfig.HEIGHT);
        } catch (Exception exception){
            miniGameScene = miniGameRenderer.getContent().getScene();
        }
        miniGameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                menuManager.handleMiniGamePause();
            } else {
                miniGameController.handleKeyPress(event.getCode());
            }
        });
        stage.setScene(miniGameScene);
        stage.setTitle("MiniGame");
        stage.setResizable(false);
        stage.show();
    }

    private void updateMiniGame() {
        miniGameController.updateGame();
        miniGameRenderer.renderGame();
        header.updateHeader();
        if(miniGameController.getGameOverStatus()){;
            menuManager.handleGameOver();
        }
    }

    public static void main(String[] args){
        Application.launch();
    }
}
