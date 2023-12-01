package org.apache.maven.SnakeGame;


import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;


public class GameApp extends Application{
    static final int GRID_SIZE = 20;
    static final int CELL_SIZE = 40;
    private final int screenSizeX = 800;
    private final int screenSizeY = 800;
    private Stage primaryStage;
    private String selectedSpeed = "150";
    private ToggleGroup speedlevels = new ToggleGroup();
    private Timeline timeline;
    private Fruit fruit;
    private ArrayList<Fruit> fruits;
    public static int fruitCounter = 0;
    public static String currentWord = "";
    public String WORD = "DOG";
    public Image image = new Image("file:C:/Users/Maks/Desktop/pics/Dog.png");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(true);
        primaryStage.setTitle("Snake Prototype");
        showMainMenu();
    }

    public void showSettingsMenu() {

        Button returnBtn = new Button("RETURN");
        returnBtn.setPrefSize(75, 20);
        returnBtn.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 13));
        returnBtn.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 20; -fx-background-color: #025151");

        returnBtn.setOnAction(event -> {
            RadioButton selectedRadioButton = (RadioButton) speedlevels.getSelectedToggle();
            selectedSpeed = selectedRadioButton.getUserData().toString();
            showMainMenu();
        });

        GridPane settingsMenu = new GridPane();
        String imageUrl = "file:/C:/Users/Maks/Desktop/pics/SettingsMenuBackground.png";
        BackgroundImage menuBackground = new BackgroundImage(new Image(imageUrl), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(screenSizeX, screenSizeY, true, true, true ,true));
        settingsMenu.setBackground(new Background(menuBackground));
        settingsMenu.setPadding(new Insets(255, 20, 20, 180));


        Text settingCategory1 = new Text("Choose speed level:");
        settingCategory1.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 22));

        settingsMenu.add(settingCategory1, 0, 0, 2, 1);

        this.speedlevels = new ToggleGroup();
        ToggleButton lowspeed = new RadioButton("Low");
        lowspeed.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 20));
        lowspeed.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 15; -fx-background-color: #025151");
        ToggleButton midspeed = new RadioButton("Medium");
        midspeed.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 20));
        midspeed.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 15; -fx-background-color: #025151");
        ToggleButton highspeed = new RadioButton("High");
        highspeed.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 20));
        highspeed.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 15; -fx-background-color: #025151");

        lowspeed.setToggleGroup(speedlevels);
        midspeed.setToggleGroup(speedlevels);
        highspeed.setToggleGroup(speedlevels);
        lowspeed.setUserData("350");
        midspeed.setUserData("250");
        highspeed.setUserData("150");

        for(int i = 0; i < speedlevels.getToggles().size(); i++){
            if(speedlevels.getToggles().get(i).getUserData().toString().equals(selectedSpeed)){
                speedlevels.getToggles().get(i).setSelected(true);
            }
        }

        settingsMenu.add(lowspeed, 0, 2);
        settingsMenu.add(midspeed, 1, 2);
        settingsMenu.add(highspeed, 2, 2);
        settingsMenu.add(returnBtn, 0, 30, 1, 3);

        Scene scene = new Scene(settingsMenu, screenSizeX, screenSizeY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainMenu(){

        Button start = new Button();
        start.setText("TEST PROTOTYPE");
        start.setPrefSize(400, 120);
        start.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 40));
        start.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-width: 5; -fx-border-color: white; -fx-background-color: #025151; ");
        start.setOnAction(event -> showGame());

        Button settings = new Button();
        settings.setText("SETTINGS");
        settings.setPrefSize(400, 120);
        settings.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 40));
        settings.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-width: 5; -fx-border-color: white; -fx-background-color: #025151");
        settings.setOnAction(event -> showSettingsMenu());

        Button exit = new Button();
        exit.setText("EXIT");
        exit.setPrefSize(400, 120);
        exit.setFont(Font.font ("Charcoal CY", FontWeight.BOLD, 40));
        exit.setStyle("-fx-text-fill: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-width: 5; -fx-border-color: white; -fx-background-color: #025151");
        exit.setOnAction(event -> System.exit(0));

        VBox menu = new VBox(55);
        String imageUrl = "file:/C:/Users/Maks/Desktop/pics/StartMenuBackground.png";
        BackgroundImage menuBackground = new BackgroundImage(new Image(imageUrl), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1200, 1200, true, true, true ,true));
        menu.setBackground(new Background(menuBackground));
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(145, 0, 0, 0));
        menu.getChildren().addAll(start, settings, exit);


        Scene scene = new Scene(menu, screenSizeX, screenSizeY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showGame(){
        GameApp game = new GameApp();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.setMaxHeight(CELL_SIZE);
        hbox.setMaxWidth(GRID_SIZE * CELL_SIZE);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        hbox.getChildren().add(imageView);
        vbox.getChildren().add(hbox);
        GridPane grid = createGrid();
        vbox.getChildren().add(grid);
        Scene scene = new Scene(vbox, GRID_SIZE*CELL_SIZE, GRID_SIZE*CELL_SIZE);

        fruits = new ArrayList<>();
        primaryStage.setTitle("SnakeGame");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        Snake snake = new Snake(grid, 3);
        int[] a = {0, 0};
        int[] ar = getRandomCoordinate(snake, a);
        spawnNormalFruit(grid, ar);
        int[] arr = getRandomCoordinate(snake, ar);
        spawnRandomFruit(grid, arr);
        int[] arra = getRandomCoordinate(snake, arr);
        spawnRandomFruit(grid, arra);

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            snake.handleInput(keyCode);
        });
        this.timeline= new Timeline(new KeyFrame(Duration.millis(Integer.parseInt(selectedSpeed)), event -> {
            snake.moveSnake();
            snake.drawSnake();
            snake.rotateHead();
            snake.eatFruit(grid, game, fruits);

            if(fruits.size() < 3) {
                int[] array = getRandomCoordinate(snake, arra);
                spawnNormalFruit(grid, array);
                // fruit1 = new Fruit(grid, arrays[0], arrays[1], WORD, currentWord);
                // snake.setCurrentEdibleFruit(fruit);
            }

            if(!snake.checkIfAlive()){
                timeline.pause();
                showGameOver();
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void showGameOver(){
        Pane gameOver = new Pane();
        String imageUrl = "file:/C:/Users/Maks/Desktop/pics/GameOverScreen.png";
        BackgroundImage menuBackground = new BackgroundImage(new Image(imageUrl), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(800, 800, true, true, true ,true));
        gameOver.setBackground(new Background(menuBackground));
        gameOver.setPadding(new Insets(280, 20, 20, 280));
        Scene scene = new Scene(gameOver, screenSizeX, screenSizeY);
        primaryStage.setScene(scene);
        primaryStage.show();
        // Timer timer = new Timer();
        // timer.schedule(new TimerTask() {
        //      @Override
        //      public void run() {
        //          showMainMenu();
        //      }
        // }, 3000);
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) {
                // Restarts the game
                showGame();
            } else{
                showMainMenu();
            }
        });

    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();

        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE; col++){
                Rectangle cell = createCell(row, col);
                grid.add(cell, row, col);
            }
        }
        return grid;
    }
    private Rectangle createCell(int row, int col) {
        Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
        if((row + col) % 2 == 0){
            cell.setFill(Color.web("#579689"));

        } else{
            cell.setFill(Color.web("#7DC8B8"));
        }
        return cell;
    }

    public int getGridSize(){
        return GRID_SIZE;
    }

    public int getCellSize(){
        return CELL_SIZE;
    }

    private void spawnNormalFruit(GridPane grid, int[] arr){
        if(!(currentWord.length() >= WORD.length())){
            Fruit fruit = new Fruit(grid, arr[0], arr[1], WORD, currentWord);
            fruits.add(fruit);
        }else{
            spawnRandomFruit(grid, arr);
        }
    }

    private void spawnRandomFruit(GridPane grid, int[]arr){
        Fruit fruit = new Fruit(grid, arr[0], arr[1]);
        fruits.add(fruit);
    }

    private int[] getRandomCoordinate(Snake snake, int[] array){

        int x = (int)(Math.random() * GRID_SIZE);
        int y = (int)(Math.random() * GRID_SIZE);
        do{
            x = (int)(Math.random() * GRID_SIZE);
            y = (int)(Math.random() * GRID_SIZE);
        }
        while(snakeIntersectsFruit(snake, x, y ) && x != array[0] && y != array[1]);

        int[] arr = {x, y};
        return arr;
    }

    private boolean snakeIntersectsFruit(Snake snake, int fruitX, int fruitY) {

        for (int i = 1; i < snake.getCurrentSnakeX().size(); i++) {
            if (snake.getCurrentSnakeX().get(i) == fruitX && snake.getCurrentSnakeY().get(i) == fruitY) { // Check if the fruit within the snake
                return true;
            }
        }
        return false;
    }


}

