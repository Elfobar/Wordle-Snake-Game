package com.example.demo.Game;

import com.example.demo.GameCore.GameConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import com.example.demo.UI.Header;
import com.example.demo.UI.Menu.MenuManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MiniGame implements GameActions{
    private MiniGameController miniGameController;
    private MiniGameRenderer miniGameRenderer;
    private Timeline gameLoop;
    private MenuManager menuManager;
    private Header header;

    public MiniGame(Timeline gameloop, MenuManager menuManager) {
        this.miniGameController = new MiniGameController();
        this.miniGameRenderer = new MiniGameRenderer(miniGameController);
        this.menuManager = menuManager;
        this.gameLoop = gameloop;
    }

    public void resetMiniGame(){
        this.miniGameController = new MiniGameController();
        this.miniGameRenderer = new MiniGameRenderer(miniGameController);
    }

    public void createMiniGameLoop(){
        this.gameLoop = new Timeline(new KeyFrame(Duration.millis(200), event -> updateMiniGame()));
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
        this.gameLoop.play();
    }

    private void updateMiniGame() {
        miniGameController.updateGame(); // update mini game state
        miniGameRenderer.renderGame(); // render mini game
        header.updateHeader(); // update header
        if(miniGameController.getGameOverStatus()){
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

    @Override
    public void startGame(Stage stage) {
        resetMiniGame();
        createMiniGameWindow(stage);
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.MINIGAME_MUSIC);
        createMiniGameLoop();
    }

    @Override
    public void stopGame() {
        if(gameLoop != null){
            gameLoop.stop();
        }
        SoundPlayer.getInstance().pauseBackgroundMusic();
    }

    @Override
    public void resumeGame(Stage stage) {
        createMiniGameWindow(stage);
        gameLoop.play();
        SoundPlayer.getInstance().resumeBackgroundMusic();
    }

    @Override
    public void startMiniGame(Stage stage) {
        resetMiniGame();
        createMiniGameWindow(stage);
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.MINIGAME_MUSIC);
        createMiniGameLoop();}

    @Override
    public void stopMiniGame() {
        if(gameLoop != null){
        gameLoop.stop();
        }
        SoundPlayer.getInstance().pauseBackgroundMusic();
    }

    @Override
    public void resumeMiniGame(Stage stage){
        createMiniGameWindow(stage);
        gameLoop.play();
        SoundPlayer.getInstance().resumeBackgroundMusic();}
}
