package com.example.demo.GameCore;

import javafx.scene.input.KeyCode;

public enum Direction {
    // Assigns coordinates change to each direction
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final int x; // x coordinate
    public final int y; // y coordinate

    private Direction(int x, int y) { // Constructor
        this.x = x;
        this.y = y;
    }

    public Direction getOpposite() { // Gets the opposite direction from current direction
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }

    public Direction getDirectionFromKeyCode(KeyCode keyCode, Direction currentDirection) { // Registers key press and turns it into a direction
        switch (keyCode) {
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            case LEFT:
                return LEFT;
            case RIGHT:
                return RIGHT;
            default:
                return currentDirection;
        }
    }

    public int getX() { // Get x coordinate
        return this.x;
    }

    public int getY() { // Get y coordinate
        return this.y;
    }
}

