package com.example.demo.Menu;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MenuManager {

    private AbstractMenu currentMenu;
    private Stage stage;

    public MenuManager(Stage stage){
        this.stage = stage;
        this.currentMenu = new MainMenu();
    }

    public void showMenu(StackPane menuContent){
        Scene scene = new Scene(menuContent);
        stage.setScene(scene);
        setMenuEventHandler();
    }

    public void setMenuEventHandler(){
        Scene scene = stage.getScene();

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getSource() instanceof ImageView){
                    ImageView source = (ImageView) mouseEvent.getSource();
                    handleMenuEvent(source.getId());
                }
            }
        };
        scene.setOnMouseClicked(eventHandler);
    }

    private void handleMenuEvent(String eventID){
        switch(eventID){
            case "start":
                break;
            case "settings":
                navigateToSettingsMenu();
                break;
            case "leaderboard":
                navigateToLeaderboardMenu();
                break;
            case "exit":
                Platform.exit();
                break;
        }
    }

    private void navigateToSettingsMenu() {
        currentMenu = new SettingsMenu();
        showMenu(currentMenu.createContent());
    }

    private void navigateToLeaderboardMenu() {
        currentMenu = new LeaderboardMenu();
        showMenu(currentMenu.createContent());
    }


    public void handleGameOver(){
        currentMenu = new GameOverScreen();
        showMenu(currentMenu.createContent());
    }

}
