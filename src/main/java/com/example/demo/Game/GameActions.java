package com.example.demo.Game;

import javafx.stage.Stage;

public interface GameActions {
    void startGame(Stage stage);
    void stopGame();
    void resumeGame(Stage stage);
    void startMiniGame(Stage stage);
    void stopMiniGame();
    void resumeMiniGame(Stage stage);
}
