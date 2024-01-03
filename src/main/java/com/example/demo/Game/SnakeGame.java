package com.example.demo.Game; //specify package name / location

//imports to make class work
import com.example.demo.GameCore.GameConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
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
    private KeyFrame frame;
    private Header header; // header for game UI
    private Grid grid; // grid for game UI    private int speed;
    private int speed;

    @Override
    public void start(Stage primaryStage) {
        initializeGame(primaryStage); //init main snake game
        initializeMiniGame(primaryStage); //init mini game
    }

    @Override
    public void startGame(Stage stage) { //starts the game, no music cuz game runs from menumanager class
        resetGame(); // reset game state
        createGameWindow(stage); // create game window
        createGameLoop(); // start game loop
    }
    @Override
    public void startMiniGame(Stage stage) { // starts the mini game and its music
        resetMiniGame();
        createMiniGameWindow(stage);
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.MINIGAME_MUSIC);
        createMiniGameLoop();
    }

    @Override
    public void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop(); // stop game loop
        }
        SoundPlayer.getInstance().pauseBackgroundMusic(); // pause snake background music
    }
    @Override
    public void stopMiniGame(){ //stop mini game and mini game music
        if(gameLoop != null){
            gameLoop.stop();
        }
        SoundPlayer.getInstance().pauseBackgroundMusic();
    }

    @Override
    public void resumeGame(Stage stage) { // resume snake game
        createGameWindow(stage); // create game window
        gameLoop.play(); // start game loop
        SoundPlayer.getInstance().resumeBackgroundMusic(); // resume background music
    }

    @Override
    public void resumeMiniGame(Stage stage){ //resume mini game and music
        createMiniGameWindow(stage);
        gameLoop.play();
        SoundPlayer.getInstance().resumeBackgroundMusic();
    }

    public void initializeGame(Stage stage) { //init game
        this.gameController = new GameController(GameConfig.INIT_SNAKE_LENGTH);
        this.grid = new Grid();
        this.gameRenderer = new GameRenderer(gameController, grid);
        gameRenderer.drawObstacle();
        GameActions gameActions = this;
        this.menuManager = new MenuManager(stage, gameActions);
        menuManager.setState("Game");
    }

    public void initializeMiniGame(Stage stage){ //init mini game
        this.miniGameController = new MiniGameController();
        this.miniGameRenderer = new MiniGameRenderer(miniGameController);
        menuManager.setState("MiniGame");
    }

    public void resetGame(){ //reset game method
        this.gameController = new GameController(GameConfig.INIT_SNAKE_LENGTH);
        grid.clearGrid(gameRenderer.getVisualSnakeBody(), gameRenderer.getVisualLetters());
        this.gameRenderer = new GameRenderer(gameController, grid);
        menuManager.setState("Game");
    }

    public void resetMiniGame(){ //reset mini game method
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
    
    public void increaseSpeed(){
        speed = speed + (-10); //This decreases the refresh rate -> increased snake speed
        this.gameLoop.getKeyFrames().clear();
        this.frame = new KeyFrame(Duration.millis(speed), event -> updateGame());
        this.gameLoop = new Timeline(frame);
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
        this.gameLoop.play();
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
            BorderPane miniGameContent = miniGameRenderer.getContent(); // get mini game content
        } catch (Exception exception){
            Scene miniGameContent = miniGameRenderer.getContent().getScene(); // get mini game scene
        }
        this.header = new Header(miniGameController); // initialize header
        HBox header = this.header.createHeader(); // create header
        Scene miniGameScene;
        try {
            BorderPane miniGameContent = miniGameRenderer.getContent(); // get mini game content
            miniGameContent.setTop(header); // set header to top
            miniGameScene = new Scene(miniGameContent, GameConfig.WIDTH, GameConfig.HEIGHT); // create mini game scene
        } catch (Exception exception){
            miniGameScene = miniGameRenderer.getContent().getScene(); // get mini game scene
        }
        miniGameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                menuManager.handleMiniGamePause(); // handle mini game pause
            } else {
                miniGameController.handleKeyPress(event.getCode()); // handle key press
            }
        });
        stage.setScene(miniGameScene); // set scene
        stage.setTitle("MiniGame"); // set title
        stage.setResizable(false); // set resizable to false
        stage.show(); // show stage
    }

    private void updateMiniGame() {
        miniGameController.updateGame(); // update mini game state
        miniGameRenderer.renderGame(); // render mini game
        header.updateHeader(); // update header
        if(miniGameController.getGameOverStatus()){
            menuManager.handleGameOver(); // handle game over
        }
    }


    public static void main(String[] args) {
        Application.launch(); // launch application
    }
}
