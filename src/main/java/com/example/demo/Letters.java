package com.example.demo;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Letters {

    private GridPane gridPane;
    private List<Cell> letters;
    private int numOfLetters;
    private final int maxLetters;
    private Word word;


    public Letters(GridPane gridPane, int maxLetters) {
        this.gridPane = gridPane;
        this.maxLetters = maxLetters;
        this.letters = new ArrayList<>();
        this.numOfLetters = 0;
        this.word = new Word();
    }

    public void spawnInitialLetters() {
        spawnNextLetter();

        for (int i = 0; i < 2; i++) {
            spawnLetter();
        }
    }


    public void spawnLetter() {
        char letter =  (char) ('A' + (int) (Math.random() * 26));
        int y = (int) (Math.random() * Game.ROWS);
        int x = (int) (Math.random() * Game.COLUMNS);

        for (Cell existingLetter : letters) {
            if (existingLetter.getCoordinate().getX() == x && existingLetter.getCoordinate().getY() == y) {
                y = (int) (Math.random() * Game.ROWS);
                x = (int) (Math.random() * Game.COLUMNS);
            }
        }
        Cell letterCell = new Cell(Game.CELL_SIZE, Color.BLUE, x, y);
        letterCell.getText().setText(String.valueOf(letter));

        letters.add(letterCell);
        gridPane.add(letterCell.getText(), x, y);
        numOfLetters++;
    }

    public void spawnNextLetter() {
        char letter = word.getNextLetter();
        int y = (int) (Math.random() * Game.ROWS);
        int x = (int) (Math.random() * Game.COLUMNS);

        Cell letterCell = new Cell(Game.CELL_SIZE, Color.BLUE, x, y);
        letterCell.getText().setText(String.valueOf(letter));

        letters.add(letterCell);
        gridPane.add(letterCell.getText(), x, y);
        numOfLetters++;
    }

    public void intersectLetter(Snake snake, Grid grid) {
        for (Cell letter : new ArrayList<>(letters)) {
            if (snake.getHead().equals(letter.getCoordinate())) {
                char pickedLetter = letter.getText().getText().charAt(0);
                char nextLetter = word.getNextLetter();
                System.out.println(nextLetter + " och " + pickedLetter);
                if (nextLetter == pickedLetter) {
                    grid.getGrid().getChildren().remove(letter.getText()); // Remove the Text node
                    letters.remove(letter);
                    snake.grow();
                    numOfLetters--;
                    word.incrementIndex();

                    if (word.isWordCompleted()) {
                        word.chooseRandomWord();
                        spawnNextLetter();
                        System.out.println("New word chosen: " + word.getCurrentWord());
                    }
                    spawnNextLetter();
                } else {
                    System.out.println("Game Over - Incorrect letter picked: " + pickedLetter);
                    System.exit(0);
                }
            }
        }
    }

    public int getNumOfLetters() {
        return numOfLetters;
    }
}


