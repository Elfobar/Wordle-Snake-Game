package com.example.demo;

import javafx.stage.Stage;

public interface GameActions {
    void startGame(Stage stage);
    void stopGame();
    void resumeGame(Stage stage);
}
