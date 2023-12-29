package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application {
    private GameRenderer gameRenderer;
    private AbstractController controller;
    private MiniGameRenderer miniGameRenderer;
    private String state;
    private Menu menu;
    private Header header;
    private Scene loadingScene;
    private Text targetWord;
    private Text currentInput;
    private Text scoreText;
    private Timeline timeline;

    public static final int HEADER_SPACE = 68;
    public static final int ROWS = 17;
    public static final int COLUMNS = 17;
    public static final int CELL_SIZE = 40;
    public static final int SNAKE_LENGTH = 3;


    @Override
    public void start(Stage primaryStage) throws Exception {
        createMenu(primaryStage);
    }

    private void initializeGame(Stage stage) {
        controller = new GameController(SNAKE_LENGTH);
        gameRenderer = new GameRenderer((GameController) controller);
        gameRenderer.drawGrid();
        this.state = "Game";
        //this.scoreText = 0;
        initializeGameLoop();

        BorderPane root = new BorderPane();
        VBox headerAndGrid = new VBox();
        this.header = new Header();
        HBox header = this.header.createHeader();

        this.scoreText = this.header.getScoreText();
        GridPane textPane = this.header.getScorePane(this.scoreText);

        this.targetWord = this.header.getTargetWord(controller.getTargetWord());
        this.currentInput = this.header.getCurrentInput();

        StackPane textOverlap = new StackPane();
        textOverlap.getChildren().addAll(targetWord, currentInput);
        textOverlap.setAlignment(Pos.CENTER_LEFT);

        GridPane overlapPane = new GridPane();
        overlapPane.setAlignment(Pos.CENTER);
        overlapPane.getChildren().add(textOverlap);

        header.getChildren().addAll(textPane, overlapPane);
        textPane.setMinWidth(40);
        HBox.setHgrow(textPane, Priority.NEVER);
        HBox.setHgrow(overlapPane, Priority.ALWAYS);

        headerAndGrid.getChildren().add(header);
        headerAndGrid.getChildren().add(gameRenderer.getGrid());

        VBox.setVgrow(gameRenderer.getGrid(), Priority.ALWAYS);
        root.setTop(headerAndGrid);
        root.setCenter(gameRenderer.getGrid());
        Scene scene = new Scene(root, ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACE);
        scene.setFill(Color.BLACK);

        gameRenderer.drawObstacle();

        scene.setOnKeyPressed(event -> controller.handleKeyPress(event.getCode()));
        Platform.runLater(() -> {
            stage.setTitle("Snake");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }

    public void initializeGameLoop(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public void updateScore(){
        GameController gc = (GameController)controller;
        int score = gc.getScore();
        this.scoreText.setText(Integer.toString(score));
    }

    public void updateGame(){
        controller.updateGame();
        gameRenderer.renderGame();
        updateWord();
        updateScore();
        if(controller.getGameOverStatus()){
            showGameOver((Stage) gameRenderer.getGrid().getScene().getWindow());
        }
    }

    public void updateWord(){
        String currentWord = "";
        currentWord = controller.getTargetWord();
        if(currentInput != null){
            String currentInputString = controller.getCurrentInput();
            currentInput.setText(currentInputString);
        }
        if(!currentWord.equals(targetWord.toString())){
            targetWord.setText(currentWord);
        }
    }

    public void createMenu(Stage stage){
        this.menu = new Menu();
        this.loadingScene = new Scene(menu.getLoadingScreen(), ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACE);
        Scene scene = new Scene(new Pane(), ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACE);
        loadingScene.setFill(Color.BLACK);
        stage.setScene(scene);
        showMenu(stage);
    }

    public void showMenu(Stage stage){
        menu.setState(0);
        if (menu.getMenuContent().getScene() == null){
            stage.getScene().setRoot(menu.getMenuContent());
        } else {
            stage.setScene(menu.getMenuContent().getScene());
        }
        stage.setTitle("SnakeGameMenu");
        stage.show();
        this.timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> updateScene(menu.getState(), stage)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateScene(int state, Stage stage){
        switch (state){
            case 1:
                this.timeline.stop();
                stage.setScene(loadingScene);
                new Thread(() -> initializeGame(stage)).start();
                //showMiniGame(stage);
                break;
            case 2:
                stage.getScene().setRoot(menu.getSettingsContent());
                stage.setTitle("Settings");
                stage.show();
                break;
            case 3:
                stage.getScene().setRoot(menu.getLeaderboardContent());
                stage.setTitle("Leaderboard");
                stage.show();
                break;
            case 4:
                this.timeline.stop();
                System.exit(0);
                break;
            case 5:
                menu.setState(0);
                stage.getScene().setRoot(menu.getMenuContent());
                stage.setTitle("SnakeGameMenu");
                stage.show();
        }
    }

    public void showGameOver(Stage stage){
        this.timeline.stop();
        Pane gameOverContent = menu.createGameOverContent();
        Scene gameOverScene = new Scene(gameOverContent, ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACE);
        gameOverScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                stage.setScene(loadingScene);
                new Thread(() -> initializeGame(stage)).start();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                showMenu(stage);
            }
        });
        stage.setScene(gameOverScene);
        stage.show();
    }

    public void showMiniGame(Stage stage){
        this.state = "MiniGame";
        initializeMiniGame();
        BorderPane miniGameContent = miniGameRenderer.getMiniGameContent();
        //VBox header = createHeader();
        //header.getChildren().get(0).setStyle("-fx-background-color: #000000;");
        //miniGameContent.setTop(header);
        Scene miniGameScene = new Scene(miniGameContent, ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACE);
        miniGameScene.setOnKeyPressed(event -> controller.handleKeyPress(event.getCode()));
        stage.setScene(miniGameScene);
        stage.setTitle("MiniGame");
        stage.setResizable(false);
        stage.show();
        initializeMiniGameLoop();
    }

    public void initializeMiniGame() {
        controller = new MiniGameController();
        miniGameRenderer = new MiniGameRenderer((MiniGameController) controller);
    }

    public void initializeMiniGameLoop(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateMiniGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateMiniGame() {
        controller.updateGame();
        miniGameRenderer.renderGame();
        updateWord();
        if(controller.getGameOverStatus()){
            showGameOver((Stage) miniGameRenderer.getGrid().getScene().getWindow());
        }
    }

    public static void main(String[] args){
        Application.launch();
    }
}
