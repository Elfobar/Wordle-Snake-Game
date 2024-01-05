package com.example.demo.Game;

import javafx.stage.Stage;

//This interface is needed to provide a contract that game classes can implement.
// In our case, the MenuManager class holds a reference to an object implementing this interface SnakeGame,
// When a user interacts with the menu, such as clicking on "Start Game"
// or "Start Mini-Game," the corresponding method in the GameActions interface is invoked. This allows for a clean and
// consistent way to manage different game launch from the menu system.
public interface GameActions {
    void startGame(Stage stage);
    void stopGame();
    void resumeGame(Stage stage);
    void startMiniGame(Stage stage);
    void stopMiniGame();
    void resumeMiniGame(Stage stage);

}
