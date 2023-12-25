package com.example.demo.Menu;


import com.example.demo.GameActions;
import com.example.demo.SnakeConfig;
import javafx.animation.PauseTransition;
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
    }

    public void showMenu(){
        Scene scene = new Scene(currentMenu.createContent(), SnakeConfig.WIDTH, SnakeConfig.HEIGHT);
        stage.setScene(scene);
        stage.setTitle("SnakeMenu");
        stage.show();
        if(this.currentMenu instanceof GameOverScreen){
            setGameOverKeyInputs();
        } else{
            setMenuEventHandler();
        }
    }

    public void setMenuEventHandler(){
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

    public void setGameOverKeyInputs(){
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
    }
    @Override
    public void startGame(Stage stage){}
    @Override
    public void stopGame(){}
    @Override
    public void resumeGame(Stage stage){}

}
