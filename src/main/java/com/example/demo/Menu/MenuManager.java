package com.example.demo.Menu;


import com.example.demo.*;
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
                    System.out.println("Clicked on ImageView with ID: " + eventSource.getId());
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
        currentMenu = new GameOverScreen();
        showMenu();



        // Save the score to a file using ScoreFileManager
        //ScoreFileManager.saveScore(playerScore, "scores.txt");
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
        gameActions.startGame(stage);
    }

    private void navigateToMainMenu(){
        currentMenu = new MainMenu();
        showMenu();
    }

    private void navigateToSettingsMenu() {
        currentMenu = new SettingsMenu();
        showMenu();
    }

    private void navigateToLeaderboardMenu() {
        currentMenu = new LeaderboardMenu();
        showMenu();
        Util.readScores();
    }
    @Override
    public void startGame(Stage stage){}
    @Override
    public void stopGame(){}
    @Override
    public void resumeGame(Stage stage){}

}
