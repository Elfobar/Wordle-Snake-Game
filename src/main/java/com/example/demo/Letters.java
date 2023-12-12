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

    public Letters(GridPane gridPane, int maxLetters, String pathToFile) {
        this.gridPane = gridPane;
        this.maxLetters = maxLetters;
        this.letters = new ArrayList<>();
        this.numOfLetters = 0;
        this.word = new Word(pathToFile);
    }

    public void spawnInitialLetters() {
        spawnNextLetter();
        for (int i = 0; i < 2; i++) {
            spawnLetter();
        }
    }


    public void spawnLetter() {
        //According to ASCII code, letters 'A' to 'Z' are within the range [65, 90]
        char letter =  (char) ('A' + (int) (Math.random() * 26));
        int y = (int) (Math.random() * Game.ROWS);
        int x = (int) (Math.random() * Game.COLUMNS);

        //for-loop checks if the randomly generated coordinates overlap with the coordinates of any existing letter
        //if yes, generates new random coordinates
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
                System.out.println(nextLetter + " and " + pickedLetter);
                if (nextLetter == pickedLetter) { //should add points to Score
                    grid.getGrid().getChildren().remove(letter.getText()); // Remove the Text node
                    letters.remove(letter);
                    snake.grow();
                    numOfLetters--;
                    word.incrementIndex();

                    if (word.isWordCompleted()) {
                        word.chooseRandomWord();

                        System.out.println("New word chosen: " + word.getCurrentWord());
                    }
                    spawnNextLetter();
                } else { //when picks the wrong letter - should deduct points
                    System.out.println("Game Over - Incorrect letter picked: " + pickedLetter);
                    System.exit(0);
                }
            }
        }
    }

    public int getNumOfLetters() {
        return numOfLetters;
    }

    public Word getWord(){
        return this.word;
    }
}
