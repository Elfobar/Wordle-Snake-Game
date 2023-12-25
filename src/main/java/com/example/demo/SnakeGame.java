package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame {
/*    private GameRenderer gameRenderer;
    private AbstractController controller;
    private MiniGameRenderer miniGameRenderer;
    private Menu menu;
    private Scene loadingScene;
    private Scene gameScene;
    private Parent pause;
    private Text targetWord;
    private Text currentInput;
    private Timeline timeline;

    public static final int HEADER_SPACE = 68;
    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
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
        initializeGameLoop();

        BorderPane root = new BorderPane();
        VBox headerAndGrid = createHeaderWithWord();
        VBox.setVgrow(gameRenderer.getGrid(), Priority.ALWAYS);
        root.setTop(headerAndGrid);
        root.setCenter(gameRenderer.getGrid());
        this.gameScene= new Scene(root, ROWS*CELL_SIZE, COLUMNS*CELL_SIZE + HEADER_SPACE);
        gameScene.setFill(Color.BLACK);
        gameScene.setOnKeyPressed(event -> listenToEvents(event.getCode(), stage));
        Platform.runLater(() -> {
            stage.setTitle("Snake");
            stage.setScene(gameScene);
            stage.setResizable(false);
            stage.show();
        });
    }

    public void listenToEvents(KeyCode keyCode, Stage stage){
        if (keyCode == KeyCode.ESCAPE && menu.getState() != 6){
            updateScene(6, stage);
        } else if ( keyCode == KeyCode.ESCAPE && menu.getState() == 6) {
            updateScene(7, stage);
        } else {
            controller.handleKeyPress(keyCode);
        }
    }
    public void initializeGameLoop(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> updateGame()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public VBox createHeaderWithWord(){
        VBox vBox = createHeader();
        vBox.getChildren().add(gameRenderer.getGrid());
        return vBox;
    }

    public VBox createHeader(){
        this.targetWord = new Text(controller.getTargetWord());
        this.currentInput = new Text();
        Font cyberFont = Util.loadCustomFont(getClass());
        Glow glow = new Glow();
        glow.setLevel(0.8);
        targetWord.setFont(cyberFont);
        targetWord.setFill(Color.LIGHTBLUE);
        targetWord.setEffect(glow);
        currentInput.setFont(cyberFont);
        currentInput.setFill(Color.LIGHTBLUE);
        currentInput.setEffect(glow);
        HBox hBox = new HBox(targetWord,  currentInput);
        hBox.setSpacing(30.0);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #160244;");
        return new VBox(hBox);
    }

    public void updateGame(){
        controller.updateGame();
        gameRenderer.renderGame();
        updateWord();
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
                showMenu(stage);
                break;
            case 6:
                this.timeline.pause();
                menu.setState(6);
                this.pause = stage.getScene().getRoot();
                stage.getScene().setRoot(menu.getEscMenuContent());
                stage.getScene().setOnMouseClicked(event -> {
                    updateScene(menu.getState(), stage);
                });
                stage.setTitle("Pause");
                stage.show();
                break;
            case 7:
                this.timeline.play();
                menu.setState(1);
                stage.getScene().setRoot(pause);
                stage.setTitle("SnakeGame");
                stage.show();
                menu.setState(1);
                break;
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
        initializeMiniGame();
        BorderPane miniGameContent = miniGameRenderer.getMiniGameContent();
        VBox header = createHeader();
        header.getChildren().get(0).setStyle("-fx-background-color: #000000;");
        miniGameContent.setTop(header);
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

 */
}

