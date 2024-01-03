package com.example.demo.UI; //specifies package location

//imports to make class work
import com.example.demo.Game.GameController;
import com.example.demo.Game.MiniGameController;
import com.example.demo.Util.Util;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Header {

    private MiniGameController miniGameController;
    private final Font font; // assigned once and is used to store the font that will be used for the text
    private GameController gameController; // used to control the game logic
    private Text targetWord; // used to display the target word
    private Text currentInput; // used to display the current input.
    private Text scoreText; // used to display the score.

    public Header(GameController gameController){
        this.font = Util.loadCustomFont();
        this.gameController = gameController;
        this.targetWord = getTargetWord(gameController.getTargetWord());
        this.currentInput = getCurrentInput();
        this.scoreText = getScoreText();
    }
    public Header(MiniGameController miniGameController){
        this.font = Util.loadCustomFont();
        this.miniGameController = miniGameController;
        this.targetWord = getTargetWord(miniGameController.getTargetWord());
        this.currentInput = getCurrentInput();
        this.scoreText = getScoreText();
    }

    public HBox createHeader(){ // container for laying out its children in a single horizontal row
        HBox hbox = initializeHeader(); //assigns the result of initializeHeader method to the hbox variable

        Text targetWord = this.targetWord; //assign targetWord variable to a new local variable also named targetWord
        Text currentInput = this.currentInput; //assigns currentInput to local variable named currentInput
        Text scoreText = this.scoreText; //assign scoreText to variable also named scoreText
        GridPane textPane = getScorePane(scoreText); // getScorePane method caled with the scoreText variable> assigns the result to the textPane

        StackPane textOverlap = new StackPane(); // Creates new StackPane container object (lays out its children in a back-to-front stack)
        textOverlap.getChildren().addAll(targetWord, currentInput); // adds targetWord and currentInput to children of the StackPane
        textOverlap.setAlignment(Pos.CENTER_LEFT); // alignment set to center left

        GridPane overlapPane = new GridPane(); // new GridPane object which is a container that lays out its children in a grid format
        overlapPane.setAlignment(Pos.CENTER); // alignmetn center
        overlapPane.getChildren().add(textOverlap); // adds textOverlap StackPane to children elements of gridpane

        hbox.getChildren().addAll(textPane, overlapPane); // adds textPane and overlapPane to HBox.
        textPane.setMinWidth(40); // set width of the textPane to 40 px
        HBox.setHgrow(textPane, Priority.NEVER); // set priority to never >should not grow to fill extra space
        HBox.setHgrow(overlapPane, Priority.ALWAYS); // sets horizontal grow priority to always so it always grow to fill extra space

        return hbox; // return hbox
    }

    public void updateHeader(){
        updateWord(); // call updateWord method in order to update header
        updateScore(); // call updateScore method to update header
    }

    private HBox initializeHeader(){
        HBox hbox = new HBox(); // Creates new hBox
        hbox.setAlignment(Pos.CENTER); // set alignment to center
        hbox.setStyle("-fx-background-color: #160244;"); // set background color
        hbox.setSpacing(10.0); // set spacing fo children of hbox elements
        return hbox;
    }

    private void updateWord(){
        String currentWord = "";
        if (gameController != null){
            currentWord = gameController.getTargetWord();
        } else {
            currentWord = miniGameController.getTargetWord();
        }
        /////
        if(currentInput != null){
            String currentInputString = "";
            if (gameController != null){
                currentInputString = gameController.getCurrentInput();
                currentInput.setText(currentInputString);
            } else {
                currentInputString = miniGameController.getCurrentInput();
                currentInput.setText(currentInputString);
            }
        }
        if(!currentWord.equals(targetWord.toString())){ // conditional for currentWord if it is not equal to the string representation of the targetWord
            targetWord.setText(currentWord); // set text targetWord to the currentWord
        }
    }

    private void updateScore(){
        int score;
        if (gameController != null) {
            score = gameController.getScore();
        } else {
            score = miniGameController.getScore();
        }
        this.scoreText.setText(Integer.toString(score));
    }

    private Text getScoreText() {

        Text scoreText = new Text("0"); // new Text object initialized with default score zero
        scoreText.setStyle("-fx-font-size: 25"); // set style to font size 25
        scoreText.setFill(Color.LIGHTBLUE); // set fill color to light blue

        return scoreText;
    }

    private Text getTargetWord(String word) { //this method basically fetches the targetWord
        Text targetWord = new Text(word); // new Text object with text of the word parameter
        targetWord.setFont(this.font); // set default cyber font
        targetWord.setFill(Color.LIGHTBLUE); // set color to light blue
        return targetWord; //return target world
    }

    private Text getCurrentInput() { //returns the glow effect of the collected word so it shines
        Text currentInput = new Text(); // new Text object
        Glow glow = new Glow(); // glow effect
        glow.setLevel(0.85); // level of the glow
        currentInput.setFont(this.font); // set font
        currentInput.setFill(Color.LIGHTBLUE); // set color to light blue.
        currentInput.setEffect(glow); // Sset effect to glow.
        return currentInput; // return current Input
    }

    private GridPane getScorePane(Text score){ // score updater
        GridPane scorePane = new GridPane(); // new GridPane
        VBox scoreVbox = new VBox(); //new VBox object
        scoreVbox.setAlignment(Pos.CENTER); // alignment of the scoreVbox is now centered

        Text text = new Text("Score:"); // display score word
        text.setStyle("-fx-font-size: 30"); // set font size
        text.setFill(Color.LIGHTBLUE); // fill color to light blue
        score.setFont(font); // cyber font set
        text.setFont(font); // set font for text score

        scoreVbox.getChildren().add(text); // Adds text to children of scoreVbox
        scoreVbox.getChildren().add(score); // Adds score text to children of scoreVbox

        scorePane.add(scoreVbox, 0, 0); // Adds the scoreVbox VBox to the scorePane GridPane at the position (0, 0).
        scorePane.setAlignment(Pos.CENTER); // Sets the alignment of the scorePane GridPane to center.
        return scorePane; // Returns the scorePane GridPane.
    }
}
