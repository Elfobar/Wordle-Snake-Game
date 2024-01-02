package com.example.demo.Game;

import javafx.stage.Stage;

public interface GameActions {
    void startGame(Stage stage);
    void stopGame();
    void resumeGame(Stage stage);
}
