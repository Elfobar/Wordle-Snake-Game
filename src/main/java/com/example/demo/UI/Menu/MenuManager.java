package com.example.demo.UI.Menu;


import com.example.demo.Game.GameActions;
import com.example.demo.GameCore.GameConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MenuManager implements GameActions {

    private final GameActions gameActions;
    private final Stage stage;
    private AbstractMenu currentMenu;
    private String state;


    public MenuManager(Stage stage, GameActions gameActions){   //
        this.stage = stage;
        this.currentMenu = new MainMenu();
        this.gameActions = gameActions;
        this.state = "None";
        showMenu();
    }

    private void showMenu(){
        Scene scene = new Scene(currentMenu.createContent(), GameConfig.WIDTH, GameConfig.HEIGHT);
        stage.setScene(scene);
        stage.setTitle(GameConfig.GAME_NAME);
        stage.show();
        if(currentMenu instanceof GameOverScreen){
            setGameOverKeyInputs();
        } else{
            setMenuEventHandler();
        }
    }

    private void setMenuEventHandler(){
        Scene scene = stage.getScene();

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getTarget() instanceof ImageView){
                    ImageView eventSource = (ImageView) mouseEvent.getTarget();
                    handleMenuEvent(eventSource.getId());
                }
            }
        };
        scene.setOnMouseClicked(eventHandler);
    }

    private void setGameOverKeyInputs(){
        Scene currentScene = stage.getScene();
        currentScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) {
                runGame();
            } else if (keyCode == KeyCode.ESCAPE) {
                navigateToMainMenu();
            }
        });
    }

    private void handleMenuEvent(String eventID){
        switch(eventID){
            case "start":
                runGame();
                SoundPlayer.getInstance().playSFX(
                        Sounds.START_1,
                        Sounds.START_2,
                        Sounds.START_3);
                break;
            case "minigame":
                runMiniGame();
                SoundPlayer.getInstance().playSFX(
                        Sounds.START_1,
                        Sounds.START_2,
                        Sounds.START_3);
                break;
            case "settings":
                navigateToSettingsMenu();
                break;
            case "scoreboard":
                navigateToScoreboardMenu();
                break;
            case "back":
                navigateToMainMenu();
                break;
            case "continue":
                if (state.equals("Game")){
                    handleGamePause();
                } else {
                    handleMiniGamePause();
                }
                break;
            case "exit":
                Platform.exit();
                break;
        }
    }

    public void handleGameOver(){
        //lose sound?
        currentMenu = new GameOverScreen();
        showMenu();
        gameActions.stopGame();
    }

    public void handleGamePause(){;
        if(currentMenu instanceof EscMenu){
            gameActions.resumeGame(stage);
            currentMenu = new MainMenu();
        } else{
            currentMenu = new EscMenu();
            showMenu();
            gameActions.stopGame();
        }
    }
    public void handleMiniGamePause(){;
        if(currentMenu instanceof EscMenu){
            gameActions.resumeMiniGame(stage);
            currentMenu = new MainMenu();
        } else{
            currentMenu = new EscMenu();
            showMenu();
            gameActions.stopMiniGame();
        }
    }
    private void runGame(){
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.BACKGROUND_TRACK);
        gameActions.startGame(stage);
    }
    private void runMiniGame(){
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.BACKGROUND_TRACK);
        gameActions.startMiniGame(stage);
    }

    private void navigateToMainMenu(){
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4);
        currentMenu = new MainMenu();
        showMenu();
    }

    private void navigateToSettingsMenu() {
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4);
        currentMenu = new SettingsMenu();
        showMenu();
    }

    private void navigateToScoreboardMenu(){
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4
        );
        currentMenu = new ScoreboardMenu();
        showMenu();
    }
    public void setState(String state){
        this.state = state;
    }
    @Override
    public void startGame(Stage stage){}
    @Override
    public void startMiniGame(Stage stage){}
    @Override
    public void stopGame(){}
    @Override
    public void stopMiniGame(){}
    @Override
    public void resumeGame(Stage stage){}
    @Override
    public void resumeMiniGame(Stage stage){}

}
