package com.example.demo.Game; //specify package name / location

//imports to make class work
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
    private MiniGameController miniGameController; // controller for mini game
    private MiniGameRenderer miniGameRenderer; // renderer for mini game
    private GameController gameController; // controller for main game
    private GameRenderer gameRenderer; // renderer for main game
    private MenuManager menuManager; // manager for menus
    private Timeline gameLoop; // loop for game updates
    private Header header; // header for game UI
    private Grid grid; // grid for game UI

    @Override
    public void start(Stage primaryStage) {
        initializeGame(primaryStage);
        initializeMiniGame(primaryStage);
    }

    @Override
    public void startGame(Stage stage) {
        resetGame(); // reset game state
        createGameWindow(stage); // create game window
        createGameLoop(); // start game loop
    }
    @Override
    public void startMiniGame(Stage stage) {
        resetMiniGame();
        createMiniGameWindow(stage);
        createMiniGameLoop();
    }

    @Override
    public void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop(); // stop game loop
        }
        SoundPlayer.getInstance().pauseBackgroundMusic(); // pause background music
    }
    @Override
    public void stopMiniGame(){
        if(gameLoop != null){
            gameLoop.stop();
        }
        SoundPlayer.getInstance().pauseBackgroundMusic();
    }

    @Override
    public void resumeGame(Stage stage) {
        createGameWindow(stage); // create game window
        gameLoop.play(); // start game loop
        SoundPlayer.getInstance().resumeBackgroundMusic(); // resume background music
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
        BorderPane root = new BorderPane(); // root pane
        VBox headerAndGrid = new VBox(); // vbox for header and grid
        this.header = new Header(gameController); // initialize header
        HBox header = this.header.createHeader(); // create header

        headerAndGrid.getChildren().add(header); // add header to vbox
        headerAndGrid.getChildren().add(grid.getGrid()); // add grid to vbox

        VBox.setVgrow(grid.getGrid(), Priority.ALWAYS); // make grid grow vertically
        root.setTop(headerAndGrid); // set vbox as top of root
        root.setCenter(grid.getGrid()); // set grid as center of root

        Scene scene = new Scene(root); // create scene with root
        scene.setOnKeyPressed(event -> { // set key press listener
            if (event.getCode() == KeyCode.ESCAPE) {
                menuManager.handleGamePause(); // handle game pause
            } else {
                gameController.handleKeyPress(event.getCode()); // handle key press
            }
        });

        gameStage.setScene(scene); // set scene on stage
        gameStage.setTitle(GameConfig.GAME_NAME); // set stage title
        gameStage.setResizable(false); // make stage not resizable
        gameStage.show(); // show stage
    }

    public void createGameLoop() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame())); // create game loop
        gameLoop.setCycleCount(Timeline.INDEFINITE); // set game loop to loop indefinitely
        gameLoop.play(); // start game loop
    }

    public void createMiniGameLoop(){
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateMiniGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void updateGame() {
        gameController.updateGame(); // update game state
        gameRenderer.renderGame(); // render game
        header.updateHeader(); // update header
        if (gameController.getGameOverStatus()) {
            menuManager.handleGameOver(); // handle game over
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

    public static void main(String[] args) {
        Application.launch(); // launch application
    }
}
