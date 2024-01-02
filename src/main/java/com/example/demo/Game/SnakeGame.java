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
        initializeGame(primaryStage); // initialize game when app starts
    }

    @Override
    public void startGame(Stage stage) {
        resetGame(); // reset game state
        createGameWindow(stage); // create game window
        createGameLoop(); // start game loop
    }

    @Override
    public void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop(); // stop game loop
        }
        SoundPlayer.getInstance().pauseBackgroundMusic(); // pause background music
    }

    @Override
    public void resumeGame(Stage stage) {
        createGameWindow(stage); // create game window
        gameLoop.play(); // start game loop
        SoundPlayer.getInstance().resumeBackgroundMusic(); // resume background music
    }

    public void initializeGame(Stage stage) {
        this.gameController = new GameController(GameConfig.INIT_SNAKE_LENGTH); // initialize game controller
        this.grid = new Grid(); // initialize grid
        this.gameRenderer = new GameRenderer(gameController, grid); // initialize game renderer
        gameRenderer.drawObstacle(); // draw obstacle
        GameActions gameActions = this; // reference to this object for game actions
        this.menuManager = new MenuManager(stage, gameActions); // initialize menu manager
    }

    public void resetGame() {
        this.gameController = new GameController(GameConfig.INIT_SNAKE_LENGTH); // reset game controller
        grid.clearGrid(gameRenderer.getVisualSnakeBody(), gameRenderer.getVisualLetters()); // clear grid
        this.gameRenderer = new GameRenderer(gameController, grid); // reset game renderer
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

    public void updateGame() {
        gameController.updateGame(); // update game state
        gameRenderer.renderGame(); // render game
        header.updateHeader(); // update header
        if (gameController.getGameOverStatus()) {
            menuManager.handleGameOver(); // handle game over
        }
    }

    public void createMiniGameWindow(Stage stage) {
        initializeMiniGame(); // initialize mini game
        BorderPane miniGameContent = miniGameRenderer.getMiniGameContent(); // get mini game content
        Scene miniGameScene = new Scene(miniGameContent, GameConfig.WIDTH, GameConfig.HEIGHT); // create scene for mini game
        miniGameScene.setOnKeyPressed(event -> miniGameController.handleKeyPress(event.getCode())); // set key press listener for mini game
        stage.setScene(miniGameScene); // set scene on stage
        stage.setTitle("MiniGame"); // set stage title
        stage.setResizable(false); // make stage not resizable
        stage.show(); // show the stage
    }

    public void initializeMiniGame() {
        miniGameController = new MiniGameController(); // initialize mini game controller
        miniGameRenderer = new MiniGameRenderer(miniGameController); // initialize mini game renderer
    }


    public void createMiniGameLoop() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateMiniGame())); // create game loop for mini game
        gameLoop.setCycleCount(Timeline.INDEFINITE); // set game loop to loop indefinitely
        gameLoop.play(); // start the game loop
    }

    private void updateMiniGame() {
        miniGameController.updateGame(); // update mini game
        miniGameRenderer.renderGame(); // render the mini game
        // updateWord(); //to update word
        if (miniGameController.getGameOverStatus()) { // check if game over
            // showGameOver((Stage) miniGameRenderer.getGrid().getScene().getWindow()); // showgame over screen
            menuManager.handleGameOver(); // call menu manageer and render the game stoppage and screen
        }
    }

    public static void main(String[] args) {
        Application.launch(); // launch application
    }
}
