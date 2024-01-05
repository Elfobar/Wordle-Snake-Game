package com.example.demo.UI.Menu;


import com.example.demo.Game.GameActions;
import com.example.demo.GameCore.GameConfig;
import com.example.demo.Sound.SoundPlayer;
import com.example.demo.Sound.Sounds;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*MenuManager class is responsible for managing different menus and handling user interactions with the UI.
It implements the GameActions interface, allowing it to use method startGame() from SnakeGame class.
*/
public class MenuManager implements GameActions {

    private final GameActions gameActions;
    private final Stage stage;
    private AbstractMenu currentMenu;
    private String state;


    /*Receives the stage because there only one stage used throughout the game. Only different scenes change.*/
    public MenuManager(Stage stage, GameActions gameActions){
        this.stage = stage;
        this.currentMenu = new MainMenu();
        this.gameActions = gameActions;
        this.state = "None";
        showMenu();
    }

    //Displays the currentMenu on the stage, sets event handlers based on the type of menu
    private void showMenu(){
        // This method creates the visualisation of menu and makes is interactive
        Scene scene = new Scene(currentMenu.createContent(), GameConfig.WIDTH, GameConfig.HEIGHT);
        stage.setScene(scene);
        stage.setTitle(GameConfig.GAME_NAME);
        stage.show();
        if(currentMenu instanceof GameOverScreen){
            setGameOverKeyInputs();
        } else{
            setMenuEventHandler();
        }
    }

    //Sets the event handler for mouse clicks on menu images, such as different buttons. Buttons are represented by pictures using ImageView
    private void setMenuEventHandler(){
        Scene scene = stage.getScene();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            /*First we check if the target of the mouse is an instance of the ImageView class(image).
            If the first if check is true, line 58 casts the target to an ImageView to work with properties of this class
            Line 60 invokes a switch that determines the behaviour of the click.
             */
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getTarget() instanceof ImageView){
                    ImageView eventSource = (ImageView) mouseEvent.getTarget();
                    handleMenuEvent(eventSource.getId());
                }
            }
        };
        //This applies eventHandler to the scene meaning that the scene will continuously listen to the clicks of a user
        scene.setOnMouseClicked(eventHandler);
    }

    //Applies eventHanlder to the gameOver screen
    private void setGameOverKeyInputs(){
        // When the snake dies, the player has two options: to return back to menu with escape button and to restart with enter button
        Scene currentScene = stage.getScene();
        currentScene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) {
                runGame();
            } else if (keyCode == KeyCode.ESCAPE) {
                navigateToMainMenu();
            }
        });
    }

    //Determines the behaviour of the click on the imageView with specific ID.
    private void handleMenuEvent(String eventID){
        // Make the menu interactive
        switch(eventID){
            case "start":
                runGame();
                SoundPlayer.getInstance().playSFX(
                        Sounds.START_1,
                        Sounds.START_2,
                        Sounds.START_3);
                break;
            case "minigame":
                runMiniGame();
                SoundPlayer.getInstance().playSFX(
                        Sounds.START_1,
                        Sounds.START_2,
                        Sounds.START_3);
                break;
            case "settings":
                navigateToSettingsMenu();
                break;
            case "scoreboard":
                navigateToScoreboardMenu();
                break;
            case "back":
                navigateToMainMenu();
                break;
            case "continue":
                if (state.equals("Game")){
                    handleGamePause();
                } else {
                    handleMiniGamePause();
                }
                break;
            case "exit":
                Platform.exit();
                break;
        }
    }

    //Handles gameOver by changing the scene and stopping the gameLoop
    public void handleGameOver(){
        // When the game is over create game over screen, menu and stop the game
        currentMenu = new GameOverScreen();
        showMenu();
        gameActions.stopGame();
    }


    //Handles gamePause by either pausing or resuming the game
    public void handleGamePause(){;
        if(currentMenu instanceof EscMenu){
            gameActions.resumeGame(stage);
            currentMenu = new MainMenu();
        } else{
            currentMenu = new EscMenu();
            showMenu();
            gameActions.stopGame();
        }
    }

    //Handles gamePause by either pausing or resuming the mini game
    public void handleMiniGamePause(){
        // Stop the mini-game when ESC is pressed
        if(currentMenu instanceof EscMenu){
            gameActions.resumeMiniGame(stage);
            currentMenu = new MainMenu();
        } else{
            currentMenu = new EscMenu();
            showMenu();
            gameActions.stopMiniGame();
        }
    }
    //Starts the game by playing the music and invoking the start game inside SnakeGame class
    private void runGame(){
        // When the game runs, the background music starts
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.BACKGROUND_TRACK);
        gameActions.startGame(stage);
    }

    //Starts the mini game by playing the music and invoking the start game inside SnakeGame class
    private void runMiniGame(){
        // When the mini-game runs, the background music starts
        SoundPlayer.getInstance().playBackgroundMusic(Sounds.BACKGROUND_TRACK);
        gameActions.startMiniGame(stage);
    }

    //Plays the random sound effect when the menu has changed
    private void navigateToMainMenu(){
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4);
        currentMenu = new MainMenu();
        showMenu();
    }

    //Plays the random sound effect when the menu has changed
    private void navigateToSettingsMenu() {
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4);
        currentMenu = new SettingsMenu();
        showMenu();
    }

    //Plays the random sound effect when the menu has changed
    private void navigateToScoreboardMenu(){
        SoundPlayer.getInstance().playSFX(
                Sounds.BUTTON_1,
                Sounds.BUTTON_2,
                Sounds.BUTTON_3,
                Sounds.BUTTON_4
        );
        currentMenu = new ScoreboardMenu();
        showMenu();
    }
    public void setState(String state){
        this.state = state;
    }
    @Override
    public void startGame(Stage stage){}
    @Override
    public void startMiniGame(Stage stage){}
    @Override
    public void stopGame(){}
    @Override
    public void stopMiniGame(){}
    @Override
    public void resumeGame(Stage stage){}
    @Override
    public void resumeMiniGame(Stage stage){}

}
