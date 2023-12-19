package com.example.demo;

import java.io.Serializable;

public class GameStateManager implements Serializable {

    private GameState currentState;

    public GameStateManager(){
        currentState = GameState.MENU;
    }


    public GameState getCurrentState(){
        return this.currentState;
    }

    public void setCurrentState(GameState state){
        this.currentState = state;
    }
}
