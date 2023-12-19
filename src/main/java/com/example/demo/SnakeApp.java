package com.example.demo;

import com.example.demo.Menu.MenuManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class SnakeApp extends Application {

    private GameController gameController;
    private GameRenderer gameRenderer;
    private MenuManager menuManager;




    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void initializeGame(Stage stage){
        this.gameController = new GameController(SnakeConfig.INIT_SNAKE_LENGTH);
        this.gameRenderer = new GameRenderer(gameController);
        this.menuManager = new MenuManager(stage);

    }
}
