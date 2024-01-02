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


    public MenuManager(Stage stage, GameActions gameActions){
        this.stage = stage;
        this.currentMenu = new MainMenu();
        this.gameActions = gameActions;
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
                    //System.out.println("Clicked on ImageView with ID: " + eventSource.getId());
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
            case "settings":
                navigateToSettingsMenu();
                break;
            case "leaderboard":
                navigateToLeaderboardMenu();
                break;
            case "back":
                navigateToMainMenu();
                break;
            case "continue":
                handleGamePause();
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

    private void runGame(){
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.BACKGROUND_TRACK);
        gameActions.startGame(stage);
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

    private void navigateToLeaderboardMenu() {
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4);
        currentMenu = new LeaderboardMenu();
        showMenu();
    }
    @Override
    public void startGame(Stage stage){}
    @Override
    public void stopGame(){}
    @Override
    public void resumeGame(Stage stage){}

}
